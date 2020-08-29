/*這裡是撰寫馬頭齒條的地方*/
package frc.robot.subsystems.shooter;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.motor.MotorFactory;


import frc.robot.Constants.PowCon;

public class Rack extends Spinable{
    private WPI_TalonSRX rack =new WPI_TalonSRX(PowCon.rack);

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

}