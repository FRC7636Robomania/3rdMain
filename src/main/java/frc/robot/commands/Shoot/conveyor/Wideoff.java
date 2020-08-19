package frc.robot.commands.Shoot.conveyor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Wideoff  extends CommandBase {
    private Shooter m_Shooter;
    private int i;
  
    /**
     * Creates a new Shoot.
     */
    public Wideoff (Shooter shooter) {
     m_Shooter = shooter;
      addRequirements(m_Shooter);
      // Use addRequirements() here to declare subsystem dependencies.
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_Shooter.wideout();
      SmartDashboard.putString("Widestatus", "WideStop");
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }
  