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

    public int getCounts() {
        return this.encoder.getCurrentPosition();
    }

    public double getCorrectedVelocity() {
        return this.encoder.getCorrectedVelocity();
    }

    public void setDirection(Encoder.Direction direction) {
        this.encoder.setDirection(direction);
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
