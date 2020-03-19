package com.quickreports.safegarage_mobile.models;

public class CloseTime {
    public static String time;

    /**
     * Converts Integer values of hour and minute into a human-readable string
     * in the HH:mm format
     * @param hour
     * @param minute
     * @return
     */
    public static String closingTimeToString(int hour, int minute) {
        // Include leading 0 if necessary
        String hourString = Integer.toString(hour);
        if (hour < 10)
            hourString = "0" + hourString;

        // Include leading 0 if necessary
        String minuteString = Integer.toString(minute);
        if (minute < 10)
            minuteString = "0" + minuteString;

        return hourString + ":" + minuteString;
    }
}
