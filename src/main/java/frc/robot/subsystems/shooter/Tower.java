package frc.robot.subsystems.shooter;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.motor.MotorFactory;
import frc.robot.subsystems.vision.Limelight;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.PowCon;
public class Tower extends Spinable{
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonSRX tower = new TalonSRX(PowCon.tower);
  private DigitalInput dot = new DigitalInput(0);
  private String status = "Stop";
  private NetworkTableEntry useLimit;
  private static final int forwardL = -2900, reverseL = 2000;
  public Tower() {
    tower.configFactoryDefault();
    MotorFactory.setSensor(tower, FeedbackDevice.CTRE_MagEncoder_Absolute);
    tower.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    Shuffleboard.getTab("Statue").addString("Tower", this::getStatus);
    Shuffleboard.getTab("Statue").addNumber("TowerPosition", this::getPosition);
    useLimit = Shuffleboard.getTab("Adjusting")
    .add("Tower Limit", 1)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1)) // specify widget properties here
    .getEntry();
  }
  private void isZero(){
    if(useLimit.getDouble(-1) > 0){
      if(dot.get() && Math.abs(getPosition()) < 1000){
        tower.setSelectedSensorPosition(0);
      }
    }
  }
  private double getPosition(){
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
    status = "Foward";
    if(tower.getSelectedSensorPosition() < forwardL)
      tower.set(ControlMode.PercentOutput, 0);
    else 
      tower.set(ControlMode.PercentOutput, 0.2);
    isZero();
  }
  @Override
  public void reverse() {
    status = "Reverse";
    if(tower.getSelectedSensorPosition() > reverseL)
      tower.set(ControlMode.PercentOutput, 0);
    else 
      tower.set(ControlMode.PercentOutput, -0.2);
    isZero();
  }
  @Override
  public void stop() {
    status = "Stop";
    tower.set(ControlMode.PercentOutput, 0);
    
  }

  public void aim(){
    double error = Limelight.getTx();
    SmartDashboard.putNumber("error", error);
    if(Math.abs(error) < 0.15){
      error = 0;
    }
    // if((error > 0 && tower.getSelectedSensorPosition() < forwardL) 
    //   || (error < 0 && tower.getSelectedSensorPosition() > reverseL)){
      tower.set(ControlMode.PercentOutput, 0.08 * error);
    // }
    
    isZero();
  }
  @Override
  public void periodic() {
  }

  @Override
  public String getStatus() {
    return status;
  }
}
