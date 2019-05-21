package frc.robot.subsystems;

import java.util.HashMap;

/* Unlike the subsystems defined in command based programming, a subsystem in this code base is just a grouping of actuators (solenoids and motors). update() will consume
a construct specifying the specific output voltages for each motor/positions of each solenoid, which will then be converted into the proper output (-1.0 to 1.0, solenoid state)
to actually cause robot to move. If you want to implement voltage-compensation in the robot, classes extending this one would be a good place to for that! */

public abstract class Subsystem {

    public abstract void update(HashMap<String, Object> outputs);
    
}