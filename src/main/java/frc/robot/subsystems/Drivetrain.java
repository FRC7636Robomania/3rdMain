/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  WPI_TalonFX leftMas  = new WPI_TalonFX(Constants.Motor.leftMaster);
  WPI_TalonFX leftFol  = new WPI_TalonFX(Constants.Motor.leftMaster);
  WPI_TalonFX rightMas = new WPI_TalonFX(Constants.Motor.leftMaster);
  WPI_TalonFX rightFol = new WPI_TalonFX(Constants.Motor.leftMaster);


  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
