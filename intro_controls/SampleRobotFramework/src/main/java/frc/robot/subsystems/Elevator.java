package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;
import frc.robot.IO;

/*Collection of necesssary elevator motors (in this example, there is only one). No voltage compensation (dividing by 12.0 to scale to -1.0 to 1.0) */
public class Elevator extends Subsystem {

    private Spark m_motor;

    private Elevator() {
        m_motor = new Spark(IO.kELEV_MOTOR);
    }

    private static Elevator m_instance = null;

    public static Elevator getInstance() {
        if (m_instance == null) {
            m_instance = new Elevator();
        }

        return m_instance;
    }


    @Override
    public void update(HashMap<String, Object> outputs) {
        double voltage = (double)outputs.get(Constants.ELEV_VOLTAGE);
        double motorOut = voltage/12.0;
        m_motor.set(motorOut);
    }


}