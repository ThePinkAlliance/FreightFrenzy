package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.pinkcode.Constants;

public class Collector extends HardwareMap {
    public enum CollectorStates {
        COLLECT,
        BOTTOM,
        MIDDLE,
        TOP
    }
    public enum CollectorIntakeStates {
        RUN,
        REVERSE,
        STOP
    }

    private double Collector_Target_Position = 2048;

    // this is the current state for the bottom portion of the collector arm
    private CollectorStates currentState = CollectorStates.COLLECT;

    // this is the current state for the collector of the intake
    private CollectorIntakeStates currentIntakeState = CollectorIntakeStates.RUN;

    public Collector(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    // this will run though the collector states
    public void toggleState() {
        CollectorStates state = nextState();

        setState(state);
    }
    // this will run though the intake states
    public void toggleIntakeState() {
        CollectorIntakeStates state = nextIntakeState();

        setIntakeState(state);
    }

    public void drive(double power) {
        this.Collector_Motor_L.setPower(power / 2);
        this.Collector_Motor_R.setPower(power / 2);
    }

    // this will set the state of the arm
    public void setState(CollectorStates position) {
        this.currentState = position;

        if (position == CollectorStates.COLLECT) {
            this.Collector_Motor_L.setTargetPosition(Constants.CollectorPositions.COLLECT);
            this.Collector_Motor_R.setTargetPosition(Constants.CollectorPositions.COLLECT);
        } else if (position == CollectorStates.BOTTOM) {
            this.Collector_Motor_L.setTargetPosition(Constants.CollectorPositions.BOTTOM);
            this.Collector_Motor_R.setTargetPosition(Constants.CollectorPositions.BOTTOM);
        } else if (position == CollectorStates.MIDDLE) {
            this.Collector_Motor_L.setTargetPosition(Constants.CollectorPositions.MIDDLE);
            this.Collector_Motor_R.setTargetPosition(Constants.CollectorPositions.MIDDLE);
        } else if (position == CollectorStates.TOP) {
            this.Collector_Motor_L.setTargetPosition(Constants.CollectorPositions.TOP);
            this.Collector_Motor_R.setTargetPosition(Constants.CollectorPositions.TOP);
        }
    }

    // moves the motor to a point defined in degrees
    public void setAngle(double angle) {
        int angleTicks = (int) ((Constants.Ticks / 360) * angle);

        Collector_Motor_L.setTargetPosition(angleTicks);
        Collector_Motor_R.setTargetPosition(angleTicks);
    }

    public void lockPosition() {
//        int angleTicks = (int) ((Constants.Ticks / 360) * angle);
        double error = Collector_Motor_R.getCurrentPosition() - Collector_Motor_L.getCurrentPosition();

        double power = (2.1 * error) - (1.5 * Collector_Motor_R.getCurrentPosition());

        power = Range.clip(power, 0.3, 1);

        Collector_Motor_R.setPower(power);
        Collector_Motor_L.setPower(power);
    }

    // this will calculate the angle from the amount of ticks the motor has moved.
    public double getAngle(DcMotor _motor) {
        int currentTicks = _motor.getCurrentPosition();

        return ((currentTicks / Constants.Ticks) * 360);
    }

    // this will set the state of the collector
    public void setIntakeState(CollectorIntakeStates state) {
        this.currentIntakeState = state;

        if (state == CollectorIntakeStates.RUN) {
            this.Collector_Intake_Motor.setPower(Constants.CollectorGrabberPositions.OPEN);
        } else if (state == CollectorIntakeStates.REVERSE) {
            this.Collector_Intake_Motor.setPower(Constants.CollectorGrabberPositions.REVERSE);
        } else {
            this.Collector_Intake_Motor.setPower(0);
        }
    }

    // retrieves the current state of the collector
    public CollectorStates getState() {
        return this.currentState;
    }

    // retrieves the current state of the intake
    public CollectorIntakeStates getIntakeState() {
        return this.currentIntakeState;
    }

    private CollectorIntakeStates nextIntakeState() {
        if (currentIntakeState == CollectorIntakeStates.REVERSE) {
            return CollectorIntakeStates.RUN;
        } else {
            return CollectorIntakeStates.REVERSE;
        }
    }

    // return the next state
    private CollectorStates nextState() {
        if (currentState == CollectorStates.COLLECT) {
            return CollectorStates.BOTTOM;
        } else if (currentState == CollectorStates.BOTTOM) {
            return CollectorStates.MIDDLE;
        } else if (currentState == CollectorStates.MIDDLE) {
            return CollectorStates.TOP;
        } else {
            return CollectorStates.COLLECT;
        }
    }
}
