package com.example.hardik.automateddriverdrowsinesscontrol;

public class SleepData {
    private long startTime;
    private long endTime;
    private long duration;
    private int quality;

    // Constructor
    public SleepData(long startTime, long endTime, long duration, int quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.quality = quality;
    }

    // Getters and setters
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
