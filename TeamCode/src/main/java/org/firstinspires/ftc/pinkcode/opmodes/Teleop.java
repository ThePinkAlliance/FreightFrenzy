package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {
    private Base Base;
    private Collector Collector;
    private PizzaSpinner PizzaSpinner;

    @Override
    public void init() {
        Base = new Base(hardwareMap);
        Collector = new Collector(hardwareMap);
        PizzaSpinner = new PizzaSpinner(hardwareMap);

        this.Base.configureBase(false);
    }

    @Override
    public void loop() {
        // drives the left, right side's of the drive train from joystick positions
        this.Base.tank(gamepad1.left_stick_y, gamepad1.right_stick_y);

        // when right bumper is pressed activate pizza spinner
        this.PizzaSpinner.spin(gamepad1.right_bumper);

        // send data to the dashboard about robot info
//        this.Dashboard.sendData("Collector Angle", this.Collector.getAngle());
//        this.Dashboard.sendData("Collector State", this.Collector.getState().toString());
//        this.Dashboard.sendData("Collector Intake State", this.Collector.getIntakeState().toString());

        // when button x is pressed move collector to bottom position
        if (gamepad2.x) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.BOTTOM);
        }

        // when button y is pressed move collector to bottom position
        if (gamepad2.y) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.MIDDLE);
        }

        // when button a is pressed move collector to bottom position
        if (gamepad2.a) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.COLLECT);
        }

        // when button b is pressed move collector to bottom position
        if (gamepad2.b) {
            this.Collector.setState(org.firstinspires.ftc.pinkcode.subsystems.Collector.CollectorStates.TOP);
        }

        telemetry.update();
    }
}