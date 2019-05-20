package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Constants {
   /*INPUT INTERPRETATION CONSTANTS*/
   //Elevator
   public static final double kELEV_LOW_HEIGHT = 0.05;
   public static final double kELEV_MID_HEIGHT = 0.5;
   public static final double kELEV_HIGH_HEIGHT = 1.0;
   
    /*ELEVATOR CONSTANTS */
    //Encoder constants
    public static final double kELEV_ENC_RADIANS_PER_PULSE = 2*3.14/1024;
    public static final double kELEV_ENC_MINRATE = 6*3.14; //3 revolutions per second
    public static final double kELEV_ENC_MAXPERIOD = 1.0;
    public static final boolean kELEV_ENC_REVERSE = false;
    public static final Encoder.EncodingType kELEV_DECODE_RATE = Encoder.EncodingType.k4X;
    //Elevator modelling constants
    public static final double kELEV_DRUM_RADIUS = 0.01; //TODO: MEASURE ME in meters
    //Elevator control constants
    public static final double kZERO_SPEED = 0.05; //in m/s
    public static final double kELEV_MIN_HEIGHT = 0.05; //determine after measuring elevator in  meters
    public static final double kELEV_MAX_HEIGHT = 1.95;
    public static final double kELEV_Kp = 0.001; //TUNE ME
    public static final double kELEV_Kd = 0; 
    

    /*DRIVETRAIN CONSTANTS*/
    //Encoder constants
    public static final double kDRIVE_ENC_RADIANS_PER_PULSE = 2*3.14/512; //Check, assume encoders have 512 pulse/rev
    public static final double kDRIVE_ENC_MAXPERIOD = 1.0;
    public static final double kDRIVE_ENC_MINRATE = 6*3.14;
    public static final Encoder.EncodingType kDRIVE_ENC_DECODE_RATE = Encoder.EncodingType.k2X;
    public static final boolean kDRIVE_LEFTENC_REVERSE = false;
    public static final boolean kDRIVE_RIGHTENC_REVERSE = true;
    //Drivetrain modelling constants
    public static final double kDRIVE_WHEEL_RADIUS = 0.03; //TODO: MEASURE ME in meters
    //Drivetrain control constants
    public static final double kDRIVE_MAX_SPEED = 10; //in m/s
    public static final double kDRIVE_MAX_ACCEL = 2; // in m/s^2
    public static final double kDRIVE_LEFT_Kf = 12.0/13.0;  //MEASURE ME in V/(m/s) 
    public static final double kDRIVE_RIGHT_Kf = 12.1/13.0; 
    public static final double kDRIVE_DEADBAND = 0.1;
    
    /*TRANSFER-HASHMAP KEYS */
    //Drivetrain input keys
    public static final String DRIVE_LEFT_SETPOINT = "drive left setpoint";
    public static final String DRIVE_RIGHT_SETPOINT = "drive right setpoint";
    //Drivetrain sensor reading keys
    public static final String DRIVE_LEFT_ENC_OUT = "drivetrain left encoder";
    public static final String DRIVE_RIGHT_ENC_OUT = "drivetrain right encoder";
    //Drivetrain controller output keys
    public static final String DRIVE_LEFT_VOLTAGE = "drivetrain left motor voltage";
    public static final String DRIVE_RIGHT_VOLTAGE = "drivetrain right motor voltage";

    //Elevator input keys
    public static final String ELEV_ZERO = "zero elevator in";
    public static final String ELEV_SETPOINT = "elevator setpoint";
    //Elevator sensor reading keys
    public static final String ELEV_LOWBOUND_OUT = "elevator lower bound switch";
    public static final String ELEV_HIGHBOUND_OUT = "elevator upper bound switch";
    public static final String ELEV_ENCODER_OUT = "elevator encoder position";
    //Elevator controller output keys
    public static final String ELEV_VOLTAGE = "elevator motor voltage";
   


}