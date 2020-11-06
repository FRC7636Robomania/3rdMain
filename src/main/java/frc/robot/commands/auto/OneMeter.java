/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.*;
import frc.robot.commands.arm.ArmOut;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.chassis.DrivetrainBase;
import frc.robot.subsystems.chassis.trajectory.TrajectoryFactory;
import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;
import frc.robot.subsystems.pneumatic.Arm;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.Wing;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class OneMeter extends SequentialCommandGroup {
  /**
   * Creates a new OneMeter.
   */
  public OneMeter(TrajectorySystem drivetrain, DrivetrainBase base, Intake intake, Conveyor conveyor, Shooter shooter, Arm arm, Wing wing) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(       
    //差瞄準拉
    new Shooting(shooter, 15000, conveyor, wing),
    new InstantCommand(()-> TrajectoryFactory.getTrajectory(Trajectory.OneMeter)),
    new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
    new TrajectoryCommand(TrajectoryFactory.getTrajectory(Trajectory.OneMeter), drivetrain, base)
          // .andThen(()->drivetrain.setOutput(0, 0))
    // new ArmOut(arm).withTimeout(0.5).andThen(()->intake.forward()),
    // new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
    // new TrajectoryCommand(TrajectoryFactory.getTrajectory(Trajectory.OneMeter), drivetrain, base)
    //       .andThen(()->drivetrain.setOutput(0, 0))
    //       .andThen(()->intake.stop()),
    // new Shooting(shooter, 17000, conveyor, wing)
    //差瞄準拉
    

    );
  }
}
