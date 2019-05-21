package frc.robot.controllers;

import java.util.HashMap;

/*The meat of the robot control system. This takes use-input/autonomous commands and transforms them into a voltage output meant to be sent to the motors.
Subsystem coordination and behavior is implemented here. Since this is robot framework is not a command-based model, state-machines are recommended
to implement behavior. 

Note: sometimes, you're going to want to group subsystems together to coordinate them at once. You can make a controller to coordinate all the subsystems' controllers
together!

getOutput() is the method that takes in user and sensor input and turns out the necesserry voltage/state for all actuators in the subsystem
disable() is to a) stop subsystem in emergencies (of course, you'll need to implement safety checks that use this method to be effective)
                b) a way to reset between autonomous and teleop periods (of course, you can change the way subsystem controllers are laid
                   if you don't agree with this rationale.
*/
public abstract class SubsystemController {

    public abstract HashMap<String,Object> getOutput(HashMap<String,Object> controlInputs, HashMap<String,Object> sensorInputs, double dTime);

    public abstract void disable();
}

