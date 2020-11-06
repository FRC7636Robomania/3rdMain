/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.AutoAim;
import frc.robot.commands.arm.ArmIn;
import frc.robot.commands.arm.ArmOut;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.chassis.DrivetrainBase;
import frc.robot.subsystems.chassis.trajectory.TrajectoryFactory;
import frc.robot.subsystems.chassis.trajectory.TrajectorySystem;
import frc.robot.subsystems.pneumatic.Arm;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Rack;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.Tower;
import frc.robot.subsystems.shooter.Wing;

public class LeftUp extends SequentialCommandGroup {
  
  public LeftUp(TrajectorySystem drivetrain, DrivetrainBase base, Intake intake, 
              Conveyor conveyor, Shooter shooter, Arm arm, Wing wing, Rack rack, Tower tower) {

    super(
      new AutoAim(rack, tower),
      new RunCommand(()->rack.forward(), rack).withTimeout(0.8),

      new RunCommand(()->rack.aim(-9090), rack).withTimeout(1.0),

      new RunCommand(() -> tower.aim(), tower).withTimeout(1.0),
      new Shooting(shooter, 13500, conveyor, wing),
      new InstantCommand(()-> TrajectoryFactory.getTrajectory(Constants.Trajectory.three)),
      new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
      new TrajectoryCommand(TrajectoryFactory.getTrajectory(Constants.Trajectory.three), drivetrain, base)
            .andThen(()->drivetrain.setOutput(0, 0)),
      new ArmIn(arm).withTimeout(1).andThen(()->intake.forward()).andThen(()->wing.forward()),
      
      new InstantCommand(()-> TrajectoryFactory.initPose(drivetrain)),
      new TrajectoryCommand(TrajectoryFactory.getTrajectory(Constants.Trajectory.upSock), drivetrain, base)
            .andThen(()->drivetrain.setOutput(0, 0))
            .andThen(()->intake.stop())
            .andThen(()->wing.stop()),
      new AutoAim(rack, tower),
      new Shooting(shooter, 13500, conveyor, wing)

      //差瞄準拉
      
      );
  }
}
