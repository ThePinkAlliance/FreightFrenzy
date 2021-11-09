package org.firstinspires.ftc.pinkcode.subsystems;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import java.util.Map;

public class Dashboard extends HardwareMap {
    private final FtcDashboard dash;

    public Dashboard(com.qualcomm.robotcore.hardware.HardwareMap _map) {
        super(_map);

        this.dash = FtcDashboard.getInstance();
    }

    public void sendBitmap(Bitmap bitmap) {
        dash.sendImage(bitmap);
    }

    public void sendData(String label, Object data) {
        TelemetryPacket packet = new TelemetryPacket();

        packet.put(label, data);
        dash.sendTelemetryPacket(packet);
    }

    public void sendData(Map<String, Object> data) {
        TelemetryPacket packet = new TelemetryPacket();

        packet.putAll(data);
        dash.sendTelemetryPacket(packet);
    }

    public void addLine(String data) {
        TelemetryPacket packet = new TelemetryPacket();

        packet.addLine(data);
        dash.sendTelemetryPacket(packet);
    }
}
