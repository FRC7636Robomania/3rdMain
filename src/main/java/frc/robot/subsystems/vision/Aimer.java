/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
    
    SmartDashboard.putNumber("Limelight_dist", Limelight.getDistance(ta.getDouble(0.0)));
    SmartDashboard.putNumber("LimelightX", Limelight.getTx());
    SmartDashboard.putNumber("LimelightY", Limelight.getTy());
    SmartDashboard.putNumber("LimelightArea", area);
  }
}
