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
        OPEN,
        CLOSE
    }
    private CollectorStates currentState = CollectorStates.COLLECT;
    private CollectorIntakeStates currentGrabberState = CollectorIntakeStates.OPEN;

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
        CollectorIntakeStates state = nextGrabberState();

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

    public void setAngle(double angle) {
        int currentTicks = Collector_Motor.getCurrentPosition();
        int angleTicks = (int) ((Constants.towerGearWidth * angle * Math.PI / 360) * Constants.Ticks);

        Collector_Motor.setTargetPosition(angleTicks);
    }

    public double getAngle() {
        int currentTicks = Collector_Motor.getCurrentPosition();

        return (Constants.Ticks / 360) * currentTicks;
    }

    // this will set the state of the collector
    public void setIntakeState(CollectorIntakeStates state) {
        this.currentGrabberState = state;

        if (state == CollectorIntakeStates.OPEN) {
            this.Collector_Servo.setPosition(Constants.CollectorGrabberPositions.OPEN);
        } else {
            this.Collector_Servo.setPosition(Constants.CollectorGrabberPositions.CLOSE);
        }
    }

    public CollectorStates getState() {
        return this.currentState;
    }

    public CollectorIntakeStates getGrabberState() {
        return this.currentGrabberState;
    }

    private CollectorIntakeStates nextGrabberState() {
        if (currentGrabberState == CollectorIntakeStates.CLOSE) {
            return CollectorIntakeStates.OPEN;
        } else {
            return CollectorIntakeStates.CLOSE;
        }
    }

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
