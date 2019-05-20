package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;
import frc.robot.IO;

public class Drivetrain extends Subsystem {

    private Spark m_leftMotor;
    private Spark m_rightMotor;

    private Drivetrain() {
        m_leftMotor = new Spark(IO.kDRIVE_LEFT_MOTOR);
        m_rightMotor = new Spark(IO.kDRIVE_RIGHT_MOTOR);
    }

    private static Drivetrain m_instance = null;

    public static Drivetrain getInstance() {
        if (m_instance == null) {
            m_instance = new Drivetrain();
        }

        return m_instance;
    }
    

    @Override
	public
    void update(HashMap<String, Object> outputs) {
        double leftVoltage = (double)outputs.get(Constants.DRIVE_LEFT_VOLTAGE);
        double rightVoltage = (double)outputs.get(Constants.DRIVE_RIGHT_VOLTAGE);

        double leftOut = leftVoltage/12.0;
        double rightOut = rightVoltage/12.0;

        m_leftMotor.set(leftOut);
        m_rightMotor.set(rightOut);
    }
    
}