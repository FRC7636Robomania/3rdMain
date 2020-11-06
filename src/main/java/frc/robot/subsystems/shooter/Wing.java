package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants.PowCon;

public class Wing extends Spinable{
  private final WPI_VictorSPX wing = new WPI_VictorSPX(PowCon.wingRight);
  private final WPI_VictorSPX middle = new WPI_VictorSPX(PowCon.wingMiddle);
  private String status = "Stop";
  public Wing(){
    MotorFactory.setFollower(wing,middle);
    // MotorFactory.setInvert(middle, InvertType.OpposeMaster);
    Shuffleboard.getTab("Statue").addString("Wing", this::getStatus);
  }
  public String getStatus(){
    return status;
  }
  @Override
  public void forward() {
    status = "Forward";
    wing.set(0.7);
  }

  @Override
  public void stop() {
    status = "Stop";
    wing.set(0);
  }

  @Override
  public void reverse() {
    status = "Reverse";
    wing.set(-0.7);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
  

