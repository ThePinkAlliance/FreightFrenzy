package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.Robot;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Robot {
    @Override
    public void init() {
        this.Base.configureBase(false);
    }

    @Override
    public void loop() {
        // drives the left, right side's of the drive train from joystick positions
        this.Base.tank(gamepad1.left_stick_y, gamepad1.right_stick_y);

        // when right bumper is pressed activate pizza spinner
        this.PizzaSpinner.spin(gamepad1.right_bumper);

        telemetry.addData("collector angle", this.Collector.getAngle());

        // when button x is pressed move collector to bottom position
        if (gamepad1.x) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.BOTTOM);
        }

        // when button y is pressed move collector to bottom position
        if (gamepad1.y) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.MIDDLE);
        }

        // when button a is pressed move collector to bottom position
        if (gamepad1.a) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.COLLECT);
        }

        // when button b is pressed move collector to bottom position
        if (gamepad1.b) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.TOP);
        }

        telemetry.update();
    }
}