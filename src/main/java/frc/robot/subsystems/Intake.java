/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.subsystems.shooter.Spinable;

/**
 * Add your docs here.
 */
public class Intake extends Spinable{
    private final WPI_TalonSRX intake = new WPI_TalonSRX(10);
    
    public Intake(){
        intake.configFactoryDefault();
    }
    @Override
    public void forward() {
        intake.set(ControlMode.PercentOutput, 0.7);
    }

    @Override
    public void stop() {
        intake.set(ControlMode.PercentOutput, 0);

    }

    @Override
    public void reverse() {
        intake.set(ControlMode.PercentOutput, -0.7);
    }
}
