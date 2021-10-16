package org.firstinspires.ftc.pinkcode.subsystems;

import org.firstinspires.ftc.pinkcode.Constants;

public class Collector extends HardwareMap {
    public enum CollectorStates {
        COLLECT,
        BOTTOM,
        MIDDLE,
        TOP
    }
    public enum CollectorGrabberStates {
        OPEN,
        CLOSE
    }
    private CollectorStates currentState = CollectorStates.COLLECT;
    private CollectorGrabberStates currentGrabberState = CollectorGrabberStates.OPEN;

    public Collector(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void toggleState() {
        CollectorStates state = nextState();

        setState(state);
    }

    public void toggleGrabberState() {
        CollectorGrabberStates state = nextGrabberState();

        setGrabberState(state);
    }

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

    public void setGrabberState(CollectorGrabberStates state) {
        this.currentGrabberState = state;

        if (state == CollectorGrabberStates.OPEN) {
            this.Collector_Servo.setPosition(Constants.CollectorGrabberPositions.OPEN);
        } else {
            this.Collector_Servo.setPosition(Constants.CollectorGrabberPositions.CLOSE);
        }
    }

    public CollectorStates getState() {
        return this.currentState;
    }

    public CollectorGrabberStates getGrabberState() {
        return this.currentGrabberState;
    }

    private CollectorGrabberStates nextGrabberState() {
        if (currentGrabberState == CollectorGrabberStates.CLOSE) {
            return CollectorGrabberStates.OPEN;
        } else {
            return CollectorGrabberStates.CLOSE;
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
