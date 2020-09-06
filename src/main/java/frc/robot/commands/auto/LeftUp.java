/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.arm.ArmOut;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.chassis.DrivetrainBase;
import frc.robot.subsystems.chassis.trajectory.TrajectoryFactory;
import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;
import frc.robot.subsystems.pneumatic.Arm;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Shooter;

public class LeftUp extends SequentialCommandGroup {
  
  public LeftUp(TrajectorySystem drivetrain, DrivetrainBase base, Intake intake, Conveyor conveyor, Shooter shooter, Arm arm) {

    super(
      //差瞄準拉
      new ShortShoot(conveyor, shooter),
      new InstantCommand(()-> TrajectoryFactory.getTrajectory(Constants.Trajectory.three)),
      new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
      new TrajectoryCommand(TrajectoryFactory.getTrajectory(Constants.Trajectory.three), drivetrain, base),
            // .andThen(()->drivetrain.setOutput(0, 0))
      new ArmOut(arm).andThen(()->intake.forward()),

      new TrajectoryCommand(TrajectoryFactory.getTrajectory(Constants.Trajectory.upSock), drivetrain, base)
            .andThen(()->drivetrain.setOutput(0, 0))
            .andThen(()->intake.stop()),
      //差瞄準拉
      new LongShoot(conveyor, shooter)
      
      );
  }
}
