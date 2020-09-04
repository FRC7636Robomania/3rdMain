/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.chassis.trajectory;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
/**
 * 一個幫助實現路徑規劃的工廠類別
 */
public class TrajectoryFactory {

  private static Trajectory trajectory = null;
  /**
   * 把檔案路徑轉換成Trajectory物件的方法
   * @param mapPath
   * @return
   */
  public static Trajectory getTrajectory(String mapPath){
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(mapPath);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
      SmartDashboard.putNumber("TotalTime", trajectory.getTotalTimeSeconds());
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + mapPath, ex.getStackTrace());
    }
    return trajectory;
  }
  /**
   * 把當前位置設成軌跡圖上起始位置的方法
   * @param drivetrain
   */
  public static void initPose(TrajectorySystem drivetrain){
    if(trajectory != null){
      TrajectoryDrivetrain.resetSensor();
      drivetrain.setOdmetry(trajectory.getInitialPose());
    }
  }
}
