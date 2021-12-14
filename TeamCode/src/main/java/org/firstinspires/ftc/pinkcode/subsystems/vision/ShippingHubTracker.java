package org.firstinspires.ftc.pinkcode.subsystems.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.pinkcode.Constants;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

public class ShippingHubTracker implements Runnable {
    private String[] WANTED_LABELS = {
            "red_shipping"
    };
    private TFObjectDetector detector;
    private List<Recognition> foundRecognitions = new ArrayList<>();
    private HardwareMap _map;
    private WebcamName Webcam;

    public ShippingHubTracker(HardwareMap _map) {
        this._map = _map;

        // initialize all vision systems
        initVuforia();
        initTfod();
    }


    private static final String VUFORIA_KEY =
            Constants.vuforiaKey;

    private VuforiaLocalizer vuforia;

    @Override
    public void run() {
        if (detector == null) {
            detector.activate();
        }

        if (detector != null) {
            List<Recognition> recs = detector.getUpdatedRecognitions();

            for (Recognition recognition: recs) {
                boolean isWanted = false;
                String label = recognition.getLabel();

                // Checks if the current recognition is a wanted object if so when it gets added to the list
                for (String l: WANTED_LABELS) {
                    if (label == l) {
                        isWanted = true;
                    }
                }

                if (isWanted) {
                    this.foundRecognitions.add(recognition);
                }
            }
        }
    }

    public void setWanted(String[] labels) {
        this.WANTED_LABELS = labels;
    }

    public List<Double> getFoundRecognitionAngles(AngleUnit angleUnit) {
        List<Double> currentList = new ArrayList<>();

        if (detector == null) {
            return null;
        }

        for (Recognition recognition: foundRecognitions) {
            currentList.add(recognition.estimateAngleToObject(angleUnit));
        }

        return currentList;
    }

    public List<Double> getFoundRecognitionAngles() {
        List<Double> currentList = new ArrayList<>();

        if (detector == null) {
            return null;
        }

        for (Recognition recognition: foundRecognitions) {
            currentList.add(recognition.estimateAngleToObject(AngleUnit.DEGREES));
        }

        return currentList;
    }

    public List<Recognition> getFoundRecognitions() {
        if (detector == null) {
            return null;
        }

        return this.foundRecognitions;
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = this.Webcam;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }
    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = this._map.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", this._map.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.94f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        detector = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        detector.loadModelFromAsset(Constants.TFOD_MODEL_ASSET_CUSTOM, Constants.LABELS_CUSTOM);
    }
}
