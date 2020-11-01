package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants.PowCon;

public class  Shooter extends Spinable{
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 40, 50, 1);
  private TalonFX flywheel = new TalonFX(PowCon.flywheel);
  private TalonFX flywheelS = new TalonFX(PowCon.flywheel2);
  double setVel = 0;

  public Shooter() {
    MotorFactory.setSensor(flywheel,FeedbackDevice.IntegratedSensor);
    MotorFactory.setSensor(flywheelS, FeedbackDevice.IntegratedSensor);
    MotorFactory.configPF(flywheel,PowCon.flywheel_kP,PowCon.flywheel_kF,0);
    MotorFactory.configPF(flywheelS, PowCon.flywheel_kP, PowCon.flywheel_kF, 0);
    flywheel.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    flywheelS.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);

    flywheel.configClosedloopRamp(0.5, 10);
    flywheelS.configClosedloopRamp(0.5, 10);
    flywheel.setInverted(true);
    flywheelS.follow(flywheel);
    flywheelS.setInverted(InvertType.OpposeMaster);

    
  }

  public double getflywheelspeed(){
    return (flywheel.getSelectedSensorVelocity() + flywheelS.getSelectedSensorVelocity()) / 2;
  }

  public double getSetValue(){
    if(setVel == 0) {
      return 21600 * 0.75;
    }else{
      return setVel * 0.85;
    }
  }

  public void velocity(double velocity){
    setVel = velocity;
    flywheel.set(ControlMode.Velocity, velocity);
  }
  
  @Override
  public void forward() {
    setVel = 20000;
    flywheel.set(ControlMode.Velocity, setVel);
    SmartDashboard.putString("Shooterstatue","ShooterFoward");
  }

  @Override
  public void stop() {
    setVel = 0;
    flywheel.set(ControlMode.Velocity, setVel);
    SmartDashboard.putString("Shooterstatue","ShooterStop");

  }

  @Override
  public void reverse() {
    SmartDashboard.putString("Shooterstatue","Don't do that please");
    System.out.println("Don't do that please");
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("flyvel", flywheel.getSelectedSensorVelocity());
  }
}


