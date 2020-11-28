/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.shooter.Rack;
import frc.robot.subsystems.shooter.Tower;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoAim extends SequentialCommandGroup {
  /**
   * Creates a new AutoAim.
   */
  public AutoAim(Tower tower, Rack rack) {
    super(
      new RunCommand(() -> tower.aim(), tower).withTimeout(0.9)
      // new RunCommand(() -> rack.aim(Rack.unit(Limelight.getDistance()))).withTimeout(0.9)
    );
  }
}
