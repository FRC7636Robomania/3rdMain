/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.Button;
import frc.robot.commands.AutoAim;
import frc.robot.commands.Shoot.SpinForward;
import frc.robot.commands.Shoot.SpinReverse;
import frc.robot.commands.arm.ArmIn;
import frc.robot.commands.arm.ArmOut;
import frc.robot.commands.auto.LeftDown;
import frc.robot.commands.auto.LeftUp;
import frc.robot.commands.auto.OneMeter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.chassis.ControlDrivetrain;
import frc.robot.subsystems.chassis.trajectory.TrajectoryDrivetrain;
import frc.robot.subsystems.pneumatic.Arm;
import frc.robot.subsystems.shooter.Conveyor;
import frc.robot.subsystems.shooter.Rack;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.Tower;
import frc.robot.subsystems.shooter.Wing;

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
  private final Joystick          joystick                        = new Joystick(0);
  private final Joystick          driverStation                   = new Joystick(1);
  private final ControlDrivetrain    controlDrivetrain            = new ControlDrivetrain();
  private final TrajectoryDrivetrain trajectoryDrivetrain         = new TrajectoryDrivetrain();
  private       SendableChooser<Command>    chooser               = new SendableChooser<Command>();
  // The robot's subsystems and commands are defined here...
  // private Compressor c = new Compressor();
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  
  public RobotContainer() {
    // Configure the button bindings
    //MusicDrivetrain.start("noise.chrp");
    configureButtonBindings();

    chooser.addOption("Left Up",          new LeftUp(trajectoryDrivetrain, controlDrivetrain));
    chooser.addOption("Left Down ",       new LeftDown(trajectoryDrivetrain, controlDrivetrain));
    chooser.setDefaultOption("One Meter", new OneMeter(trajectoryDrivetrain, controlDrivetrain));
    
    Shuffleboard.getTab("Auto").add(chooser);

  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    joystickMapping();
    driverStationMapping();
    teleop();
  }
  /**
   * Mapping joystick & command here.
   */
  private void joystickMapping(){
    new JoystickButton(joystick, Button.towerZero)     .whenHeld(new InstantCommand(()->m_tower.zero()));
    new JoystickButton(joystick, Button.armOut)        .whenHeld(new ArmOut(m_arm));
    new JoystickButton(joystick, Button.armIn)         .whenHeld(new ArmIn(m_arm));


  }
  /**
   * Mapping driver station & command here
   */
  private void driverStationMapping(){
    new JoystickButton(driverStation, Button.flySpin)       .whenHeld(new SpinForward(m_shooter));
    new JoystickButton(driverStation, Button.conveyor)      .whenHeld(new SpinReverse(m_conveyor))
                                                            .whenHeld(new SpinForward(m_wing));
    new JoystickButton(driverStation, Button.turretleft)    .whenHeld(new SpinForward(m_tower));    
    new JoystickButton(driverStation, Button.turretright)   .whenHeld(new SpinReverse(m_tower));  
    new JoystickButton(driverStation, Button.rackup)        .whenHeld(new SpinForward(m_rack));
    new JoystickButton(driverStation, Button.rackdoewn)     .whenHeld(new SpinReverse(m_rack));  
    new JoystickButton(driverStation, Button.intake)        .whenHeld(new SpinForward(m_intake)); 
    new JoystickButton(driverStation, Button.autoAim)       .whenHeld(new AutoAim());
  }
  /**
   * Teleop control
   */
  private void teleop(){
    controlDrivetrain.setDefaultCommand(
      new RunCommand(
        ()->controlDrivetrain
                .curvatureDrive(joystick.getY() * 0.3, joystick.getZ() * 0.3, joystick.getTrigger()), 
          controlDrivetrain)
      );
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
