/*這裡是撰寫馬頭齒條的地方*/
package frc.robot.subsystems.shooter;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.motor.MotorFactory;


import frc.robot.Constants.PowCon;

public class Rack extends Spinable{
    private WPI_TalonSRX rack =new WPI_TalonSRX(PowCon.rack);
    private double unit, dist; //dist hasn't been update.
    public Rack(){
        MotorFactory.setSensor(rack, FeedbackDevice.CTRE_MagEncoder_Relative);
    }
    
    @Override
    public void forward() {
        rack.set(ControlMode.PercentOutput, 0.4);
        SmartDashboard.putString("Rackstatue","Rackfoward");
    }

    @Override
    public void stop() {
        rack.set(ControlMode.PercentOutput, 0);
        SmartDashboard.putString("Rackstatue","Rackstop");
    }

    @Override
    public void reverse() {
        rack.set(ControlMode.PercentOutput, -0.4);        
        SmartDashboard.putString("Rackstatue","Rackreverse");
    }

    public void aimunit(){
        rack.set(ControlMode.MotionMagic,-unit);  
      }
      public boolean isAimfinish(){
        SmartDashboard.putNumber("err", rack.getClosedLoopError(0));
        return Math.abs(rack.getClosedLoopError(0))<50;
      }
    
      public void resetAimer(){
        rack.setSelectedSensorPosition(0, 12000,10);
      }
      public void Uplimit(){
        rack.setSelectedSensorPosition(-10000);
      }
      public void Downlimit(){
        rack.setSelectedSensorPosition(0);
      }
    
      public double getunit(){
        if (dist>600){
          unit= 11200+100*(dist-600)/50;
        }else if(dist>500){
          unit=11000+200*(dist-500)/50;
        }
        else if(dist>450){
          unit =8600-1100*(dist-450)/50;
        }
        else if(dist>400){
          unit=8450+150*(dist-400)/50;
        }
        else if(dist>350){
          unit =8000+450*(dist-350)/50;
        }
        else if(dist>300){
          unit = 8050-50*(dist-300)/50;
        }
        else if(dist>250){
          unit = 7300+750*(dist-250)/50;
        }
        else if(dist>200){
          unit = 6100+1200*(dist-200)/50;
        }
        else if(dist>150){
          unit = 5200+900*(dist-150)/50;
        }
        else if(dist>100){
          unit=941+4260*(dist-100)/50;
        }
        else{
          unit =0;
        }
        return unit;
      }

}