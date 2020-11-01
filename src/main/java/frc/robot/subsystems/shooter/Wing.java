package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.PowCon;

public class Wing extends Spinable{
  private final WPI_VictorSPX wideleft = new WPI_VictorSPX(PowCon.wideleft);
  private final WPI_VictorSPX wideright = new WPI_VictorSPX(PowCon.wideright);
  
  private final WPI_VictorSPX middle = new WPI_VictorSPX(87);

  public Wing(){
    MotorFactory.setFollower(wideleft, wideright);
    MotorFactory.setInvert(wideright, InvertType.OpposeMaster);
    MotorFactory.setFollower(wideleft, middle);
  }

  @Override
  public void forward() {
    SmartDashboard.putString("Wingstatue","WingForward");
    wideleft.set(-0.3);
  }

  @Override
  public void stop() {
    SmartDashboard.putString("Wingstatue","WingStop");
    wideleft.set(0);
  }

  @Override
  public void reverse() {
    SmartDashboard.putString("Wingstatue","WingReverse");
    wideleft.set(0.3);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
  

