/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pneumatic;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.subsystems.shooter.Spinable;

public class Fol extends Spinable {
  WPI_VictorSPX slaver = new WPI_VictorSPX(4);
  /**
   * Creates a new Fol.
   */
  public Fol() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void forward() {
    slaver.set(0.8);
  }

  @Override
  public void stop() {
    slaver.set(0);
  }

  @Override
  public void reverse() {
    slaver.set(-0.8);
  }

  @Override
  public String getStatus() {
    // TODO Auto-generated method stub
    return null;
  }
}
