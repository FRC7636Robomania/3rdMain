package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowCon;

public class Shooter extends SubsystemBase {
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonFX flywheel = new TalonFX(PowCon.flywheel);
  private WPI_VictorSPX conveyor = new WPI_VictorSPX(PowCon.conveyor);
  private WPI_VictorSPX widemas = new WPI_VictorSPX(PowCon.wide);
  double setVel = 0;

  public Shooter() {
    MotorFactory.setSensor(flywheel,FeedbackDevice.IntegratedSensor);
    MotorFactory.setSensorPhase(flywheel, false);
    MotorFactory.configPID(flywheel,PowCon.flywheel_kP,PowCon.flywheel_kF,1);
    MotorFactory.configmotorlimit(flywheel,0.001, 1, -1, 1, 30);
    flywheel.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);

  }
    
  public double getflywheelspeed(){
    return flywheel.getSelectedSensorVelocity(0);
  }
  public void flywheelspinup(double vel){
    flywheel.set(ControlMode.Velocity, vel);
    SmartDashboard.putNumber("flyvel", flywheel.getSelectedSensorVelocity());
    SmartDashboard.putNumber("flyvol", flywheel.getMotorOutputVoltage());
    setVel = vel;
  }
      
  public void flywheelstop(){
    flywheel.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putNumber("flyvel", flywheel.getSelectedSensorVelocity());
    SmartDashboard.putNumber("flyvel", flywheel.getSelectedSensorVelocity());
  }
 
  public void fastconveyor(){
    if(getflywheelspeed()>0.95*setVel){
      conveyor.set(ControlMode.PercentOutput,0.8);
    }
    else if(getflywheelspeed()<0.85*setVel){
      conveyor.set(ControlMode.PercentOutput,0);
    }
  } 

  public void longconveyor(){
    if(getflywheelspeed()>0.95*setVel){
      conveyor.set(ControlMode.PercentOutput, 0.8);
    }
    else{
      conveyor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void conveyorstop(){
    conveyor.set(ControlMode.PercentOutput,0.0);
  }
  

  public void widein(){
    widemas.set(-0.5);

  }

  public void wideout(){
    widemas.set(0);
  }
}  


