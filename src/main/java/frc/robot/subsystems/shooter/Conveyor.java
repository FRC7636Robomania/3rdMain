package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowCon;

public class Conveyor extends Spinable{
  private WPI_VictorSPX conveyor = new WPI_VictorSPX(PowCon.conveyor);
  double setVel = 0;
  double vel;

  public Conveyor() {
  }
  
  @Override
  public void forward() {
    conveyor.set(ControlMode.PercentOutput, 0.7);

  }

  @Override
  public void stop() {
    conveyor.set(ControlMode.PercentOutput, 0);

  }

  @Override
  public void reverse() {
    conveyor.set(ControlMode.PercentOutput, -0.7);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}


