/*這裡是撰寫馬頭齒條的地方*/
package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.motor.MotorFactory;
import frc.robot.subsystems.vision.Limelight;
import frc.robot.Constants.PowCon;

public class Rack extends Spinable {
    private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true,
            8, 10, 1);

    private WPI_TalonSRX rack = new WPI_TalonSRX(PowCon.rack);
    private String status = "Stop";
    private int lastPosition = 0;

    // private double position = 0;
    public Rack() {
        lastPosition = rack.getSelectedSensorPosition();
        rack.configFactoryDefault();
        MotorFactory.setSensor(rack, FeedbackDevice.CTRE_MagEncoder_Absolute);
        MotorFactory.configPF(rack, 0.01, 0.1, 0);
        rack.setSelectedSensorPosition(lastPosition);

        rack.configMotionAcceleration(1600, 10);
        rack.configMotionCruiseVelocity(1500, 10);

        MotorFactory.setInvert(rack, false);
        MotorFactory.setSensorPhase(rack, false);
        // use which limitswitch pin, use which port connect to encoder
        // rack.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
        // LimitSwitchNormal.NormallyClosed);
        // rack.configClearPositionOnLimitR(true,10);

        rack.configSupplyCurrentLimit(supplyCurrentLimitConfiguration, 10);

        Shuffleboard.getTab("PositionCombine").addString("Rack", this::getStatus);
        Shuffleboard.getTab("PositionCombine").addNumber("RackPosition", this::getPosition);
    }

    public void zero() {
        rack.setSelectedSensorPosition(0);
    }

    public void aim(double position) {
        double error = position - rack.getSelectedSensorPosition();
        rack.set(ControlMode.PercentOutput, 0.0002 * error);
        SmartDashboard.putNumber("rackError", error);
    }

    public void aim() {
        double unit = Rack.unit(Limelight.getDistance());
        double err = unit - rack.getSelectedSensorPosition();
        rack.set(ControlMode.PercentOutput, 0.0002 * err);
    }

    public void isZero() {
        // if(!rack.getSensorCollection().isFwdLimitSwitchClosed()){
        // zero();
        // }
    }

    public void initial() {
        rack.overrideLimitSwitchesEnable(false);
        double[] history = new double[10];
        int count = 0;
        while (true) {
            double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
            rack.set(ControlMode.PercentOutput, -0.18);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            history[count] = rack.getSelectedSensorPosition();
            count++;
            // 超出十個就從最舊的開始覆蓋
            if (count >= 10) {
                count = 0;
            }
            // 找出最大最小
            for (int i = 0; i < history.length; i++) {
                if (history[i] > max)
                    max = history[i];
                if (history[i] < min)
                    min = history[i];
            }
            SmartDashboard.putNumber("temp max", max);
            SmartDashboard.putNumber("temp min", min);
            // 判斷是否有改變
            if ((max - min) < 50) {
                break;
            }
        }
        rack.set(ControlMode.PercentOutput, 0);
        rack.overrideLimitSwitchesEnable(true);
        // 會出來代表已經到最底，並且限位被按下
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(rack.isRevLimitSwitchClosed() == 1){
            SmartDashboard.putString("limitType", "NO");
            rack.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        }else if(rack.isRevLimitSwitchClosed() == 0){
            SmartDashboard.putString("limitType", "NC");
            rack.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        }
    }
    @Override
    public void forward() {
        rack.set(ControlMode.PercentOutput, 0.2);
        status = "Foward";
        isZero();
    }

    @Override
    public void stop() {
        rack.set(ControlMode.PercentOutput, 0);
        status = "Stop";
    }

    @Override
    public void reverse() {
        rack.set(ControlMode.PercentOutput, -0.2);
        status ="Reverse";
        isZero();   
    }
    @Override
    public String getStatus() {
        return status;
    }
    public double getPosition(){
        return rack.getSelectedSensorPosition();
    }
    public boolean getLimit(){
        // normorlly return false
        return !rack.getSensorCollection().isFwdLimitSwitchClosed();
    }
    @Override
    public void periodic() {
        // position = rack.getSelectedSensorPosition();
        // SmartDashboard.putNumber("unit", Rack.aim(Limelight.getDistance()));
        //不論常開/常閉設置如何，關閉的傳感器在所有情況下均返回true，而打開的傳感器在所有情況下均返回false。這樣可以確保函數名稱沒有歧義。
        SmartDashboard.putBoolean("limit", rack.getSensorCollection().isRevLimitSwitchClosed());
        SmartDashboard.putNumber("Rack Position", rack.getSelectedSensorPosition());
        SmartDashboard.putString("rackStatue", status);
        // aim();
    }

    public static double unit(double Dist){
        double unit;
        if(Dist>500){
            unit= -10150 - 275 * (Dist - 500) / 50;
        }
        else if(Dist>450){
            unit = -9875 + 1345 * (Dist - 450) / 50;
        }
        else if(Dist>400){
            unit= -11220 + 1345 * (Dist - 400) / 50;
        }
        else if(Dist>350){
            unit =-10090 - 1130 * (Dist - 350) / 50;
        }
        else if(Dist>300){
            unit = -9045 - 1045 *(Dist-300)/50;
        }
        else if(Dist>250){
            unit = -8890 - 155 * (Dist - 250) / 50;
        }
        else if(Dist>200){
            unit = -7790 - 900 * (Dist - 200) / 50;
        }
        else if(Dist>150){
            unit = -6601 - 1189 / 50 * (Dist - 150);
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