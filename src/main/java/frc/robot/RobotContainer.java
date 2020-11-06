/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;

import javax.annotation.meta.When;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.Button;
import frc.robot.commands.Shoot.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.chassis.ControlDrivetrain;
import frc.robot.subsystems.chassis.trajectory.TrajectoryDrivetrain;
import frc.robot.subsystems.pneumatic.*;
import frc.robot.subsystems.shooter.*;
import frc.robot.subsystems.vision.Limelight;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Shooter           m_shooter                       = new Shooter();
  private final Tower             m_tower                         = new Tower();
  private final Conveyor          m_conveyor                      = new Conveyor(m_shooter);
  private final Arm               m_arm                           = new Arm();
  private final Intake            m_intake                        = new Intake();
  private final Wing              m_wing                          = new Wing();
  private final Rack              m_rack                          = new Rack();
  private final Pneumatic         m_Pneumatic                     = new Pneumatic();
  private final Joystick          joystick                        = new Joystick(0);
  private final Joystick          driverStation                   = new Joystick(1);
  private final ControlDrivetrain    controlDrivetrain            = new ControlDrivetrain();
  private final TrajectoryDrivetrain trajectoryDrivetrain         = new TrajectoryDrivetrain();
  private final SendableChooser<Command>    chooser               = new SendableChooser<Command>();
  private UsbCamera frontCamera, behindCamera;
  // private CvSink front;
  // private CvSource source;
  NetworkTableEntry drive;
  public RobotContainer() {
    configureButtonBindings();
    drive = Shuffleboard.getTab("Adjusting")
    .add("Chassic Speed", 1)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", 0, "max", 1)) // specify widget properties here
    .getEntry();

  }
 
  private void configureButtonBindings() {
    joystickMapping();
    driverStationMapping();
    teleop();
    modeSelector();
    CamServe();
    Pneumatic();
  }
  /**
   * Mapping joystick & command here.
   */
  private void joystickMapping(){
    new JoystickButton(joystick, Button.armOut)        .whenHeld(new ArmOut(m_arm));
    new JoystickButton(joystick, Button.armIn)         .whenHeld(new ArmIn(m_arm));
    new JoystickButton(joystick, Button.towerZero)     .whenHeld(new InstantCommand(()->m_tower.zero()));
    new JoystickButton(joystick, Button.rackZero)      .whenHeld(new InstantCommand(()->m_rack.zero()));
    new JoystickButton(joystick, Button.tempShoot)     .whenHeld(new SpinForward(m_shooter));
    new JoystickButton(joystick, Button.zeroRack)      .whenHeld(new RunCommand(()->m_rack.forward(), m_rack).withInterrupt(m_rack::getLimit).withTimeout(1));
    new JoystickButton(joystick, Button.intake_opp)    .whenHeld(new SpinReverse(m_intake))
                                                       .whenHeld(new SpinReverse(m_wing))
                                                       .whenHeld(new SpinReverse(m_conveyor));
    }
  /**
   * Mapping driver station & command here
   */
  private void driverStationMapping(){
    // new JoystickButton(driverStation, Button.flySpin)       .whenHeld(new DistantanceShoot(m_shooter));
    new JoystickButton(driverStation, Button.flySpin)       .whenHeld(new SpinForward(m_shooter));
    new JoystickButton(driverStation, Button.conveyor)      
                                                            .whenHeld(new SpinForward(m_wing))
                                                            .whenHeld(new RunCommand(()->m_conveyor.forward()))//.withInterrupt(this::getConveyButton))
                                                            .whenReleased(new InstantCommand(()->m_conveyor.stop(), m_conveyor));
                                                            
  
    new JoystickButton(driverStation, Button.turretleft)    .whenHeld(new SpinForward(m_tower));    
    new JoystickButton(driverStation, Button.turretright)   .whenHeld(new SpinReverse(m_tower));  
    new JoystickButton(driverStation, Button.rackup)        .whenHeld(new SpinForward(m_rack));
    new JoystickButton(driverStation, Button.rackdown)      .whenHeld(new SpinReverse(m_rack));  
    new JoystickButton(driverStation, Button.intake)        .whenHeld(new SpinForward(m_intake))
                                                            .whenHeld(new SpinForward(m_wing)); 
    new JoystickButton(driverStation, Button.autoAim)       .whenHeld(new RunCommand(()->m_tower.aim(), m_tower))//.withInterrupt(this::getAimButton))
                                                            .whenReleased(new InstantCommand(()->m_tower.stop(), m_tower))
                                                            .whenPressed(new RunCommand(()-> m_rack.aim(Rack.unit(Limelight.getDistance()))).withTimeout(1.0))
                                                            .whenReleased(new InstantCommand(()->m_rack.stop(), m_rack));
  }

  public boolean getAimButton(){
    SmartDashboard.putBoolean("aimButton", driverStation.getRawButton(Button.autoAim));
    return driverStation.getRawButtonReleased(Button.autoAim);
  }
  public boolean getConveyButton(){
    return !driverStation.getRawButtonPressed(Button.flySpin);
  }
  private void teleop(){
    controlDrivetrain.setDefaultCommand(
      new RunCommand(
        ()->controlDrivetrain
                .curvatureDrive(joystick.getY() * drive.getDouble(0.5), joystick.getZ() * -drive.getDouble(0.4) * 0.8 , joystick.getTrigger()), 
          controlDrivetrain)
      );
  }

  private void modeSelector(){
    // chooser.addOption("Left Up",          new LeftUp(trajectoryDrivetrain, controlDrivetrain, m_intake, m_conveyor, m_shooter, m_arm, m_wing, m_rack, m_tower));
    // chooser.addOption("Left Down ",       new LeftDown(trajectoryDrivetrain, controlDrivetrain));
    chooser.setDefaultOption("LeftUp Base", new OneMeter(-8240, trajectoryDrivetrain, controlDrivetrain, m_intake, m_conveyor, m_shooter, m_arm, m_wing, m_rack, m_tower));
    chooser.addOption("LeftUp Sock", new LeftUp(trajectoryDrivetrain, controlDrivetrain, m_intake, m_conveyor, m_shooter, m_arm, m_wing, m_rack, m_tower));
    chooser.addOption("Middle", new OneMeter(-10157, trajectoryDrivetrain, controlDrivetrain, m_intake, m_conveyor, m_shooter, m_arm, m_wing, m_rack, m_tower));
    Shuffleboard.getTab("Auto").add(chooser);
  }

  private void CamServe(){
    frontCamera = CameraServer.getInstance().startAutomaticCapture();
    behindCamera = CameraServer.getInstance().startAutomaticCapture();
    frontCamera.setResolution(320, 240);
    frontCamera.setFPS(3);
    behindCamera.setResolution(320, 240);
    behindCamera.setFPS(3);
    // front = CameraServer.getInstance().getVideo(frontCamera);
  }

  private void Pneumatic(){
    m_Pneumatic.Pneumatic_ON();
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
