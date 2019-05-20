package frc.robot.input;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.IO;

public class ControlBoard {

    private XboxController m_controller;


    private static ControlBoard m_instance = null;

    private ControlBoard() {
        m_controller = new XboxController(IO.kXBOX_PORT);
    }

    public static ControlBoard getInstance() {
        if (m_instance == null) {
            m_instance = new ControlBoard();
        }

        return m_instance;
    }

    public HashMap<String,Object> getDriveInputs() {
        double leftVel = m_controller.getY(GenericHID.Hand.kLeft)*Constants.kDRIVE_MAX_SPEED;
        double rightVel = m_controller.getY(GenericHID.Hand.kRight)*Constants.kDRIVE_MAX_SPEED;

        HashMap<String,Object> driveInputMap = new HashMap<String,Object>();
        driveInputMap.put(Constants.DRIVE_LEFT_SETPOINT, leftVel);
        driveInputMap.put(Constants.DRIVE_RIGHT_SETPOINT, rightVel);
        return driveInputMap;
    }

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