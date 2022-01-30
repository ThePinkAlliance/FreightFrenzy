package org.firstinspires.ftc.pinkcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.pinkcode.roadrunner.util.Encoder;

public class OdoemtryPod {
    private boolean isRetracted;
    private int minPosition = 0;
    private int maxPosition = 50;

    private Encoder encoder;
    private Servo servo;

    public OdoemtryPod(Servo servo, Encoder encoder) {
        this.encoder = encoder;
        this.servo = servo;

        if (servo.getPosition() > (minPosition + 1)) {
            this.isRetracted = true;
        }
    }

    public OdoemtryPod(Servo servo) {
        this.servo = servo;

        if (servo.getPosition() > (minPosition + 1)) {
            this.isRetracted = true;
        }
    }

    public OdoemtryPod(Servo servo, Encoder encoder, int minPosition, int maxPosition) {
        this.encoder = encoder;
        this.servo = servo;

        this.minPosition = minPosition;
        this.maxPosition = maxPosition;

        if (servo.getPosition() > (minPosition + 1)) {
            this.isRetracted = true;
        }
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public int getCounts() {
        if (encoder != null) {
            return this.encoder.getCurrentPosition();
        }

        return 0;
    }

    public double getCorrectedVelocity() {
        if (encoder != null) {
            return this.encoder.getCorrectedVelocity();
        }

        return 0.0;
    }

    public void setDirection(Encoder.Direction direction) {
        if (encoder != null) {
            this.encoder.setDirection(direction);
        }
    }

    public void home() {
        this.servo.setPosition(this.minPosition);
        this.isRetracted = false;
    }

    public void retract() {
        this.servo.setPosition(this.maxPosition);
        this.isRetracted = true;
    }
}
