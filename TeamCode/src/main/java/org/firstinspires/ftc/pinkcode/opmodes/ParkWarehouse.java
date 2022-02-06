package org.firstinspires.ftc.pinkcode.opmodes;

import static org.firstinspires.ftc.pinkcode.roadrunner.drive.DriveConstants.MAX_ANG_ACCEL;
import static org.firstinspires.ftc.pinkcode.roadrunner.drive.DriveConstants.MAX_ANG_VEL;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.Path;
import com.acmerobotics.roadrunner.path.PathBuilder;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionSegment;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.AutoPath;
import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.roadrunner.drive.SampleTankDrive;
import org.firstinspires.ftc.pinkcode.subsystems.Collector;
import org.firstinspires.ftc.pinkcode.subsystems.Dashboard;
import org.firstinspires.ftc.pinkcode.subsystems.Navigation;
import org.firstinspires.ftc.pinkcode.subsystems.PizzaSpinner;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.HashMap;
import java.util.Map;

@Autonomous(name = "Park Warehouse", group = "auto")
@Config
public class ParkWarehouse extends LinearOpMode {
    private Navigation nav;
    private SampleTankDrive tank;
    private Dashboard dashboard;
    private Collector collector;
    private PizzaSpinner pizzaSpinner;

    private static double x1 = 45;
    private static double y1 = -1;

    private PIDFController feedforward = new PIDFController(Constants.arm_kP, Constants.arm_kI, Constants.arm_kD, Constants.arm_kF);
    private double previousPosition = 0;
    private double currentPosition = 100;

    private enum STAGES {
        SHIPPING,
        DUCKS,
        PARK,
        STOP
    }

    private STAGES stages = STAGES.DUCKS;

    private double currentArmPosition = 100;

    private Runnable climberTask = new Runnable() {
        @Override
        public void run() {
            collector.setPower(-setTicks(currentPosition));
        }
    };
    private Thread climberThread = new Thread(climberTask);

    private double previousCounts = 0;
    private double linearSpeed = 0;
    boolean max = false;  //this is best line imo imo
    private Map<String, Object> dashboardData = new HashMap<>();

    private Pose2d startingPose = new Pose2d();

    @Override
    public void runOpMode() throws InterruptedException {
        this.nav = new Navigation(hardwareMap);
        this.collector = new Collector(hardwareMap);
        this.dashboard = new Dashboard(hardwareMap);
        this.tank = new SampleTankDrive(hardwareMap);
        this.pizzaSpinner = new PizzaSpinner(hardwareMap);
        this.nav.imu.startAccelerationIntegration(new Position(), new Velocity(), 100);

        this.nav.resetEncoders();

        waitForStart();

        Trajectory t = this.tank.trajectoryBuilder(startingPose).forward(31).addDisplacementMarker(() -> {
            collector.setPower(-setTicks(1000));
        }).build();
        Trajectory e = this.tank.trajectoryBuilder(t.end()).splineTo(new Vector2d(x1, y1), 0).build();

        this.tank.followTrajectory(t);
        tank.setMotorPowers(1, 1);
        this.tank.followTrajectory(e);

        dashboard.sendData(dashboardData);
    }

    public double setTicks(double ticks) {
        return feedforward.calculate(this.collector.getCurrentTicks(), ticks);
    }
}
