package frc.robot.controllers;

import java.util.HashMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveController extends SubsystemController {
    
    double filteredLeftSetpoint;
    double filteredRightSetpoint;

    double prevFilteredLeftSetpoint;
    double prevFilteredRightSetpoint;

    public DriveController() {
        prevFilteredLeftSetpoint = 0.0;
        prevFilteredRightSetpoint = 0.0;
    }
   
   
   
    @Override
    public HashMap<String, Object> getOutput(HashMap<String, Object> controlInputs, HashMap<String, Object> sensorInputs, double dTime) {

        // parse input
        double leftSetpoint = (double)controlInputs.get(Constants.DRIVE_LEFT_SETPOINT);
        double rightSetpoint = (double)controlInputs.get(Constants.DRIVE_RIGHT_SETPOINT);

        SmartDashboard.putNumber("DriveLeft_Setpoint", leftSetpoint);
        

        // compute desired setpoints with acceleration cap
        if(Math.abs(leftSetpoint - prevFilteredLeftSetpoint) < Constants.kDRIVE_DEADBAND) {
            filteredLeftSetpoint = prevFilteredLeftSetpoint;
        } else if (prevFilteredLeftSetpoint < leftSetpoint) {
            filteredLeftSetpoint = Constants.kDRIVE_MAX_ACCEL*dTime + prevFilteredLeftSetpoint;
        } else {
            filteredLeftSetpoint = -Constants.kDRIVE_MAX_ACCEL*dTime + prevFilteredLeftSetpoint;
        }

        if(Math.abs(rightSetpoint - prevFilteredRightSetpoint) < Constants.kDRIVE_DEADBAND) {
            filteredRightSetpoint = prevFilteredRightSetpoint;
        } else if (prevFilteredRightSetpoint < rightSetpoint) {
            filteredRightSetpoint = Constants.kDRIVE_MAX_ACCEL*dTime + prevFilteredRightSetpoint;
        } else {
            filteredRightSetpoint = -Constants.kDRIVE_MAX_ACCEL*dTime + prevFilteredRightSetpoint;
        }

        prevFilteredLeftSetpoint = filteredLeftSetpoint;
        prevFilteredRightSetpoint = filteredRightSetpoint;

        SmartDashboard.putNumber("Filtered_Setpoint", filteredLeftSetpoint);

        // dead reckoning feedforward controller
        double leftVoltageOut = Constants.kDRIVE_LEFT_Kf*filteredLeftSetpoint;
        double rightVoltageOut = Constants.kDRIVE_RIGHT_Kf*filteredRightSetpoint;


        // produce output
        HashMap<String,Object> driveControlOutMap = new HashMap<String,Object>();
        driveControlOutMap.put(Constants.DRIVE_LEFT_VOLTAGE, leftVoltageOut);
        driveControlOutMap.put(Constants.DRIVE_RIGHT_VOLTAGE, rightVoltageOut);

        return driveControlOutMap;
    }

    @Override
    public void disable() {
        filteredLeftSetpoint = 0.0;
        filteredRightSetpoint = 0.0;
        prevFilteredLeftSetpoint = 0.0;
        prevFilteredRightSetpoint = 0.0;
    }



} 