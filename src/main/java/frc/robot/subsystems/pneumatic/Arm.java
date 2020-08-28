/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pneumatic;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.Pne;

public class Arm extends Pneumatic {
    private static Solenoid s1 = new Solenoid(Pne.Solenoid);
    private static DoubleSolenoid ds1 = new DoubleSolenoid(Pne.ArmDS_2,Pne.ArmDS_1);

    public Arm(){
    }
    public void Pneumatic_ON(){
    }

    public void Solenoid1_ON(){
        s1.set(true);
    }

    public void Solenoid1_OFF(){
        s1.set(false);
    }

    public void ArmDS_ON(){
        ds1.set(DoubleSolenoid.Value.kForward);
    }

    public void ArmDS_OFF(){
        ds1.set(DoubleSolenoid.Value.kReverse);
    }
}
