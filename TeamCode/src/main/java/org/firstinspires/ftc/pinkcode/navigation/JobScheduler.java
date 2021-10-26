package org.firstinspires.ftc.pinkcode.navigation;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import java.util.HashMap;

public class JobScheduler {
    private final HashMap<Integer, Pose2d> jobs = new HashMap();

    public Pose2d getJob(int i) {
        return jobs.get(i);
    }

    public Pose2d[] addJob(Pose2d job) {
        int length = jobs.size();

        jobs.put(length, job);

        return (Pose2d[]) jobs.values().toArray();
    }

    public void removeJob(int index) {
        jobs.remove(index);
    }
}
