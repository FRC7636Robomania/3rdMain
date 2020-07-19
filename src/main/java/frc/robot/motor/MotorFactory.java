/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.motor;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

/**
 * Config motor, using "Fluent Interface"
 */
public class MotorFactory {
    /**
     * Restoring motor config.
     */
    public class MotorConfig {
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
    public static TalonFX init(TalonFX motor){
        motor.configFactoryDefault();
        return motor;
    }
    
    /**
     * Set follower.
     * @param master
     * @param follower
     * @return master 
     */
    public static TalonFX setFollower(TalonFX master, TalonFX follower){
        follower.follow(master);
        return master;
    }
    /**
     * Set sensor type
     * @param motor
     * @param sensorType
     * @return motor
     * {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static TalonFX setSensor(TalonFX motor, FeedbackDevice sensorType){
        MotorConfig.sensor = sensorType;
        motor.configSelectedFeedbackSensor(sensorType);
        return motor;
    }
    /**
     * Set sensor position.
     * @param motor
     * @param sensorPosition
     * @param pidSlot
     * @param timeoutMs
     * @return motor
     */
    public static TalonFX setPosion(TalonFX motor, int sensorPosition, int pidSlot, int timeoutMs){
        MotorConfig.sensorPosition = sensorPosition;
        MotorConfig.pidSlot        = pidSlot;
        MotorConfig.timeoutMs      = timeoutMs;
        motor.setSelectedSensorPosition(sensorPosition, pidSlot, timeoutMs);
        return motor;
    }
    /**
     * Set sensor phase.
     * @param motor
     * @param phase
     * @return motor
     */
    public static TalonFX setSensorPhase(TalonFX motor, boolean phase){
        motor.setSensorPhase(phase);
        return motor;
    }
    public static void configLikePrevious(TalonFX motor, boolean phase){
        motor.configSelectedFeedbackSensor(MotorConfig.sensor);
        motor.setSelectedSensorPosition(MotorConfig.sensorPosition, MotorConfig.pidSlot, MotorConfig.timeoutMs);
    }


}
