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

public class Aimer extends SubsystemBase {
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
    
    SmartDashboard.putNumber("Limelight_dist", Limelight.getDistance());
    SmartDashboard.putNumber("LimelightX", Limelight.getTx());
    SmartDashboard.putNumber("LimelightY", Limelight.getTy());
    SmartDashboard.putNumber("LimelightArea", Limelight.getTa());


  }
}
