/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.motor.MotorFactory;

public class Drivetrain extends SubsystemBase {
  WPI_TalonFX leftMas  = new WPI_TalonFX(Constants.Motor.leftMaster);
  WPI_TalonFX leftFol  = new WPI_TalonFX(Constants.Motor.leftMaster);
  WPI_TalonFX rightMas = new WPI_TalonFX(Constants.Motor.leftMaster);
  WPI_TalonFX rightFol = new WPI_TalonFX(Constants.Motor.leftMaster);
  AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());
  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.Motor.wheelPitch);
  private Pose2d pose;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    MotorFactory.setFollower(leftMas, leftFol);
    MotorFactory.setInvert(leftMas, InvertType.None);
    MotorFactory.setPosion(leftMas, 0, 0, 10);
    MotorFactory.setSensor(leftMas, FeedbackDevice.IntegratedSensor);
    MotorFactory.setSensorPhase(leftMas, false);
    MotorFactory.configLikePrevious(leftFol, false, InvertType.FollowMaster);
    MotorFactory.setFollower(rightMas, rightFol);
    MotorFactory.configLikePrevious(rightMas, true, InvertType.InvertMotorOutput);
    MotorFactory.configLikePrevious(rightFol, true, InvertType.FollowMaster);
    ahrs.reset();
  }
  

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return odometry.getPoseMeters();
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
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    SmartDashboard.putNumber("leftRate", getLeftvelocity() * Constants.Motor.distancePerPulse);
    SmartDashboard.putNumber("rightRate", getRigthtvelocity() * Constants.Motor.distancePerPulse);
    return new DifferentialDriveWheelSpeeds(getLeftvelocity(), getRigthtvelocity());
  }

  /**
   * Returns left velocity
   * @return
   */
  public double getLeftvelocity(){
    return (leftMas.getSelectedSensorVelocity() + leftFol.getSelectedSensorVelocity()) / 2.0 * Constants.Motor.distancePerPulse;
  }

  /**
   * Returns right velocity
   * @return
   */
  public double getRigthtvelocity(){
    return (rightMas.getSelectedSensorVelocity() + rightFol.getSelectedSensorVelocity()) / 2.0 * Constants.Motor.distancePerPulse;
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

  /**
   * set motor output, change "voltage" to "PercentOutput"
   * 
   * @param leftVolts    left voltages
   * @param rightVolts   right voltages
   */
  public void setOutput(double leftVolts, double rightVolts) {
    leftMas.set(ControlMode.PercentOutput, leftVolts / 12);
    rightMas.set(ControlMode.PercentOutput, rightVolts / 12);

    SmartDashboard.putNumber("leftOutput ", leftVolts /12);
    SmartDashboard.putNumber("rightOutput", rightVolts / 12);
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
    SmartDashboard.putNumber("x", getX());
    SmartDashboard.putNumber("Y", getY());
    //distants
    
    SmartDashboard.putNumber("leftDistants", getLeftvelocity() * Constants.Motor.distancePerPulse);
    SmartDashboard.putNumber("rightDistants", getRigthtvelocity() * Constants.Motor.distancePerPulse);
    SmartDashboard.putNumber("Yaw", ahrs.getYaw());
  }
  public Pose2d getPose2d(){
    return pose;
  }
  @Override
  public void periodic() {
    pose = odometry.update(getHeading(), 
    getLeftvelocity() * Constants.Motor.distancePerPulse,
    getRigthtvelocity() * Constants.Motor.distancePerPulse);
    message();
  }
}
