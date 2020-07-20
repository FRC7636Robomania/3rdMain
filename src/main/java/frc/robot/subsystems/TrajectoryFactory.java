/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TrajectoryFactory extends SubsystemBase {
  private static Trajectory trajectory = null;
  /**
   * Creates a new TrajectoryFactory.
   */
  public TrajectoryFactory() {

  }
  public static Trajectory getTrajectory(String mapPath){
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(mapPath);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + mapPath, ex.getStackTrace());
    }
    return trajectory;
  }
  public static void initPose(Drivetrain drivetrain){
    if(trajectory != null){
      drivetrain.setOdmetry(trajectory.getInitialPose());
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
