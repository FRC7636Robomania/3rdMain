/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.motor.MotorFactory;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants.*;


/**
 * Must be sure these objects will be instantiated only once
 */
public class DrivetrainBase extends SubsystemBase {
  protected static WPI_TalonFX leftMas  = new WPI_TalonFX(Constants.Motor.leftMaster);
  protected static WPI_TalonFX leftFol  = new WPI_TalonFX(Constants.Motor.leftFollewer);
  protected static WPI_TalonFX rightMas = new WPI_TalonFX(Constants.Motor.rightMaster);
  protected static WPI_TalonFX rightFol = new WPI_TalonFX(Constants.Motor.rightFollower);
  protected static AHRS ahrs = new AHRS(SPI.Port.kMXP);
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
    MotorFactory.setInvert(leftMas, Motor.isLeftMotorInvert);
    MotorFactory.setPosion(leftMas, 0, 0, 10);
    MotorFactory.setSensor(leftMas, FeedbackDevice.IntegratedSensor);
    MotorFactory.setSensorPhase(leftMas, Motor.isLeftPhaseInvert);
    MotorFactory.configLikePrevious(leftFol, Motor.isLeftPhaseInvert, Motor.isLeftMotorInvert);
    MotorFactory.setFollower(rightMas, rightFol);
    MotorFactory.configLikePrevious(rightMas, Motor.isRightPhaseInvert, Motor.isRightMotorInvert);
    MotorFactory.configLikePrevious(rightFol, Motor.isRightPhaseInvert, Motor.isRightMotorInvert);
    MotorFactory.voltageCompSaturation(rightMas, 11);
    MotorFactory.voltageCompSaturation(leftMas,  11);

    MotorFactory.configPF(leftMas,  Chassis.p, Chassis.f, 0);
    MotorFactory.configPF(rightMas, Chassis.p, Chassis.f, 0);
    
    
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
