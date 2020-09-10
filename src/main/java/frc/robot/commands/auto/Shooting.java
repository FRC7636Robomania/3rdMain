/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.Wing;

public class Shooting extends ParallelCommandGroup {
  /**
   * Creates a new Shooting.
   */
  public Shooting(Shooter shooter, double velocity, Conveyor conveyor, Wing wing) {
    super(
      new VelocityShoot(shooter, velocity).withTimeout(3),
      new StartEndCommand(()->wing.forward(), ()->wing.stop(), wing).withTimeout(3.3),
      new WaitCommand(0.5),
      new StartEndCommand(()->conveyor.forceSpin(), ()->conveyor.stop(), conveyor).withTimeout(3)
    );
  }
}
