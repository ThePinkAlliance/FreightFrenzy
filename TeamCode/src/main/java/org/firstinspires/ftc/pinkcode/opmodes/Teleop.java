package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {
    private Base Base;
    private Vision Vision;

    @Override
    public void init() {
        Base = new Base(hardwareMap);
        Vision = new Vision(hardwareMap);

        this.Vision.activate();
        this.Base.configureBase(false);
    }

    @Override
    public void loop() {
        // drives the left, right side's of the drive train from joystick positions
        this.Base.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        telemetry.update();
    }
}