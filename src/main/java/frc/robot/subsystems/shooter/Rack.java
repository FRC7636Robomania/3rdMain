/*這裡是撰寫馬頭齒條的地方*/
package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.motor.MotorFactory;
import frc.robot.subsystems.vision.Limelight;
import frc.robot.Constants.PowCon;

public class Rack extends Spinable{
    private WPI_TalonSRX rack =new WPI_TalonSRX(PowCon.rack);
    public Rack(){
        MotorFactory.setSensor(rack, FeedbackDevice.CTRE_MagEncoder_Relative);
        MotorFactory.configPF(rack, 0.01, 0.1, 0);
        rack.configMotionAcceleration(1600, 10);
        rack.configMotionCruiseVelocity(1500,10);
        MotorFactory.setInvert(rack, false);
        MotorFactory.setSensorPhase(rack, false);

    }
    public void zero(){
        rack.setSelectedSensorPosition(0);
    }
    public void aim(){
        //rack_aim.getRack(Limelight.getDistance()
        // rack.set(ControlMode.MotionMagic, rack_aim.getRack(Limelight.getDistance())
        double unit = rack_aim.getRack(Limelight.getDistance());
        double err = unit - rack.getSelectedSensorPosition();
        rack.set(ControlMode.PercentOutput, -0.0001 * err);
    }
    @Override
    public void forward() {
        rack.set(ControlMode.PercentOutput, 0.2);
        SmartDashboard.putString("Rackstatue","Rackfoward");
    }

    @Override
    public void stop() {
        rack.set(ControlMode.PercentOutput, 0);
        SmartDashboard.putString("Rackstatue","Rackstop");
    }

    @Override
    public void reverse() {
        rack.set(ControlMode.PercentOutput, -0.2);        
        SmartDashboard.putString("Rackstatue","Rackreverse");

    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("RackPosition", rack.getSelectedSensorPosition());
        SmartDashboard.putNumber("unit", rack_aim.getRack(Limelight.getDistance()));
        // aim();
    }

    public static double aim(double Dist){
        double unit;
        if (Dist>600){
            unit= 11200+100*(Dist-600)/50;
        }else if(Dist>500){
            unit=11000+200*(Dist-500)/50;
        }
        else if(Dist>450){
            unit =8600-1100*(Dist-450)/50;
        }
        else if(Dist>400){
            unit=8450+150*(Dist-400)/50;
        }
        else if(Dist>350){
            unit =8000+450*(Dist-350)/50;
        }
        else if(Dist>300){
            unit = 8050-50*(Dist-300)/50;
        }
        else if(Dist>250){
            unit = 7300+750*(Dist-250)/50;
        }
        else if(Dist>200){
            unit = 6100+1200*(Dist-200)/50;
        }
        else if(Dist>150){
            unit = 5200+900*(Dist-150)/50;
        }
        else if(Dist>100){
            unit=941+4260*(Dist-100)/50;
        }
        else{
            unit =0;
        }
        return unit;
    }

}