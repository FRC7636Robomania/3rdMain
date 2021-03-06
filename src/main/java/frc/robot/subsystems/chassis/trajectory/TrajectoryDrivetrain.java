/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis.trajectory;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.*;
import frc.robot.subsystems.chassis.DrivetrainBase;


public class TrajectoryDrivetrain extends DrivetrainBase implements TrajectorySystem{

  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());
  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Motor.wheelPitch);
  private Pose2d pose;
  
  /**
   * Creates a new Drivetrain.
   */
  public TrajectoryDrivetrain() {
    Shuffleboard.getTab("Auto").addNumber("x", this::getX);
    Shuffleboard.getTab("Auto").addNumber("y", this::getY);
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return pose;
  }
  /**
   * encoder velocity to chassis speed
   * 
   * @return current chassis speed
   */
  public DifferentialDriveWheelSpeeds getSpeed() {
    return new DifferentialDriveWheelSpeeds(
      leftMas.getSelectedSensorVelocity() * Motor.distancePerPulse, 
      rightMas.getSelectedSensorVelocity() * Motor.distancePerPulse
      );
  }

  /**
   * set odmetry,let the starting position 
   * of the robot be the starting point of the trajectory
   * 
   * @param pose2d the trajectory origin
   */
  public void setOdmetry(Pose2d pose2d){
    odometry.resetPosition(pose2d, pose2d.getRotation());
  }
  
  /**
   * Returns left velocity
   * @return
   */
  public double getLeftVelocity(){
    return ((leftMas.getSelectedSensorVelocity()));
  }

  public double getLeftPosition(){
    return ((leftMas.getSelectedSensorPosition()));
  }

  /**
   * Returns right velocity
   * @return
   */
  public double getRigthtVelocity(){
    return ((rightMas.getSelectedSensorVelocity()));
  }

  /**
   * Returns right velocity
   * @return
   */
  public double getRigthtPosition(){
    return ((rightMas.getSelectedSensorPosition()));
  }
  
  /**
   * Provide kinematics object, contain track width
   * 
   * @return kinematics
   */
  public  DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  /**
   * get "X" from odmetry
   * 
   * @return current "X"
   */
  public double getX(){
    return odometry.getPoseMeters().getTranslation().getX();
  }  

  /**
   * get "Y" from odmetry
   * 
   * @return current "Y"
   */
  public double getY(){
    return odometry.getPoseMeters().getTranslation().getY();
  }

  public void setOutput(double left, double right) {

    leftMas.set(ControlMode.Velocity, left / Motor.distancePerPulse / 10);
    rightMas.set(ControlMode.Velocity, right / Motor.distancePerPulse / 10);
    SmartDashboard.putNumber("leftOutput ", left / Motor.distancePerPulse / 10);
    SmartDashboard.putNumber("rightOutput", right / Motor.distancePerPulse / 10);
  }

  public void voltage(double left, double right){
    leftMas.set(ControlMode.PercentOutput, left / 11);
    rightMas.set(ControlMode.PercentOutput, right / 11);
    SmartDashboard.putNumber("leftOutput ", left / 11);
    SmartDashboard.putNumber("rightOutput", right / 11);
  }
 
  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading 
   */
  public Rotation2d getHeading() {
    return Rotation2d.fromDegrees(-ahrs.getAngle());
  }

  /**
   * Show message
   */
  public void message(){
    //distance
    SmartDashboard.putNumber("leftDistants", getLeftPosition() * Motor.distancePerPulse);
    SmartDashboard.putNumber("rightDistants", getRigthtPosition() * Motor.distancePerPulse);
    SmartDashboard.putNumber("Yaw", ahrs.getYaw());
  }

  @Override
  public void periodic() {
    pose = odometry.update(getHeading(), 
    leftMas.getSelectedSensorPosition()  * Motor.distancePerPulse,
    rightMas.getSelectedSensorPosition() * Motor.distancePerPulse);
    message();
  }
}
