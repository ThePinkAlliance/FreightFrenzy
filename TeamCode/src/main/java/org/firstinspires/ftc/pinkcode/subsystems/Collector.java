package org.firstinspires.ftc.pinkcode.subsystems;

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
        HALT
    }

    // this is the current state for the bottom portion of the collector arm
    private CollectorStates currentState = CollectorStates.COLLECT;

    // this is the current state for the collector of the intake
    private CollectorIntakeStates currentIntakeState = CollectorIntakeStates.RUN;

    public Collector(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    // this will run thought the collector states
    public void toggleState() {
        CollectorStates state = nextState();

        setState(state);
    }

    // this will run though the intake states
    public void toggleIntakeState() {
        CollectorIntakeStates state = nextIntakeState();

        setIntakeState(state);
    }

    // this will set the state of the arm
    public void setState(CollectorStates position) {
        this.currentState = position;

        if (position == CollectorStates.COLLECT) {
            this.Collector_Motor.setTargetPosition(Constants.CollectorPositions.COLLECT);
        } else if (position == CollectorStates.BOTTOM) {
            this.Collector_Motor.setTargetPosition(Constants.CollectorPositions.BOTTOM);
        } else if (position == CollectorStates.MIDDLE) {
            this.Collector_Motor.setTargetPosition(Constants.CollectorPositions.MIDDLE);
        } else if (position == CollectorStates.TOP) {
            this.Collector_Motor.setTargetPosition(Constants.CollectorPositions.TOP);
        }
    }

    // moves the motor to a point defined in degrees
    public void setAngle(double angle) {
        int angleTicks = (int) ((Constants.Ticks / 360) * angle);

        Collector_Motor.setTargetPosition(angleTicks);
    }

    // this will calculate the angle from the amount of ticks the motor has moved.
    public double getAngle() {
        int currentTicks = Collector_Motor.getCurrentPosition();

        return ((currentTicks / Constants.Ticks) * 360);
    }

    // this will set the state of the collector
    public void setIntakeState(CollectorIntakeStates state) {
        this.currentIntakeState = state;

        if (state == CollectorIntakeStates.RUN) {
            this.Collector_Servo.setPosition(Constants.CollectorGrabberPositions.OPEN);
        } else {
            this.Collector_Servo.setPosition(Constants.CollectorGrabberPositions.CLOSE);
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
        if (currentIntakeState == CollectorIntakeStates.HALT) {
            return CollectorIntakeStates.RUN;
        } else {
            return CollectorIntakeStates.HALT;
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
