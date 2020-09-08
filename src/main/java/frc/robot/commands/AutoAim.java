/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.shooter.Rack;
import frc.robot.subsystems.shooter.Tower;
import frc.robot.subsystems.vision.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoAim extends SequentialCommandGroup {
  /**
   * Creates a new AutoAim.
   */
  public AutoAim(Rack Rac,Tower tow) {
    //new InstantCommand(()-> Rac.aim(Limelight.getDistance())))
    super(new InstantCommand(() -> tow.aim(Limelight.getTx())), new InstantCommand(()-> Rac.aim(Limelight.getDistance())));
  }
}
