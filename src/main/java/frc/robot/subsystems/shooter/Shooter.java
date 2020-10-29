package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants.PowCon;

public class  Shooter extends Spinable{
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 40, 50, 1);
  private TalonFX flywheel = new TalonFX(PowCon.flywheel);
  double setVel = 0, vel = 0;

  public Shooter() {
    MotorFactory.setSensor(flywheel,FeedbackDevice.IntegratedSensor);
    MotorFactory.configPF(flywheel,PowCon.flywheel_kP,PowCon.flywheel_kF,0);
    flywheel.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    //MotorFactory.configmotorlimit(flywheel,0.001, 1, -1, 1, 30);
    flywheel.configClosedloopRamp(0.5, 10);
    flywheel.setInverted(true);
    
  }

  public double getflywheelspeed(){
    return flywheel.getSelectedSensorVelocity();
  }

  public double getSetValue(){
    if(setVel == 0) {
      return 21600 * 0.75;
    }else{
      return setVel * 0.85;
    }
  }

  public void velocity(double velocity){
    setVel = vel;
    flywheel.set(ControlMode.Velocity, velocity);
  }
  
  @Override
  public void forward() {
    vel = 5 * 2000.0 * 2048.0 / 600.0;
    flywheel.set(ControlMode.Velocity, 20000);
    // setVel = vel;
    SmartDashboard.putString("Shooterstatue","ShooterFoward");
  }

  @Override
  public void stop() {
    setVel = 0;
    flywheel.set(ControlMode.Velocity, 0);
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
    SmartDashboard.putNumber("flyvol", flywheel.getMotorOutputVoltage());
  }
}


