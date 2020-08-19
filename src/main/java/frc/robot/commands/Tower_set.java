package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Tower;


public class Tower_set extends CommandBase {
  private Tower m_Tower;
  
  public Tower_set(Tower tower) {
    m_Tower = tower ;
     addRequirements(m_Tower);
     // Use addRequirements() here to declare subsystem dependencies.
   }
   @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Tower.towerspinup1();
    SmartDashboard.putString("Towerstatus", "towerSpin");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Tower.towerstop();
    SmartDashboard.putString("Towerstatus", "towerOFF");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
