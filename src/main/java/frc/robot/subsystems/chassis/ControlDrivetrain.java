/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ControlDrivetrain extends DrivetrainBase {
  private DifferentialDrive chassis = new DifferentialDrive(new SpeedControllerGroup(leftMas, leftFol), 
                                                            new SpeedControllerGroup(rightMas, rightFol));
    
  public ControlDrivetrain(){
    chassis.setQuickStopAlpha(0.1);
    chassis.setQuickStopThreshold(0.1);
    chassis.setDeadband(0.08);
  }
  public void drive(double speed, double turn){
      leftMas.set(ControlMode.PercentOutput,speed+turn);
      rightMas.set(ControlMode.PercentOutput,speed-turn);
      SmartDashboard.putNumber("speed",speed);
      SmartDashboard.putNumber("turn",turn);
  }
  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn){
    chassis.curvatureDrive(xSpeed * 0.5, zRotation * 0.5, isQuickTurn);
  } 
}
