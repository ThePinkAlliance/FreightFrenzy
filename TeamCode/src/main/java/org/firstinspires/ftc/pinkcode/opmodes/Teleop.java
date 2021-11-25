package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Turret;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;
import org.firstinspires.ftc.pinkcode.utils.Utils;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends OpMode {
    private Base Base;
//    private Vision Vision;
    private Turret Turret;
    private Utils Utils;

    @Override
    public void init() {
        Base = new Base(hardwareMap);
//        Vision = new Vision(hardwareMap);
        Turret = new Turret(hardwareMap);
        Utils = new Utils();

//        this.Vision.activate();
        this.Base.configureBase(false);
    }

    @Override
    public void loop() {
        // drives the left, right side's of the drive train from joystick positions
        this.Base.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

//        List<Recognition> recs = Vision.GetAllRecOfLabel("red_shipping");
//
//        // for null statements
//        if (recs != null && !recs.isEmpty()) {
//            Recognition baseRec = recs.get(0);
//            double angleToObject = baseRec.estimateAngleToObject(AngleUnit.DEGREES);
//            double angleToRobot = Utils.math.calculateFromObject(angleToObject, Constants.CAMERA_POSITIONS.LEFT);
//            telemetry.addData("Angle: ", angleToObject);
//            telemetry.addData("Angle From Robot: ", angleToRobot);
//        }

        telemetry.update();
    }

}