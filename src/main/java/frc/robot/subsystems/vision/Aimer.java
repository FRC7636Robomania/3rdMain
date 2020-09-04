/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.*;



public class Aimer extends SubsystemBase {
    double x,y,area;
    private double dist = 0;
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-sexyboy");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
  /**
   * Creates a new Aimer.
   */
  public Aimer() {

  }

  @Override
  public void periodic() {
    
    area = ta.getDouble(0.0);
    if (area>0){
      x = tx.getDouble(0.0);
      y = ty.getDouble(0.0);
      dist =  (Vision.target_high-Vision.limelight_high)/Math.tan(Math.toRadians(41+y));
    }
    SmartDashboard.putNumber("Limelight_dist", dist);
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }
}
