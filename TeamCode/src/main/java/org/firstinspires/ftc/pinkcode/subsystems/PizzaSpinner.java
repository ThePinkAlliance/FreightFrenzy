package org.firstinspires.ftc.pinkcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.pinkcode.Constants;

@Config
public class PizzaSpinner extends HardwareMap {
    public double velocity = 230;
    public double autoVelocity = 130;

    public PizzaSpinner(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void spin(double right_trigger, double left_trigger) {
        // If right bumper is held, boolean value is true and motor goes to 80% power

        if (left_trigger > 0.1) {
            Spinner_Motor.setVelocity(-velocity);
        } else if (left_trigger == 0 && right_trigger == 0) {
            Spinner_Motor.setVelocity(0);
        }

        if (right_trigger > 0.1) {
            Spinner_Motor.setVelocity(velocity);
        }
    }

    public void spinReverse(boolean right_bumper) {
        // If right bumper is held, boolean value is true and motor goes to 80% power
        if (right_bumper) {
            this.Spinner_Motor.setPower(-Constants.pizzaSpinner);
        } else {
            this.Spinner_Motor.setPower(0);
        }
    }

    public double getVelocity() {
        return Spinner_Motor.getVelocity();
    }

    public void autoSpin() {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= 11){
            this.Spinner_Motor.setVelocity(autoVelocity);
        }
        this.Spinner_Motor.setVelocity(0);
    }

    public void autoSpin(double time) {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= time){
            this.Spinner_Motor.setPower(Constants.pizzaSpinner);
        }
        this.Spinner_Motor.setPower(0);
    }

    public void autoSpinReverse(double time) {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.startTime();

        //spin for 10 to 12 seconds
        while(elapsedTime.seconds() >= time) {
            this.Spinner_Motor.setPower(-Constants.pizzaSpinner);
        }
        this.Spinner_Motor.setPower(0);
    }
}
