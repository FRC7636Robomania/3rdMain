/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pneumatic;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.shooter.Spinable;

public class Hanging extends Spinable {
  WPI_TalonSRX master = new WPI_TalonSRX(36);
//   WPI_VictorSPX slaver = new WPI_VictorSPX(4);
  /**
   * Creates a new Hanging.
   */
  public Hanging() {
    // slaver.follow(master);
    // slaver.setInverted(InvertType.FollowMaster);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void forward() {
    master.set(ControlMode.PercentOutput,0.8);  
    SmartDashboard.putString("Hang", "Foward");  
  }

  @Override
  public void stop() {
    master.set(ControlMode.PercentOutput,0);
    SmartDashboard.putString("Hang", "Stop");  


  }

  @Override
  public void reverse() {
    master.set(ControlMode.PercentOutput,-0.8);
    SmartDashboard.putString("Hang", "Reverse");  


  }

  @Override
  public String getStatus() {
    
    return null;
  }
}
