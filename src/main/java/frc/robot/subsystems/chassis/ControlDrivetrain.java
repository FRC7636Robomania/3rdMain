/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpiutil.math.MathUtil;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ControlDrivetrain extends DrivetrainBase {
  private double m_quickStopAccumulator = 0, leftout = 0, rightout = 0, lastRotation = 0;
  public ControlDrivetrain(){
   
  }
  public void drive(double speed, double turn){
      leftMas.set(ControlMode.PercentOutput,speed+turn);
      rightMas.set(ControlMode.PercentOutput,speed-turn);
      SmartDashboard.putNumber("speed",speed);
      SmartDashboard.putNumber("turn",turn);
  }
  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
    if(Math.abs(zRotation)<0.05){
      zRotation =0;
     }else{
      double err = lastRotation - zRotation;
			if(Math.abs(err) > 0.3){
		    zRotation = lastRotation - err * 0.4;
		  }else if(Math.abs(err) > 0.8) {
		    zRotation = lastRotation - err * 0.2;
		  }
      lastRotation = zRotation;
    }
    zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);
    double angularPower;
    boolean overPower;
    double m_quickStopAlpha = 0.1;
    if (isQuickTurn) {
      if (Math.abs(xSpeed) < 0.1) {
        m_quickStopAccumulator = (1 - 0.1) * m_quickStopAccumulator
            + m_quickStopAlpha * MathUtil.clamp(zRotation, -1.0, 1.0) * 2;
      }

      overPower = true;
      angularPower = zRotation;
    } else {
      overPower = false;
      angularPower = Math.abs(xSpeed) * zRotation - m_quickStopAccumulator;

      if (m_quickStopAccumulator > 1) {
        m_quickStopAccumulator -= 1;
      } else if (m_quickStopAccumulator < -1) {
        m_quickStopAccumulator += 1;
      } else {
        m_quickStopAccumulator = 0.0;
      }
    }

    double leftMotorOutput = xSpeed - angularPower;
    double rightMotorOutput = xSpeed + angularPower;

    // If rotation is overpowered, reduce both outputs to within acceptable range
    if (overPower) {
      if (leftMotorOutput > 1.0) {
        rightMotorOutput -= leftMotorOutput - 1.0;
        leftMotorOutput = 1.0;
      } else if (rightMotorOutput > 1.0) {
        leftMotorOutput -= rightMotorOutput - 1.0;
        rightMotorOutput = 1.0;
      } else if (leftMotorOutput < -1.0) {
        rightMotorOutput -= leftMotorOutput + 1.0;
        leftMotorOutput = -1.0;
      } else if (rightMotorOutput < -1.0) {
        leftMotorOutput -= rightMotorOutput + 1.0;
        rightMotorOutput = -1.0;
      }
    }

    // Normalize the wheel speeds
    double maxMagnitude = Math.max(Math.abs(leftMotorOutput), Math.abs(rightMotorOutput));
    if (maxMagnitude > 1.0) {
      leftMotorOutput /= maxMagnitude;
      rightMotorOutput /= maxMagnitude;
    }
    leftout=  leftMotorOutput;
    rightout = rightMotorOutput;

    leftMas.set(-leftout);
    rightMas.set(-rightout);
  }
  
}
