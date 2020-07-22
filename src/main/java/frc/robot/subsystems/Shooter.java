package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowCon;

public class Shooter extends SubsystemBase {
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 40, 50, 1);
  private TalonFX flywheel = new TalonFX(PowCon.flywheel);
  private WPI_VictorSPX conveyor = new WPI_VictorSPX(PowCon.conveyor);
  private WPI_VictorSPX wideleft = new WPI_VictorSPX(PowCon.wideleft);
  private WPI_VictorSPX wideright = new WPI_VictorSPX(PowCon.wideright);
  double setVel = 0;

  public Shooter() {
    MotorFactory.setSensor(flywheel,FeedbackDevice.IntegratedSensor);
    MotorFactory.setSensor(conveyor,FeedbackDevice.CTRE_MagEncoder_Relative);
    MotorFactory.configPID(flywheel,PowCon.flywheel_kP,PowCon.flywheel_kF,0);
    //MotorFactory.configmotorlimit(flywheel,0.001, 1, -1, 1, 30);
    flywheel.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    MotorFactory.setFollower(wideleft, wideright);
    
  }

  public double getflywheelspeed(){
    return flywheel.getSelectedSensorVelocity();
  }
  public void flywheelspinup(){
    double vel = 1* 2000.0 * 2048.0 / 600.0;
    flywheel.set(ControlMode.Velocity,vel);
    setVel = vel;
  }
      
  public void flywheelstop(){
    flywheel.set(ControlMode.PercentOutput, 0);
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
    wideleft.set(-0.5);

  }

  public void wideout(){
    wideleft.set(0);
  }  
@Override
  public void periodic() {
    SmartDashboard.putNumber("flyvel", flywheel.getSelectedSensorVelocity());
    SmartDashboard.putNumber("flyvol", flywheel.getMotorOutputVoltage());
    // This method will be called once per scheduler run
  }
}


