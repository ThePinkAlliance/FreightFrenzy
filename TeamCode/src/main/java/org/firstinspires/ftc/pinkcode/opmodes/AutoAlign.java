package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.roadrunner.drive.MecanumDrive;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;
import org.firstinspires.ftc.pinkcode.utils.Utils;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@Autonomous(name = "Auto Align")
public class AutoAlign extends LinearOpMode {
    private MecanumDrive drive;
    private Vision vision;
    private Utils utils;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap);
        vision = new Vision(hardwareMap);
        utils = new Utils();

        vision.activate();

        waitForStart();

        while (opModeIsActive()) {
            List<Recognition> recs = vision.GetAllRecOfLabel("red_shipping");

            if (recs != null && !recs.isEmpty()) {
                for (Recognition rec: recs) {
                    double angleToObject = rec.estimateAngleToObject(AngleUnit.DEGREES);
                    double angleToRobot = utils.math.calculateFromObject(angleToObject, Constants.CAMERA_POSITIONS.FRONT);
                    double convertedAngle = utils.math.convertToRoadrunner(angleToRobot, Constants.CAMERA_POSITIONS.FRONT);

                    telemetry.addData("angleToRobot", angleToRobot);
                    telemetry.addData("angleToRobotConverted", convertedAngle);

                    telemetry.update();

                    Trajectory align = drive.trajectoryBuilder(new Pose2d()).lineToLinearHeading(new Pose2d(0, 0, Math.toRadians(convertedAngle))).build();

                    drive.followTrajectory(align);
                }
            }
        }
    }
}
