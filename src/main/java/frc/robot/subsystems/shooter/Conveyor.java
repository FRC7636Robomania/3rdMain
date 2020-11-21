package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants.PowCon;

public class Conveyor extends Spinable{
  private WPI_VictorSPX conveyor = new WPI_VictorSPX(PowCon.conveyor);
  private Shooter       shooter;
  private String status = "Stop";

  public Conveyor(Shooter shooter) {
    this.shooter = shooter;
    Shuffleboard.getTab("Statue").addString("Conveyor", this::getStatus);
    conveyor.setInverted(true);
    
  }

  public void forceSpin(){
    conveyor.set(ControlMode.PercentOutput, 0.8);
    status = "forceSpin";  
  }
  
  @Override
  public void forward() {
    if(shooter.getflywheelspeed() >= 12500 * 0.8){
      conveyor.set(ControlMode.PercentOutput, 0.85);
      status = "Forward";
    }else{
      status = "flywheelTooSlowly!!";
      conveyor.set(ControlMode.PercentOutput, 0 );
    }
  }

  @Override
  public void stop() {
    conveyor.set(ControlMode.PercentOutput, 0);
    status = "ConveyorStop";
  }

  @Override
  public void reverse() {
    if(shooter.getflywheelspeed() >= PowCon.flywheelVelocity * 0.8){
      conveyor.set(ControlMode.PercentOutput, -0.85);
      status = "ConveyorReverse";
    }else{
      status = "flywheelTooSlowly!!";

    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  @Override
  public String getStatus() {
    return status;
  }
}


