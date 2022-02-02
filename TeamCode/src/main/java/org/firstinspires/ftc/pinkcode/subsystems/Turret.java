package org.firstinspires.ftc.pinkcode.subsystems;

import org.firstinspires.ftc.pinkcode.Constants;

public class Turret extends HardwareMap {

    enum GRABBER_STATE {
        OPEN,
        CLOSE
    }

    GRABBER_STATE grabberState = GRABBER_STATE.CLOSE;

    public Turret(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

//    public void setCurrentAngle(double angle) {
//        double error = getCurrentAngle() - angle;
//
//        double kp = 2.1;
//        double kd = 1.5;
//
//        double power = (kp * error) - (kd * Turret_Rotate_Motor_L.getCurrentPosition());
//
//        Turret_Rotate_Motor_L.setPower(power);
//        Turret_Rotate_Motor_R.setPower(power);
//    }

    //sets state of grabber to input state
    public int setGrabberState(GRABBER_STATE newState) {
        this.grabberState = newState;

        if (newState == GRABBER_STATE.CLOSE) {
//            this.Grabber_Motion.setPosition(Constants.GRABBER_POSITIONS.CLOSE);

            return Constants.GRABBER_POSITIONS.CLOSE;
        } else if (newState == GRABBER_STATE.OPEN) {
//            this.Grabber_Motion.setPosition(Constants.GRABBER_POSITIONS.OPEN);

            return Constants.GRABBER_POSITIONS.OPEN;
        } else {
            return Constants.GRABBER_POSITIONS.CLOSE;
        }
    }

//    public double getCurrentAngle() {
//        return Turret_Rotate_Motor_L.getCurrentPosition();
//    }
}
