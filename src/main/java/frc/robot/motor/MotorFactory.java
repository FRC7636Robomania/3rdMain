/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.motor;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

/**
 * Config motor, using "Fluent Interface".
 * Must using all method before using configLikePrevious()
 */
public class MotorFactory {
    /**
     * Restoring motor config.
     */
    private static class MotorConfig {
        public static FeedbackDevice sensor;
        public static int sensorPosition;
        public static int pidSlot;
        public static int timeoutMs;
    
    }
    /**
     * Initializing motor
     * @param motor
     * @return motor
     */
    public static TalonFX init(final TalonFX motor) {
        motor.configFactoryDefault();
        return motor;
    }

    /**
     * Set follower.
     * 
     * @param master
     * @param follower
     * @return master
     */
    public static TalonFX setFollower(final TalonFX master, final TalonFX follower) {
        follower.follow(master);
        return master;
    }

    /**
     * Set sensor type
     * 
     * @param motor
     * @param sensorType
     * @return motor {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static TalonFX setSensor(final TalonFX motor, final FeedbackDevice sensorType) {
        MotorConfig.sensor = sensorType;
        motor.configSelectedFeedbackSensor(sensorType);
        return motor;
    }

    /**
     * Set sensor position.
     * 
     * @param motor
     * @param sensorPosition
     * @param pidSlot
     * @param timeoutMs
     * @return motor
     */
    public static TalonFX setPosion(final TalonFX motor, final int sensorPosition, final int pidSlot,
            final int timeoutMs) {
        MotorConfig.sensorPosition = sensorPosition;
        MotorConfig.pidSlot = pidSlot;
        MotorConfig.timeoutMs = timeoutMs;
        motor.setSelectedSensorPosition(sensorPosition, pidSlot, timeoutMs);
        return motor;
    }

    /**
     * Set sensor phase.
     * 
     * @param motor
     * @param phase
     * @return motor
     */
    public static TalonFX setSensorPhase(final TalonFX motor, final boolean phase) {
        motor.setSensorPhase(phase);
        return motor;
    }

    /**
     * Set invertType
     * 
     * @param motor
     * @param invertType
     * @return motor
     */
    public static TalonFX setInvert(final TalonFX motor, final InvertType invertType) {
        motor.setInverted(invertType);
        return motor;
    }

    /**
     * Set motor like previous.
     * 
     * @param motor
     * @param sensorPhase
     * @param invertType  {@link MotorConfig}
     */
    public static void configLikePrevious(final TalonFX motor, final boolean sensorPhase, final InvertType invertType) {
        motor.configSelectedFeedbackSensor(MotorConfig.sensor);
        motor.setSelectedSensorPosition(MotorConfig.sensorPosition, MotorConfig.pidSlot, MotorConfig.timeoutMs);
        motor.setSensorPhase(sensorPhase);
        motor.setInverted(invertType);
    }


}
