package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowCon;

public class Tower extends SubsystemBase {
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
      40, 50, 1);
  private TalonSRX tower = new TalonSRX(PowCon.tower);
  // private DigitalInput button = new DigitalInput(3);

  public Tower() {
    MotorFactory.setSensor(tower, FeedbackDevice.CTRE_MagEncoder_Relative);
    tower.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
  }

  public double gettowerspeed() {
    return tower.getSelectedSensorVelocity();
  }

  public void towerspinup1() {
    tower.set(ControlMode.PercentOutput, -0.5);
  }
  public void towerspinup2() {
    tower.set(ControlMode.PercentOutput, 0.5);
  }

  public void towerstop() {
    tower.set(ControlMode.PercentOutput, 0);
  }
  /**
   * Go back method, will be called when need. 
   */
  public void goBack(){

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("towerp", tower.getSelectedSensorPosition());
    // This method will be called once per scheduler run
  }
	public double getSelectedSensorPosition() {
		return tower.getSelectedSensorPosition();
	}

	public void set(ControlMode percentoutput, double d) {
	}
    }
