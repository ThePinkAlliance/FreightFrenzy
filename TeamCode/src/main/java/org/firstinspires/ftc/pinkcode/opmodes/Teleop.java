package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.Robot;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Robot {
    @Override
    public void init() {
        this.Base.configureBase(false);
    }

    @Override
    public void loop() {
        this.Base.tank(gamepad1.left_stick_y, gamepad1.right_stick_y);
        this.PizzaSpinner.spin(gamepad1.right_bumper);

        telemetry.addData("collector angle", this.Collector.getAngle());

//        if (gamepad1.x) {
//            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.BOTTOM);
//        }

//        if (gamepad1.b) {
//            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.TOP);
//        }

        telemetry.update();
    }
}