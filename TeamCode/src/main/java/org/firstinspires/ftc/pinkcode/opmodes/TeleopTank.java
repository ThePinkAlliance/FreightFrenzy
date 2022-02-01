package org.firstinspires.ftc.pinkcode.opmodes;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.Dashboard;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;

import java.util.HashMap;
import java.util.Map;

@TeleOp(name = "Teleop Tank", group = "teleop")
public class TeleopTank extends OpMode {
    private Base base;
    private PizzaSpinner pizzaSpinner;
    private Collector collector;
    private ElapsedTime armTime = new ElapsedTime();
    private Dashboard dashboard;
    private Map<String, Object> dashboardData = new HashMap<>();

    /*
        kP – The proportional gain.
        kI – The integral gain.
        kD – The derivative gain.
    */
    private PIDFController feedforward = new PIDFController(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF);
    private double previousPosition = 0;
    private double currentPosition = 0;

    @Override
    public void init() {
        this.base = new Base(hardwareMap);
        this.pizzaSpinner = new PizzaSpinner(hardwareMap);
        this.collector = new Collector(hardwareMap);
        this.dashboard = new Dashboard(hardwareMap);

        armTime.startTime();

        this.base.configureBase(true);
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
            this.collector.setCollectorPosition(0.2);
        }

        if (gamepad2.b) {
            this.collector.setCollectorPosition(0.31);
        }

        if (gamepad2.a) {
            this.collector.setCollectorPosition(0.5);
        }

        if (gamepad2.x) {
            this.collector.setCollectorPosition(0.45);
        }


        if (gamepad2.left_stick_y > 0.1) {
            this.collector.setPower(0.4);
        } else {
            this.collector.setPower(0);
        }

        double power = feedforward.calculate(this.collector.getCurrentTicks(), Constants.arm_targetTicks);

        collector.setPower(power);

        armTime.reset();

        previousPosition = collector.getCurrentTicks();

        dashboard.sendData(dashboardData);
    }
}
