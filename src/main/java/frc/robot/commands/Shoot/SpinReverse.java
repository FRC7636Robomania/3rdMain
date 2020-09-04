/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Spinable;
/**
 * 要使用這個命令需要傳遞一個Spinable的子類別，並覆寫Spinable的三個抽象方法，
 * 在這個命令中，馬達將會呼叫該子類別的reverse()方法，結束時呼叫stop()方法
 */
public class SpinReverse extends CommandBase {
  private Spinable motor;

  public SpinReverse(Spinable motor) {
    this.motor = motor;
    addRequirements(motor);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    motor.reverse();
  }

  @Override
  public void end(boolean interrupted) {
    motor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
