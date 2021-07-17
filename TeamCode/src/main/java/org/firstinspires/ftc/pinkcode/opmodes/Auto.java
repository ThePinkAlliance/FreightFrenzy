package org.firstinspires.ftc.pinkcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.pinkcode.RobotAsync;

@Autonomous(name = "Auto", group = "auto")
public class Auto extends RobotAsync {
    enum TrajectoryState {
        MOVE_TO_START,
        FINISH
    }

    TrajectoryState trajectoryState = TrajectoryState.MOVE_TO_START;

    @Override
    public void runOpMode() throws InterruptedException {
        Trajectory _move_to_start = this.Roadrunner.mecanumDrive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(20, 20), Math.toRadians(90))
                .build();

        Trajectory _finish = this.Roadrunner.mecanumDrive.trajectoryBuilder(_move_to_start.end())
                .splineTo(new Vector2d(50, 0), Math.toRadians(180))
                .build();

        if (this.opModeIsActive()) {
            switch (trajectoryState) {
                case MOVE_TO_START:
                    this.Roadrunner.mecanumDrive.followTrajectory(_move_to_start);
                break;

                case FINISH:
                    this.Roadrunner.mecanumDrive.followTrajectory(_finish);
                    break;
            }
        }
    }
}
