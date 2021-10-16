package org.firstinspires.ftc.pinkcode.subsystems;


import android.widget.Spinner;

public class PizzaSpinner extends HardwareMap {

    public PizzaSpinner(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void spin(boolean button_x) {
        boolean ButtonX = button_x;

        //If button x is held, boolean value is true and motor goes to half power
        if (button_x) {
            Spinner_Motor.setPower(0.5);
        } else {
            Spinner_Motor.setPower(0);
        }
    }
}
