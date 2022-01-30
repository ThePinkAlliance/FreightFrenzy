package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;

@TeleOp(name = "Teleop Tank", group = "teleop")
public class TeleopTank extends OpMode {
    private Base base;
    private PizzaSpinner pizzaSpinner;
    private Collector collector;

    private double currentPosition = 0;

    @Override
    public void init() {
        this.base = new Base(hardwareMap);
        this.pizzaSpinner = new PizzaSpinner(hardwareMap);
        this.collector = new Collector(hardwareMap);
    }

    @Override
    public void loop() {
        this.pizzaSpinner.spin(gamepad2.right_bumper, gamepad2.left_bumper);
        this.base.tank(gamepad1.left_stick_y, gamepad1.right_stick_y);

        if (gamepad1.right_trigger > 0.1) {
            this.collector.setIntakeState(Collector.CollectorIntakeStates.RUN);
        } else {
            this.collector.setIntakeState(Collector.CollectorIntakeStates.STOP);
        }

        if (gamepad1.left_trigger > 0.1) {
            this.collector.setIntakeState(Collector.CollectorIntakeStates.REVERSE);
        } else if (!(gamepad1.right_trigger > 0.1)) {
            this.collector.setIntakeState(Collector.CollectorIntakeStates.STOP);
        }

        if (gamepad2.left_trigger > 0.1) {
            this.collector.setIntakeState(Collector.CollectorIntakeStates.REVERSE);
        } else if (!(gamepad1.right_trigger > 0.1) && !(gamepad2.left_trigger > 0.1)) {
            this.collector.setIntakeState(Collector.CollectorIntakeStates.STOP);
        }

        if (gamepad2.y) {
            double pos = this.collector.getCollectorPosition();

            this.collector.setCollectorPosition(0.2);
        }

        if (gamepad2.b) {
            this.collector.setCollectorPosition(0.31);
        }

        if (gamepad2.a) {
            double pos = this.collector.getCollectorPosition();

            this.collector.setCollectorPosition(0.5);
        }

        if (gamepad2.x) {
            double pos = this.collector.getCollectorPosition();

            this.collector.setCollectorPosition(0.45);
        }

        if (gamepad2.left_stick_y > 0.1) {
            this.collector.setPower(0.4);
        } else {
            this.collector.setPower(0);
        }

        if (gamepad2.left_stick_y < -0.1) {
            this.collector.setPower(-0.5);
        } else if (!(gamepad2.left_stick_y > 0.1)) {

            // this is to fight gravity very jank but there was not that much time to fix the issue
            this.collector.setPower(-0.13);
        }
    }
}
