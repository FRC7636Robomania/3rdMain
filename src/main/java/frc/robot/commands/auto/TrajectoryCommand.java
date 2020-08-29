/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;
/**
 * Add your docs here.
 */
public class TrajectoryCommand extends RamseteCommand{
  public TrajectoryCommand(Trajectory trajectory, TrajectorySystem drivetrain){
    super(trajectory, 
          drivetrain::getPose, 
          new RamseteController(20.0, 1), 
          drivetrain.getKinematics(), 
          drivetrain::setOutput, 
          drivetrain
         );
  }
}
