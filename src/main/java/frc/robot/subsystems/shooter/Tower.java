package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.motor.MotorFactory;
import frc.robot.subsystems.vision.Limelight;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.PowCon;
public class Tower extends Spinable{
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonSRX tower = new TalonSRX(PowCon.tower);
  DigitalInput dot = new DigitalInput(0);

  private static final int forwardL = -2900, reverseL = 1500;
  public Tower() {
    MotorFactory.setSensor(tower, FeedbackDevice.CTRE_MagEncoder_Absolute);
    tower.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
  }
  private void isZero(){
    if(dot.get() && Math.abs(position()) < 1000){
      tower.setSelectedSensorPosition(0);
    }
  }
  private double position(){
    return tower.getSelectedSensorPosition();
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
    if(tower.getSelectedSensorPosition() < forwardL)
      tower.set(ControlMode.PercentOutput, 0);
    else 
      tower.set(ControlMode.PercentOutput, 0.5);
    isZero();
  }

  @Override
  public void stop() {
    SmartDashboard.putString("Towerstatue","TowerStop");
    tower.set(ControlMode.PercentOutput, 0);
    
  }

  public void aim(){
    double error = Limelight.getTx();
    SmartDashboard.putNumber("error", error);
    if(Math.abs(error) < 0.15){
      error = 0;
    }
    tower.set(ControlMode.PercentOutput, 0.08 * error);
    isZero();
  }
  @Override
  public void reverse() {
    SmartDashboard.putString("Towerstatue","TowerReverse");
    if(tower.getSelectedSensorPosition() > reverseL)
      tower.set(ControlMode.PercentOutput, 0);
    else 
      tower.set(ControlMode.PercentOutput, -0.5);
    isZero();
  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("towerPosition", tower.getSelectedSensorPosition());
  }
}
