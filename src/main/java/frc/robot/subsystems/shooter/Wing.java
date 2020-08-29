package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.motor.MotorFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowCon;

public class Wing extends Spinable{
  private final WPI_VictorSPX wideleft = new WPI_VictorSPX(PowCon.wideleft);
  private final WPI_VictorSPX wideright = new WPI_VictorSPX(PowCon.wideright);
  
  public Wing(){
    MotorFactory.setFollower(wideleft, wideright);
    MotorFactory.setInvert(wideright, InvertType.FollowMaster);
  }

  @Override
  public void forward() {
    SmartDashboard.putString("Wingstatue","WingForward");
    wideleft.set(-0.5);
  }

  @Override
  public void stop() {
    SmartDashboard.putString("Wingstatue","WingStop");
    wideleft.set(0);
  }

  @Override
  public void reverse() {
    SmartDashboard.putString("Wingstatue","WingReverse");
    wideleft.set(0.1);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
  

