package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants.PowCon;

public class  Shooter extends Spinable{
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration 
            = new SupplyCurrentLimitConfiguration(true, 35, 40, 1);
  private TalonFX flywheel = new TalonFX(PowCon.flywheel);
  private TalonFX flywheelS = new TalonFX(PowCon.flywheel2);
  private String status = "Stop";
  double setVel = 0;

  public Shooter() {
    MotorFactory.setSensor(flywheel,FeedbackDevice.IntegratedSensor);
    MotorFactory.configPF(flywheel,PowCon.flywheel_kP,PowCon.flywheel_kF,0);
    flywheel.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    flywheel.config_kD(0, PowCon.flywheel_kD);

    flywheel.configClosedloopRamp(0.5, 10);
    flywheel.setInverted(false);
    flywheelS.follow(flywheel);
    flywheelS.setInverted(InvertType.OpposeMaster);
    flywheelS.setStatusFramePeriod(255, 10);
    // flywheelS.setInverted(true);
    Shuffleboard.getTab("Statue").addString("Shooter", this::getStatus);
  }
  @Override
  public String getStatus(){
    return status;
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
    setVel = velocity;
    flywheel.set(ControlMode.Velocity, velocity);
    
  }
  public void percentOutput(double value){

  }
  
  @Override
  public void forward() {
    setVel = PowCon.flywheelVelocity;
    flywheel.set(ControlMode.Velocity, setVel);
    // flywheelS.set(ControlMode.Velocity, setVel);
    // flywheel.set(ControlMode.PercentOutput,0.8);
    // flywheelS.set(ControlMode.PercentOutput, 0.8);
    status = "Foward";
  }

  @Override
  public void stop() {
    setVel = 0;
    flywheel.set(ControlMode.Velocity, setVel);
    // flywheelS.set(ControlMode.Velocity, setVel);
    // flywheel.set(ControlMode.PercentOutput,0);
    // flywheelS.set(ControlMode.PercentOutput, 0.);

    status = "Stop";

  }

  @Override
  public void reverse() {
    status = "Don't do that please";
    System.out.println("Don't do that please");
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("flyvel", flywheel.getSelectedSensorVelocity());
  }
}


