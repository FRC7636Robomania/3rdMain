package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.PowCon;

public class Conveyor extends Spinable{
  private WPI_VictorSPX conveyor = new WPI_VictorSPX(PowCon.conveyor);
  private Shooter       shooter;

  public Conveyor(Shooter shooter) {
    this.shooter = shooter;
  }

  public void forceSpin(){
    conveyor.set(ControlMode.PercentOutput, -0.7);
  }
  
  @Override
  public void forward() {
    if(shooter.getflywheelspeed() >= 12000 * 0.8){
      conveyor.set(ControlMode.PercentOutput, 0.7);
      SmartDashboard.putString("Conveyorstatue","ConveyorForward");  
    }else{
      SmartDashboard.putString("Conveyorstatue","flywheelTooSlowly!!");  

    }
  }

  @Override
  public void stop() {
    conveyor.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putString("Conveyorstatue","ConveyorStop");
  }

  @Override
  public void reverse() {
    if(shooter.getflywheelspeed() >= 12000 * 0.8){
      conveyor.set(ControlMode.PercentOutput, -0.7);
      SmartDashboard.putString("Conveyorstatue","ConveyorReverse");  
    }else{
      SmartDashboard.putString("Conveyorstatue","flywheelTooSlowly!!");  

    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}


