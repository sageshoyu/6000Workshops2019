package frc.robot.controllers;

import java.util.HashMap;


public abstract class SubsystemController {

    public abstract HashMap<String,Object> getOutput(HashMap<String,Object> controlInputs, HashMap<String,Object> sensorInputs, double dTime);

    public abstract void disable();
}

