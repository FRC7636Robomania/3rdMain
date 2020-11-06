/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.shooter.Spinable;

public class Intake extends Spinable{
    private final VictorSPX intake = new VictorSPX(5);
    private String status = "Stop";
    public Intake(){
        intake.configFactoryDefault();
        Shuffleboard.getTab("Statue").addString("Intake", this::getStatus);

    }
    @Override
    public void forward() {
        intake.set(ControlMode.PercentOutput, 0.95);
        status = "Forward";
    }

    @Override
    public void stop() {
        intake.set(ControlMode.PercentOutput, 0);
        status = "Stop";

    }

    @Override
    public void reverse() {
        intake.set(ControlMode.PercentOutput, -0.95);
        status = "Reverse";
    }

    @Override
    public String getStatus() {
        return status;
    }
}
