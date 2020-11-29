package frc.robot.subsystems.shooter;

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

public class Tower extends Spinable {
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonSRX tower = new TalonSRX(PowCon.tower);
  private DigitalInput dot = new DigitalInput(1);
  private String status = "Stop";
  private NetworkTableEntry useLimit;
  private static final int forwardL = 5000, reverseL = -5000;

  public Tower() {
    // tower.configFactoryDefault();
    MotorFactory.setSensor(tower, FeedbackDevice.CTRE_MagEncoder_Absolute);
    tower.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    Shuffleboard.getTab("PositionCombine").addString("Tower", this::getStatus);
    Shuffleboard.getTab("PositionCombine").addNumber("TowerPosition", this::getPosition);
    useLimit = Shuffleboard.getTab("PositionCombine").add("Tower Limit", 1).withWidget(BuiltInWidgets.kNumberSlider)
        .getEntry();
  }

  private void isZero() {
    //need to be modified
  }

  private double getPosition() {
    return tower.getSelectedSensorPosition();
  }

  public double gettowerspeed() {
    return tower.getSelectedSensorVelocity();
  }

  public double getSelectedSensorPosition() {
    return tower.getSelectedSensorPosition();
  }

  public void zero() {
    tower.setSelectedSensorPosition(0);
  }

  @Override
  public void forward() {
    
    if(tower.getSelectedSensorPosition() > forwardL)
      tower.set(ControlMode.PercentOutput, 0);
    else{
      status = "Foward";
      tower.set(ControlMode.PercentOutput, 0.21);
    }
    isZero();
  }

  @Override
  public void reverse() {
    if(tower.getSelectedSensorPosition() < reverseL)
      tower.set(ControlMode.PercentOutput, 0);
    else{
      status = "Reverse";
      tower.set(ControlMode.PercentOutput, -0.21);

    }
    isZero();
  }

  @Override
  public void stop() {
    status = "Stop";
    tower.set(ControlMode.PercentOutput, 0);

  }

  public void aim() {
    double error = Limelight.getTx();
    // SmartDashboard.putNumber("error", error);
    if (Math.abs(error) < 0.15 || Limelight.getTa() < 0) {
      error = 0;
    }
    if (error > 10) {
      tower.set(ControlMode.PercentOutput, -0.007 * error);
    } else {
      tower.set(ControlMode.PercentOutput, -0.02 * error);
    }
    SmartDashboard.putNumber("output", -0.01 * error);

    // if((error > 0 && (tower.getSelectedSensorPosition() > forwardL &&
    // tower.getSelectedSensorPosition() < reverseL))){
    // }
    isZero();
  }

  @Override
  public void periodic() {
    if (!dot.get()) {
      tower.setSelectedSensorPosition(0, 0, 10);
    }
    SmartDashboard.putBoolean("TowerLimit", dot.get());
    SmartDashboard.putNumber("Tower Position", tower.getSelectedSensorPosition());
  }

  @Override
  public String getStatus() {
    return status;
  }
}
