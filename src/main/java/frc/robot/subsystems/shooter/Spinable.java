/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * This class provide three method, almost every motor on the robot
 * can extends this class.
 * Can be used with SpinForward & SpinReverse.
 * Different part of robot (e.g.:flywheel, conveyor, wing), will have different output mode.
 * Some modidy can be wroten in the chileClass.
 * 
 * 這個類別可以被用來實現幾乎所有機器人上的馬達，每個馬達不同的地方可以在各自的類別中覆寫以下三個方法，
 * 搭配SpinForward和SpinReverse可以有效地減少命令的類別數量
 */
public abstract class Spinable extends SubsystemBase{
  public abstract void forward();
  public abstract void stop();
  public abstract void reverse();
  public abstract String getStatus();
}
