package org.firstinspires.ftc.pinkcode.subsystems;

public class Turret extends HardwareMap {
    public Turret(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);
    }

    public void setCurrentAngle(double angle) {
        double error = getCurrentAngle() - angle;

        double power = (2.1 * error) - (1.5 * Turret_Rotate_Motor_L.getCurrentPosition());

        Turret_Rotate_Motor_L.setPower(power);
        Turret_Rotate_Motor_R.setPower(power);
    }

    public double getCurrentAngle() {
        return Turret_Rotate_Motor_L.getCurrentPosition();
    }
}
