/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.*;
/**
 * Add your docs here.
 */
public class Limelight {
    private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-sexyboy");

    public static double getTx() {
        return table.getEntry("tx").getDouble(0.0);
    }

    public static double getDistance(){
        double y = table.getEntry("ty").getDouble(0.0);
        return (Vision.target_high-Vision.limelight_high)
                                / Math.tan(Math.toRadians(41+y));
    }

}
