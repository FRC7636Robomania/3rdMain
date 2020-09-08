/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class rack_aim extends SubsystemBase {

  public static double getRack(double Dist) {
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
    return -unit;

  }
}
