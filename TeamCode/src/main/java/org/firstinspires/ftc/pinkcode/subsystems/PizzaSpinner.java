package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PizzaSpinner extends HardwareMap {

//    private final double speed = 0.557;
    private final double speed = 0.557;

    public PizzaSpinner(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void spin(boolean right_bumper, boolean left_bumper) {
        // If right bumper is held, boolean value is true and motor goes to 80% power
        if (right_bumper) {
           this.Spinner_Motor.setPower(speed);
        }else {
            this.Spinner_Motor.setPower(0);
        }

        if (left_bumper) {
            this.Spinner_Motor.setPower(-speed);
        } else if (!right_bumper) {
            this.Spinner_Motor.setPower(0);
        }
    }

    public void spinReverse(boolean right_bumper) {
        // If right bumper is held, boolean value is true and motor goes to 80% power
        if (right_bumper) {
            this.Spinner_Motor.setPower(-speed);
        } else {
            this.Spinner_Motor.setPower(0);
        }
    }

    public void autoSpin() {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= 11){
            this.Spinner_Motor.setPower(speed);
        }
        this.Spinner_Motor.setPower(0);
    }

    public void autoSpin(double time) {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= time){
            this.Spinner_Motor.setPower(speed);
        }
        this.Spinner_Motor.setPower(0);
    }

    public void autoSpinReverse(double time) {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= time) {
            this.Spinner_Motor.setPower(-speed);
        }
        this.Spinner_Motor.setPower(0);
    }
}
