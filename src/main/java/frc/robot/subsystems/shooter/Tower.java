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
  // private DigitalInput button = new DigitalInput(3);

  public Tower() {
    tower.configFactoryDefault();
    MotorFactory.setSensor(tower, FeedbackDevice.CTRE_MagEncoder_Relative);
    tower.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);

    tower.configForwardSoftLimitThreshold(3000);
    tower.configReverseSoftLimitThreshold(-3000);

    tower.configForwardSoftLimitEnable(true);
    tower.configReverseSoftLimitEnable(true);
  }

  public double gettowerspeed() {
    return tower.getSelectedSensorVelocity();
  }

	public double getSelectedSensorPosition() {
		return tower.getSelectedSensorPosition();
  }
  
  // public void zero(){
  //   tower.setSelectedSensorPosition(0);
  // }

  @Override
  public void forward() {
    tower.set(ControlMode.PercentOutput, 0.5);
  }

  @Override
  public void stop() {
    tower.set(ControlMode.PercentOutput, 0);

  }

  @Override
  public void reverse() {
    tower.set(ControlMode.PercentOutput, -0.5);

  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("towerPosition", tower.getSelectedSensorPosition());
    // This method will be called once per scheduler run
  }
    }
