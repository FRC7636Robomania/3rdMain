/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.chassis.DrivetrainBase;
import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;
import frc.robot.subsystems.pneumatic.Arm;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Rack;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.Tower;
import frc.robot.subsystems.shooter.Wing;
import frc.robot.subsystems.vision.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Middle extends SequentialCommandGroup {
  /**
   * Creates a new Middle.
   */
  public Middle(TrajectorySystem drivetrain, DrivetrainBase base, Intake intake, Conveyor conveyor, Shooter shooter, Arm arm, Wing wing, Rack rack, Tower tower) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      // new InstantCommand(()-> TrajectoryFactory.getTrajectory(Constants.Trajectory.middle)),
      // new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
      // new TrajectoryCommand(TrajectoryFactory.getTrajectory(Constants.Trajectory.middle), drivetrain, base)
      //   .andThen(()->drivetrain.setOutput(0, 0)),

      new RunCommand(()->rack.forward(), rack).withTimeout(0.8),

      new RunCommand(()->rack.aim(Rack.unit(Limelight.getDistance())), rack).withTimeout(1.0),

      new RunCommand(() -> tower.aim(), tower).withTimeout(1.0),

      new Shooting(shooter, 13500, conveyor, wing)
    );
  }
}
