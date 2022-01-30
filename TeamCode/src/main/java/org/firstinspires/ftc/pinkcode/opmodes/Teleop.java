package org.firstinspires.ftc.pinkcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.pinkcode.subsystems.Base;
import org.firstinspires.ftc.pinkcode.subsystems.Vision;
import org.firstinspires.ftc.pinkcode.subsystems.vision.ShippingHubTracker;
import org.firstinspires.ftc.pinkcode.utils.Utils;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@TeleOp(name = "Teleop", group = "Teleop")
@Disabled
public class Teleop extends OpMode {
    private Base Base;
    private ShippingHubTracker Vision;
    private Utils Utils;
    private Thread VisionThread;

    @Override
    public void init() {
        Base = new Base(hardwareMap);
        Vision = new ShippingHubTracker(hardwareMap);

        // Create the new thread and start it in a new process
        // By creating a thread we free up the current thread for other things.
        this.VisionThread = new Thread(Vision);
        this.VisionThread.start();

        this.Base.configureBase(false);
    }

    // variables used in the following
    boolean checkedOnce = false;
    double focalLength = 2;
    double widthOfObject = 15; // I think it is 15 inches, the object is the shipping hub
    @Override
    public void loop() {
        // drives the left, right side's of the drive train from joystick positions
        this.Base.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        List<Recognition> recs = Vision.getFoundRecognitions();

//        List<Recognition> recs = Vision.GetAllRecOfLabel("red_shipping");

        // for null statements
        if (recs != null && !recs.isEmpty()) {
            Recognition baseRec = recs.get(0);
            double angleToObject = baseRec.estimateAngleToObject(AngleUnit.DEGREES);
            double angleToRobot = Utils.math.calculateFromObject(angleToObject, Constants.CAMERA_POSITIONS.FRONT);
            telemetry.addData("Angle: ", angleToObject);
            telemetry.addData("Angle From Robot: ", angleToRobot);

            // to find the distance, we need to find the focal length
            // focal length is easy to find: F = (width of object (px) * distance from object) / widthOfObject
            if (!checkedOnce) {
                int imageWidthPX = baseRec.getImageWidth();
                int distance = 24; //inches
                focalLength = (imageWidthPX * distance) / widthOfObject;
                checkedOnce = true;

                telemetry.addData("focalLength", focalLength);
            }
            // Once we have focal length, the equation for distance(D) is as follows: D = (Width * Focal length)/ new pixel width
            int newPixelSize = baseRec.getImageWidth();
            double distanceToObject = (widthOfObject * focalLength) / newPixelSize;
            telemetry.addData("image width", newPixelSize);
            telemetry.addData("Distance From Object: ", (0.2413 / Math.tan(angleToObject)));
        }

        telemetry.update();
    }

}