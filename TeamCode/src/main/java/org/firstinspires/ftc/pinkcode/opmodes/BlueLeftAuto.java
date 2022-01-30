package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;
import org.firstinspires.ftc.pinkcode.utils.Utils;

@Autonomous(name = "blue left")
public class BlueLeftAuto extends LinearOpMode {
    private Base base;
    private PizzaSpinner pizzaSpinner;
    private Utils utils;

    @Override
    public void runOpMode() throws InterruptedException {
        base = new Base(hardwareMap);
        pizzaSpinner = new PizzaSpinner(hardwareMap);
        utils = new Utils();

        base.configureBase(false);

        waitForStart();
    }

    // this needs to be in the positive 90 and negative 90
    public long rotateAngle(double angle) {
        double time = angle / 43.5;

        if (angle > .1) {
            base.tank(1, -1);
        } else if (angle < -.1) {
            base.tank(-1, 1);
        }

        return (long) time;
    }

    public long moveInches(double inches, double power) {
        base.tank(power, power);
        return (long) utils.math.timeUntilReachedInches(inches);
    }
}
