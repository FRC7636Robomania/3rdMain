package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowCon;

public class Shooter extends SubsystemBase {
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonFX flywheel = new TalonFX(PowCon.flywheel);
  double setVel = 0;

  public Shooter() {
    flywheel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,10);
    flywheel.configFactoryDefault();
    flywheel.configNeutralDeadband(0.001);
    flywheel.configPeakOutputForward(1,30);
    flywheel.configPeakOutputReverse(-1,30);
    flywheel.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);

  }
    
  public double getflywheelspeed(){
    return flywheel.getSelectedSensorVelocity(0);
  }
  public void flywheelspinup(double vel){
    flywheel.set(ControlMode.Velocity, vel);
    setVel = vel;
  }
      
  public void flywheelstop(){
    flywheel.set(ControlMode.PercentOutput, 0);
  }
}  

