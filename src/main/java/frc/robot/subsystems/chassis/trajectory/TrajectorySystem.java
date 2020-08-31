/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis.trajectory;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

/**
 * Add your docs here.
 */
public interface TrajectorySystem {
    /**
     * Provide kinematics object, contain track width
     * 
     * @return kinematics
     */
    public DifferentialDriveKinematics getKinematics();

    /**
     * encoder velocity to chassis speed
     * 
     * @return current chassis speed
     */
    public DifferentialDriveWheelSpeeds getSpeed();

    /**
     * Provide feedforward controller
     * 
     * @return feedForward controlller 
     */
    public SimpleMotorFeedforward getFeedforward();

    /**
     * Provide PID controller
     * 
     * @return left PID controller
     */
    public PIDController getLeftPidController();

    /**
     * Provide PID controller
     * 
     * @return right PID controller
     */
    public PIDController getRightPidController();

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose();

    /**
     * set odmetry,let the starting position 
     * of the robot be the starting point of the trajectory
     * 
     * @param pose2d the trajectory origin
     */
    public void setOdmetry(Pose2d pose2d);

    /**
     * Returns left velocity
     * @return
     */
    public double getLeftVelocity();

    /**
     * Returns left position
     * @return
     */
    public double getLeftPosition();

    /**
     * Returns right velocity
     * @return
     */
    public double getRigthtVelocity();

    /**
     * Returns right position
     * @return
     */
    public double getRigthtPosition();

    /**
     * get "X" from odmetry
     * 
     * @return current "X"
     */
    public double getX();

    /**
     * get "Y" from odmetry
     * 
     * @return current "Y"
     */
    public double getY();

    /**
     * Use velocity to set output.
     * @param left
     * @param right
     */
    public void setOutput(double left, double right);
    /**
     * Use voltage to set output.
     * @param left
     * @param right
     */
    public void voltage(double left, double right);

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading 
     */
    public Rotation2d getHeading();

    /**
     * Show message
     */
    public void message();
}
