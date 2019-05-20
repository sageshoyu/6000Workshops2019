package frc.robot.input;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.IO;

public class ControlBoard {

    private Joystick m_leftStick;
    private Joystick m_rightStick;


    private static ControlBoard m_instance = null;

    private ControlBoard() {
        m_leftStick = new Joystick(IO.kLEFTSTICK_PORT);
        m_rightStick = new Joystick(IO.kRIGHTSTICK_PORT);
    }

    public static ControlBoard getInstance() {
        if (m_instance == null) {
            m_instance = new ControlBoard();
        }

        return m_instance;
    }

    public HashMap<String,Object> getDriveInputs() {
        double leftVel = m_leftStick.getY()*Constants.kDRIVE_MAX_SPEED;
        double rightVel = m_rightStick.getY()*Constants.kDRIVE_MAX_SPEED;

        HashMap<String,Object> driveInputMap = new HashMap<String,Object>();
        driveInputMap.put(Constants.DRIVE_LEFT_SETPOINT, leftVel);
        driveInputMap.put(Constants.DRIVE_RIGHT_SETPOINT, rightVel);
        return driveInputMap;
    }

    public HashMap<String,Object> getElevatorInputs(){
        double setpoint = Constants.kELEV_LOW_HEIGHT;
        if (m_rightStick.getRawButton(IO.kELEV_MID_BUTTON)) {
            setpoint = Constants.kELEV_MID_HEIGHT;
        } else if(m_rightStick.getRawButton(IO.kELEV_HIGH_BUTTON)) {
            setpoint = Constants.kELEV_HIGH_HEIGHT;
        }
        
        
        HashMap<String,Object> elevatorInputMap = new HashMap<String,Object>();
        elevatorInputMap.put(Constants.ELEV_ZERO, m_rightStick.getRawButton(IO.kELEV_ZERO_BUTTON));
        elevatorInputMap.put(Constants.ELEV_SETPOINT, setpoint);

        return elevatorInputMap;
    }

}