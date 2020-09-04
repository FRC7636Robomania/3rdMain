package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.PowCon;

public class Tower extends Spinable{
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonSRX tower = new TalonSRX(PowCon.tower);

  public Tower() {
    tower.configFactoryDefault();
    MotorFactory.setSensor(tower, FeedbackDevice.CTRE_MagEncoder_Relative);
    tower.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
  }

  public double gettowerspeed() {
    return tower.getSelectedSensorVelocity();
  }

	public double getSelectedSensorPosition() {
		return tower.getSelectedSensorPosition();
  }
  
  public void zero(){
    tower.setSelectedSensorPosition(0);
  }

  @Override
  public void forward() {
    SmartDashboard.putString("Towerstatue","TowerFoward");
    if(tower.getSelectedSensorPosition() < -2900)
      tower.set(ControlMode.PercentOutput, 0);
    else 
      tower.set(ControlMode.PercentOutput, 0.5);
  }

  @Override
  public void stop() {
    SmartDashboard.putString("Towerstatue","TowerStop");
    tower.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void reverse() {
    SmartDashboard.putString("Towerstatue","TowerReverse");
    if(tower.getSelectedSensorPosition() > 1500)
      tower.set(ControlMode.PercentOutput, 0);
    else 
      tower.set(ControlMode.PercentOutput, -0.5);
  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("towerPosition", tower.getSelectedSensorPosition());
    // This method will be called once per scheduler run
  }
    }
