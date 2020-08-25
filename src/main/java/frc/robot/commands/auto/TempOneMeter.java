/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.chassis.trajectory.TrajectoryFactory;
import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TempOneMeter extends SequentialCommandGroup {
  /**
   * Creates a new TempOneMeter.
   */
  public TempOneMeter(TrajectorySystem drivetrain) {
    super(
      new InstantCommand(()-> TrajectoryFactory.getTrajectory(Constants.Trajectory.OneMeter)),
      new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
      new RamseteCommand(
        TrajectoryFactory.getTrajectory(Constants.Trajectory.OneMeter), 
        drivetrain::getPose, 
        new RamseteController(20.0, 0.9), 
        // drivetrain.getFeedforward(),
        drivetrain.getKinematics(), 
        // drivetrain::getSpeed,
        // drivetrain.getLeftPidController(),
        // drivetrain.getRightPidController(),
        drivetrain::setOutput, 
        drivetrain)
            .andThen(()->drivetrain.setOutput(0, 0)),
      new InstantCommand(()-> drivetrain.setOutput(0, 0)));
  }
}
