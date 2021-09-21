package org.firstinspires.ftc.pinkcode.navigation.threads;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.pinkcode.navigation.Cache;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

public class ImuThread implements Runnable {
    private BNO055IMU imu;

    public ImuThread(BNO055IMU imu) {
        this.imu = imu;
    }

    @Override
    public void run() {
        Cache.currentAcceleration = imu.getAcceleration();
        Cache.currentAngle = imu.getAngularOrientation().toAxesOrder(AxesOrder.XYZ);
        Cache.currentImuPosition = imu.getPosition();
    }
}
