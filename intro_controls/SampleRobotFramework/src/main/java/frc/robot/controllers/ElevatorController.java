package frc.robot.controllers;

import java.util.HashMap;

import frc.robot.Constants;

public class ElevatorController extends SubsystemController {

    private enum State {
        DISABLED, ZEROING, RUNNING
    }

    public ElevatorController() {
        state = State.DISABLED;
    }
    
    State state;
    double offset;
    double filteredSetpoint;
    double lastError; 

    @Override
    public HashMap<String, Object> getOutput(HashMap<String, Object> controlInputs, HashMap<String, Object> sensorInputs, double dTime) {
        // Parsing input
        boolean shouldZero = (boolean)controlInputs.get(Constants.ELEV_ZERO);
        double setpoint = (double)controlInputs.get(Constants.ELEV_SETPOINT);

        boolean isLowHit = (boolean)sensorInputs.get(Constants.ELEV_LOWBOUND_OUT);
        boolean isTopHit = (boolean)sensorInputs.get(Constants.ELEV_HIGHBOUND_OUT);
        double encoderPos = (double)sensorInputs.get(Constants.ELEV_ENCODER_OUT);
        

        // STATE MACHINE TO CALCULATE DESIRED SETPOINT
        double kVoltageLimit = 0.0;
        switch(state) {
            case DISABLED:

                kVoltageLimit = 0.0;
                filteredSetpoint = encoderPos;

                if(shouldZero) state = State.ZEROING;

                break;

            case ZEROING:

                kVoltageLimit = 4.0;
                if(isLowHit) {
                    offset = -encoderPos;
                    state = State.RUNNING;
                } else {
                    filteredSetpoint -= Constants.kZERO_SPEED*dTime;
                }

                break;

            case RUNNING:
                kVoltageLimit = 12.0;
                filteredSetpoint = setpoint;
                
                if (filteredSetpoint > Constants.kELEV_MAX_HEIGHT) {
                    filteredSetpoint = Constants.kELEV_MAX_HEIGHT;
                } else if (filteredSetpoint < Constants.kELEV_MIN_HEIGHT) {
                    filteredSetpoint = Constants.kELEV_MIN_HEIGHT;
                }

                if(shouldZero) state = State.ZEROING;

                break;
        }

        // FEEDBACK LOOP CALCULATIONS
        double error = filteredSetpoint - (encoderPos + offset);
        double voltageOut = Constants.kELEV_Kp*error + Constants.kELEV_Kd*(error-lastError)/dTime;
        lastError = error;

        
        // ENSURING WE ARE WITHIN OUR VOLTAGE BOUNDS
        if (voltageOut != 0.0 && Math.abs(voltageOut) > kVoltageLimit) {
            voltageOut = Math.abs(voltageOut)/voltageOut * kVoltageLimit;
        }

        // PRODUCE OUTPUT
        HashMap<String,Object> elevControlOutMap = new HashMap<String,Object>();
        elevControlOutMap.put(Constants.ELEV_VOLTAGE,voltageOut);

        return elevControlOutMap;
    }






}