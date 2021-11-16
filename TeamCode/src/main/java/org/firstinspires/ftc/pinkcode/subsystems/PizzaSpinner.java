package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PizzaSpinner extends HardwareMap {

    private final double speed = 0.3;

    public PizzaSpinner(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void spin(boolean right_bumper, double reduction) {
        // If right bumper is held, boolean value is true and motor goes to 80% power
        if (right_bumper) {
           this.Spinner_Motor.setPower(speed);
        } else {
            this.Spinner_Motor.setPower(0);
        }
    }

    public void reverse(boolean right_bumper) {
        // If right bumper is held, boolean value is true and motor goes to 80% power
        if (right_bumper) {
            this.Spinner_Motor.setPower(-speed);
        } else {
            this.Spinner_Motor.setPower(0);
        }
    }

    public void autoSpin(){
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= 11){
            this.Spinner_Motor.setPower(0.8);
        }
        this.Spinner_Motor.setPower(0);
    }
}
