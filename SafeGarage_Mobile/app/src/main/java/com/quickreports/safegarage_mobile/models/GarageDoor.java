package com.quickreports.safegarage_mobile.models;

public class GarageDoor {
    public static final int OPEN = 1;
    public static final int CLOSED = 2;
    public static final int TRANSITIONING = 3; // split up into closing and opening states?

    public static int state;

    public static String getState() {
        switch (state) {
            case OPEN:
                return "Open";
            case CLOSED:
                return "Closed";
            case TRANSITIONING:
                return "Transitioning";
            default:
                return "Unknown Garage Door State";
        }
    }
}
