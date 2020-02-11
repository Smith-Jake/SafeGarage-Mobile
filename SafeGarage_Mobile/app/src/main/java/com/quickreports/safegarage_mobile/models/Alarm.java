package com.quickreports.safegarage_mobile.models;

public class Alarm {

    private boolean isSmokeAlarming;
    private boolean isCoAlaraming;


    public Alarm(boolean isSmokeAlarming, boolean isCoAlaraming) {
        this.isSmokeAlarming = isSmokeAlarming;
        this.isCoAlaraming = isCoAlaraming;
    }

    public boolean isSmokeAlarming() {
        return isSmokeAlarming;
    }

    public boolean isCoAlaraming() {
        return isCoAlaraming;
    }

}
