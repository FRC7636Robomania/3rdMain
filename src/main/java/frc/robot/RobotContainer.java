/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
<<<<<<< HEAD
import edu.wpi.first.wpilibj2.command.RunCommand;
=======

>>>>>>> e0e19a0a9866129a73f9b4ebd1d120e43f2d7f23
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.Button;
import frc.robot.commands.Tower_set;
import frc.robot.commands.Tower_set2;
import frc.robot.commands.Shoot.shoot.Fastshoot;
import frc.robot.commands.auto.LeftUp;
<<<<<<< HEAD
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.chassis.ControlDrivetrain;
import frc.robot.subsystems.chassis.MusicDrivetrain;

=======
import frc.robot.subsystems.*;
//import frc.robot.subsystems.chassis.MusicDrivetrain;
>>>>>>> e0e19a0a9866129a73f9b4ebd1d120e43f2d7f23
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
<<<<<<< HEAD
  private final        Shooter              m_shooter            = new Shooter();
  public  final static Joystick             joystick             = new Joystick(0);
  public  static       ControlDrivetrain    controlDrivetrain    = new ControlDrivetrain();
  private              SendableChooser<Command>    chooser       = new SendableChooser<Command>();
=======
  private final Shooter          m_shooter          = new Shooter();
  private final Tower            m_Tower            = new Tower();
  private final Joystick         joystick           = new Joystick(0);
>>>>>>> e0e19a0a9866129a73f9b4ebd1d120e43f2d7f23



  // The robot's subsystems and commands are defined here...

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  
  public RobotContainer() {
    // Configure the button bindings
    //MusicDrivetrain.start("noise.chrp");
    configureButtonBindings();
<<<<<<< HEAD

    controlDrivetrain.setDefaultCommand(new RunCommand(()-> controlDrivetrain.drive(joystick.getRawAxis(1) * -0.2, joystick.getRawAxis(0) * 0.1), controlDrivetrain));
    chooser.addOption("Left Up ", new LeftUp(Robot.trajectoryDrivetrain));
=======
    
>>>>>>> e0e19a0a9866129a73f9b4ebd1d120e43f2d7f23
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
<<<<<<< HEAD
    new JoystickButton(joystick, Button.emergencyshooter).whenHeld(new Fastshoot(m_shooter));
=======
    //new JoystickButton(joystick, 3).whenHeld(()->    MusicDrivetrain.start("noise.chrp"));
    new JoystickButton(joystick, Button.emergencyshooter)  .whenHeld(new Fastshoot(m_shooter));
    new JoystickButton(joystick, Button.tower1)  .whenHeld(new Tower_set(m_Tower));    
    new JoystickButton(joystick, Button.tower2)  .whenHeld(new Tower_set2(m_Tower));    
>>>>>>> e0e19a0a9866129a73f9b4ebd1d120e43f2d7f23
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return chooser.getSelected();
  }
}
