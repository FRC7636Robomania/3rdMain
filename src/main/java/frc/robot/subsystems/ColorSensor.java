/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
  ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kMXP);
  /**
   * Creates a new ColorSensor.
   */
  public ColorSensor() {

  }
  public Color getColor(){
    return colorSensor.getColor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
