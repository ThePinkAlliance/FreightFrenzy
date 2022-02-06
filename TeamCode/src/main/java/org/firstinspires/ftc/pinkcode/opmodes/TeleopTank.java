package org.firstinspires.ftc.pinkcode.opmodes;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.pinkcode.ArmConfig;
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

    private boolean didRumble = false;
    private ElapsedTime time = new ElapsedTime();
    private ElapsedTime lastUnlock = new ElapsedTime();
    private ElapsedTime heldTime = new ElapsedTime();
    private ElapsedTime shakedelay = new ElapsedTime();
    boolean lockHeld = false;
    boolean shakeStarted = false;

    /*
        kP – The proportional gain.
        kI – The integral gain.
        kD – The derivative gain.
    */
    private PIDFController feedforward = new PIDFController(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF);
    private double previousPosition = 0;
    private double currentPosition = 100;
    boolean max = false;

    boolean open = false;

    @Override
    public void init() {
        time.reset();

        this.base = new Base(hardwareMap);
        this.pizzaSpinner = new PizzaSpinner(hardwareMap);
        this.collector = new Collector(hardwareMap);
        this.dashboard = new Dashboard(hardwareMap);

        armTime.reset();

        this.base.configureBase(true);
    }
    @Override
    public void loop() {
        // this will queue 3 rumbles after 2 mins to inform the drivers that end game has started
        if (time.seconds() >= 90 && !didRumble) {
            gamepad2.rumbleBlips(4);
            gamepad1.rumbleBlips(4);

            didRumble = true;
        }

        this.pizzaSpinner.spin(gamepad2.right_trigger, gamepad2.left_trigger);
        this.base.tank(gamepad1.left_stick_y, gamepad1.right_stick_y);

        this.collector.setPower(setTicks(-currentPosition));

        if (this.collector.getCurrentTicks() > -150) {
            this.collector.setCollectorPosition(ArmConfig.armParkAngle);
        }

//        if (this.collector.getCurrentTicks() > -1000) {
//            this.collector.setCollectorPosition(ArmConfig.armDepositAngle);
//        }

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

        if (gamepad2.cross && currentPosition != 10) {
            currentPosition = 10;
            this.collector.setCollectorPosition(ArmConfig.armTravelAngle);
        }

        // when square button is pressed the arm will go into the deposit position
        if (gamepad2.triangle) {
            currentPosition = 1600;
            this.collector.setCollectorPosition(ArmConfig.armTravelAngle);
        }

        if (gamepad2.square) {
            shakeStarted = true;
            shakedelay.reset();
            currentPosition = 1700;
        }

        if (shakeStarted && shakedelay.milliseconds() >= 250) {
            currentPosition = 1600;
            shakeStarted = false;
        }

        if (gamepad2.dpad_up) {
            this.collector.setCollectorPosition(ArmConfig.armDepositAngle);
        } else if (gamepad2.dpad_down) {
            this.collector.setCollectorPosition(ArmConfig.armParkAngle);
        }

        if (!gamepad2.right_bumper) {
            heldTime.reset();
            lockHeld = false;
        }

        if (gamepad2.right_bumper && open && lastUnlock.milliseconds() > 300 && !lockHeld) {
            this.collector.setLockState(ArmConfig.lock_close);
            this.open = false;
            this.heldTime.reset();
            this.lastUnlock.reset();
        } else if (gamepad2.right_bumper && !open && lastUnlock.milliseconds() > 300 && !lockHeld) {
            this.collector.setLockState(ArmConfig.lock_open);
            this.open = true;
            this.heldTime.reset();
            this.lastUnlock.reset();
        } else if (gamepad2.right_bumper && heldTime.milliseconds() >= 250) {
            this.lockHeld = true;
        }

        armTime.reset();
        dashboardData.put("collector position", collector.getCurrentTicks());
        dashboardData.put("spinner velocity", pizzaSpinner.getVelocity());
        previousPosition = collector.getCurrentTicks();

        dashboard.sendData(dashboardData);
    }

    public double setTicks(double ticks) {
        return feedforward.calculate(this.collector.getCurrentTicks(), ticks);
    }
}
