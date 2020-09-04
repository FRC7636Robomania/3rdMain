/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Shooter;


public class ShortShoot extends SequentialCommandGroup {

  public ShortShoot(Conveyor conveyor, Shooter shooter) {
    super(
      // new RunCommand(toRun, requirements)
      new RunCommand(()->shooter.velocity(12000), shooter).withTimeout(3),
      new RunCommand(()->conveyor.forward(), conveyor).withTimeout(3.5)
    );
  }
}
