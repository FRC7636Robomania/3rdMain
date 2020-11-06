package frc.robot.subsystems.pneumatic;

import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatic extends SubsystemBase {
  protected static Compressor c =new Compressor();

  public Pneumatic(){
    Pneumatic_ON();
  }

  public void Pneumatic_ON(){
    c.setClosedLoopControl(false);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
