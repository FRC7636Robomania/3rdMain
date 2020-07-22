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
    public static class Motor{
        public static final int leftMaster = 18;
        public static final int leftFollewer = 20;
        public static final int rightMaster = 21;
        public static final int rightFollower = 19;
        public static final double distancePerPulse = 0.1524 * Math.PI / 2048 / 9.7;
        public static final double wheelPitch = 0.7407;
    }
    public static class PowCon{
        public static final int flywheel = 4;
        public static final int conveyor = 1;
        public static final int aimer = 8;
        public static final int intakearmer = 0;
        public static final int wideright = 2;
        public static final int wideleft = 3;
        public static final double flywheel_kP = 0.1;
        public static final double flywheel_kF = 0.0506;
    }
    public static class Button{
        //Driverstation
        /**____________________
         * |                  |
         * |    ○ ○  ○  ○     |              
         * |                  |                      
         * |    ○ ○  ○  ○     |
         * |                  |
         * ____________________
         */
        public static final int shoot = 1;

        public static final int aim = 2;
        public static final int turretleft = 3;
        public static final int turretright = 4;

        //Joystick
        public static final int emergencyarmdown  = 7;
        public static final int emergencyarmup    = 8;
        public static final int emergencyintake   = 9;
        public static final int emergencyshooter = 1;
    }
}
