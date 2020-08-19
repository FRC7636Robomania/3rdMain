/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.motor.MotorFactory;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Must be sure these objects will be instantiated only once
 */
public class DrivetrainBase extends SubsystemBase {
  protected static TalonFX leftMas  = new TalonFX(Constants.Motor.leftMaster);
  protected static TalonFX leftFol  = new TalonFX(Constants.Motor.leftFollewer);
  protected static TalonFX rightMas = new TalonFX(Constants.Motor.rightMaster);
  protected static TalonFX rightFol = new TalonFX(Constants.Motor.rightFollower);
  protected static AHRS ahrs = new AHRS(SPI.Port.kMXP);
  // Joystick joy1 = new Joystick(0);
  private   static boolean isFirst = true; 
  
  /**
   * Creates a new DrivetrainBase.
   */
  public DrivetrainBase() {
    if(isFirst){
      firstConfig();
      isFirst = false;
    }
  }

  public void firstConfig(){
    MotorFactory.setFollower(leftMas, leftFol);
    MotorFactory.setInvert(leftMas, Constants.Motor.isLeftMotorInvert);
    MotorFactory.setPosion(leftMas, 0, 0, 10);
    MotorFactory.setSensor(leftMas, FeedbackDevice.IntegratedSensor);
    MotorFactory.setSensorPhase(leftMas, Constants.Motor.isLeftPhaseInvert);
    MotorFactory.configLikePrevious(leftFol,Constants.Motor.isLeftPhaseInvert, Constants.Motor.isLeftMotorInvert);
    MotorFactory.setFollower(rightMas, rightFol);
    MotorFactory.configLikePrevious(rightMas, Constants.Motor.isRightPhaseInvert, Constants.Motor.isRightMotorInvert);
    MotorFactory.configLikePrevious(rightFol, Constants.Motor.isRightPhaseInvert, Constants.Motor.isRightMotorInvert);
    MotorFactory.voltageCompSaturation(rightMas, 10);
    MotorFactory.voltageCompSaturation(leftMas, 10);
    // MotorFactory.configPID(leftMas, 20.3, 10, 0);
    // MotorFactory.configPID(rightMas, 20.3, 10, 0);
    // leftMas.config_kP(0, 10);
    // rightMas.config_kP(0, 10);
    
    
    ahrs.reset();
  }

  public static void resetSensor(){
    MotorFactory.setPosion(leftMas, 0, 0, 0);
    MotorFactory.setPosion(rightMas, 0, 0, 0);
    MotorFactory.setPosion(leftFol, 0, 0, 0);
    MotorFactory.setPosion(rightFol, 0, 0, 0);
    ahrs.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
