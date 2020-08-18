/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ControlDrivetrain extends DrivetrainBase {
    /**
     * Create your ControlDrivetrain here
     */
    public ControlDrivetrain(){

    }

    public void drive(double speed, double turn){
        // final double speed = joy1.getRawAxis(0)*0.5;
        // final double turn = -joy1.getRawAxis(1)*0.5;  
        double slider = -1.0 * joy1.getRawAxis(3); /* right-side Y for Xbox360Gamepad */
        
        leftMas.set(ControlMode.PercentOutput,speed+turn);
        rightMas.set(ControlMode.PercentOutput,speed-turn);
        SmartDashboard.putNumber("speed",speed);
        SmartDashboard.putNumber("turn",turn);
    }

    public void curvatureDrive(){

    }
    
    public void tankDrive(){

    }
}
