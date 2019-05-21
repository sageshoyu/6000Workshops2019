package frc.robot.input;

import java.util.HashMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.IO;

/*ControlBoard represents the entire human driverstation controls (Joysticks, Gamepads, Xbox Controllers, etc) 
and groups related/triggers/axes together. Each subsystem has its own get<SUBSYSTEM_NAME>Inputs() method that returns 
an interpretation of those inputs sent to send to the subsystem controllers. This interpretation could be the desired setpoint,
whether a subsytsem should zero, etc.

Note: since this only works on one controller, only one class was necessarry. But if you expand to more controllers/custom setups,
you may have to break this up into smaller subunits and access them individually, or group them as fields in a big ControlBoard 
class (there are a ton of solutions to this problem).
*/

public class ControlBoard {
    
    
    private XboxController m_controller;

    //Only ONE ControlBoard is necessary for robot function. Using Singleton pattern to ensure only one instance of ControlBoard.
    private static ControlBoard m_instance = null;

    private ControlBoard() {
        m_controller = new XboxController(IO.kXBOX_PORT);
    }

    // lazy instantiation
    public static ControlBoard getInstance() {
        if (m_instance == null) {
            m_instance = new ControlBoard();
        }

        return m_instance;
    }

    //Interpret drive input controls - take axis [-1.0,1.0] and scale it to a velocity setpoint using max allowable velocity
    public HashMap<String,Object> getDriveInputs() {
        double leftVel = m_controller.getY(GenericHID.Hand.kLeft)*Constants.kDRIVE_MAX_SPEED;
        double rightVel = m_controller.getY(GenericHID.Hand.kRight)*Constants.kDRIVE_MAX_SPEED;

        HashMap<String,Object> driveInputMap = new HashMap<String,Object>();
        driveInputMap.put(Constants.DRIVE_LEFT_SETPOINT, leftVel);
        driveInputMap.put(Constants.DRIVE_RIGHT_SETPOINT, rightVel);
        return driveInputMap;
    }

    //Interpret elevator input controls - take in buttons and interpret desired setpoint. Includes whether 'zeroing/homing' 
    //button is pressed since it overrides everything in the controller
    public HashMap<String,Object> getElevatorInputs(){
        double setpoint = Constants.kELEV_LOW_HEIGHT;
        if (m_controller.getRawButton(IO.kELEV_MID_BUTTON)) {
            setpoint = Constants.kELEV_MID_HEIGHT;
        } else if(m_controller.getRawButton(IO.kELEV_HIGH_BUTTON)) {
            setpoint = Constants.kELEV_HIGH_HEIGHT;
        }
        
        
        HashMap<String,Object> elevatorInputMap = new HashMap<String,Object>();
        elevatorInputMap.put(Constants.ELEV_ZERO, m_controller.getRawButton(IO.kELEV_ZERO_BUTTON));
        elevatorInputMap.put(Constants.ELEV_SETPOINT, setpoint);

        return elevatorInputMap;
    }

}