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
import frc.robot.subsystems.chassis.DrivetrainBase;
import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;
/**
 * 把複雜的建構式包裝成簡單的類別，需要搭配實現了TrajectorySystem的機器人
 */
public class TrajectoryCommand extends RamseteCommand{
  public TrajectoryCommand(Trajectory trajectory, TrajectorySystem drivetrain, DrivetrainBase base){
    super(trajectory, 
          drivetrain::getPose, 
          new RamseteController(4.0, 0.8), 
          drivetrain.getKinematics(), 
          drivetrain::setOutput, 
          base
         );
  }
}
