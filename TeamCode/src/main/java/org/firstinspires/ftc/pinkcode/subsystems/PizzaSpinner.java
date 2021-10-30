package org.firstinspires.ftc.pinkcode.subsystems;

public class PizzaSpinner extends HardwareMap {

    public PizzaSpinner(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void spin(boolean right_bumper) {
        boolean RightBumper = right_bumper;

        // If right bumper is held, boolean value is true and motor goes to 80% power
        if (right_bumper) {
           this.Spinner_Motor.setPower(0.8);
        } else {
            this.Spinner_Motor.setPower(0);
        }
    }
}
