/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pneumatic;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.subsystems.shooter.Spinable;

public class Hanging extends Spinable {
  WPI_TalonSRX master = new WPI_TalonSRX(36);
  WPI_VictorSPX slaver = new WPI_VictorSPX(6);
  /**
   * Creates a new Hanging.
   */
  public Hanging() {
    slaver.follow(master);
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void forward() {
    master.set(ControlMode.Velocity,0.8);    
  }

  @Override
  public void stop() {
    master.set(ControlMode.Velocity,0);

  }

  @Override
  public void reverse() {
    master.set(ControlMode.Velocity,-0.8);

  }

  @Override
  public String getStatus() {
    
    return null;
  }
}
