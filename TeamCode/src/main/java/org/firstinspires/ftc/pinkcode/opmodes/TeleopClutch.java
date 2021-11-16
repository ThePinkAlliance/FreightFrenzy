package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;

@TeleOp(name = "Teleop Clutch", group = "Teleop")
public class TeleopClutch extends OpMode {
    private Base Base;
    private Collector Collector;
    private PizzaSpinner PizzaSpinner;

    private double clutch = 1;
    private double reduction_pizza_spinner = 1;

    @Override
    public void init() {
        Base = new Base(hardwareMap);
        Collector = new Collector(hardwareMap);
        PizzaSpinner = new PizzaSpinner(hardwareMap);

        this.Base.configureBase(false);
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper) {
            clutch = 2;
        } else {
            clutch = 1;
        }

        // drives the left, right side's of the drive train from joystick positions
        this.Base.tank(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        // when right bumper is pressed activate pizza spinner
        this.PizzaSpinner.spin(gamepad2.right_bumper, reduction_pizza_spinner);
        this.PizzaSpinner.reverse(gamepad1.left_bumper);

        this.Collector.drive(-gamepad2.left_stick_y);

        if (gamepad2.a) {
            reduction_pizza_spinner = 2.2;
        } else {
            reduction_pizza_spinner = 0;
        }

        if (gamepad2.right_trigger >= .2) {
            this.Collector.setIntakeState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorIntakeStates.RUN);
        } else {
            this.Collector.setIntakeState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorIntakeStates.STOP);
        }

        if (gamepad2.left_trigger >= .2) {
            this.Collector.setIntakeState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorIntakeStates.REVERSE);
        } else if (gamepad2.right_trigger <= .2) {
            this.Collector.setIntakeState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorIntakeStates.STOP);
        }

        telemetry.update();
    }
}