/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.controllers.DriveController;
import frc.robot.controllers.ElevatorController;
import frc.robot.controllers.SubsystemController;
import frc.robot.input.ControlBoard;
import frc.robot.input.DriveSensors;
import frc.robot.input.ElevatorSensors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  private double prevTime;
  private double curTime;
  private double dTime;

  private SubsystemController elevatorController;
  private SubsystemController driveController;

  @Override
  public void robotInit() {
    elevatorController = new ElevatorController();
    driveController = new DriveController();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    prevTime = Timer.getFPGATimestamp();
  }

  @Override
  public void teleopPeriodic() {
    curTime = Timer.getFPGATimestamp();
    dTime = curTime-prevTime;

    HashMap<String,Object> driveInputs = ControlBoard.getInstance().getDriveInputs();
    //HashMap<String,Object> driveSensorOuts = DriveSensors.getInstance().getUpdate();

    HashMap<String,Object> elevatorInputs = ControlBoard.getInstance().getElevatorInputs();
    HashMap<String,Object> elevatorSensorOuts = ElevatorSensors.getInstance().getUpdate();

    HashMap<String,Object> driveOuts = driveController.getOutput(driveInputs, null, dTime);
    HashMap<String,Object> elevatorOuts = elevatorController.getOutput(elevatorInputs, elevatorSensorOuts, dTime);
  
    Drivetrain.getInstance().update(driveOuts);
    Elevator.getInstance().update(elevatorOuts);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
