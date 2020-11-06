/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * Vision constants
     */
    public static class Vision{
        public static final String limelightName = "limelight-unicorn";
        public static final double targetHigh = 206;
        public static final double limelightHigh = 58;
        public static final double limelightAngle = 36;
        public static final double lime_kp = 0.15;
        public static final double lime_ki = 0.0;
        public static final double lime_kd = 0.0;
    }
    /**
     * Trajectory path' path
     */
    public static class Trajectory{
        public static final String three    = "output/ThreeBall.wpilib.json";
        public static final String two      = "output/TwoBall.wpilib.json";
        public static final String OneMeter = "output/OneMeter.wpilib.json";
        public static final String upSock   = "output/UpSock.wpilib.json";
    }
    /**
     * Chassis's PID, recommend not used.
     */
    public static class Chassis{
        public static final double p = 0.01;
        public static final double i = 0.0001;
        public static final double d = 100;
        public static final double f = 0.1;
    }
    /**
     * Chassis motor config
     */
    public static class Motor{
        public static final int leftMaster    = 19;
        public static final int leftFollewer  = 18;
        public static final int rightMaster   = 21;
        public static final int rightFollower = 20;
        public static final double distancePerPulse = 0.1524 * Math.PI / 2048 / 9.7;
        public static final double wheelPitch = 0.7407;

        public static final boolean isRightMotorInvert = false;
        public static final boolean isLeftMotorInvert = true;
        public static final boolean isRightPhaseInvert = false;
        public static final boolean isLeftPhaseInvert = true;
    }
    /**
     * Some costants about powercell 
     */
    public static class PowCon{
        public static final int flywheel = 17;
        public static final int flywheel2 = 15;

        public static final int conveyor = 1;

        public static final int aimer = 8;

        public static final int wingRight = 3;
        public static final int wingMiddle = 2;

        public static final double flywheel_kP = 0.2; 
        public static final double flywheel_kF = 1 * 1023 / 7200;

        public static final int tower = 52;

        public static final int rack = 25;

        public static final int flywheelVelocity = 15000;

    }
    /**
     * Some constants about Pneumatic
     */
    public static class Pne{
        public static final int Solenoid = 0;
        public static final int ArmDS_1 = 6;
        public static final int ArmDS_2 = 7;
    }
    /**
     * DriverStation;s button and Joystick's button mapping
     */
    public static class Button{
        //Driverstation

        // ____________________
        // |                  |
        // |    1 2  3  4     |              
        // |                  |                      
        // |    5 6  7  8     |
        // |                  |
        // ____________________
        
        public static final int flySpin     = 1;
        public static final int conveyor    = 2;
        public static final int turretleft  = 3;
        public static final int turretright = 4;
        public static final int rackdown      = 5;
        public static final int rackup   = 6;
        public static final int intake      = 7;
        public static final int autoAim     = 8;

        //Joystick
        //trigger be used to curvature drive
        public static final int armOut      = 3;
        public static final int armIn       = 4;
        public static final int towerZero   = 5;
        public static final int rackZero    = 6;
        public static final int tempShoot   = 7;
        public static final int zeroRack    = 8;
    }
}
