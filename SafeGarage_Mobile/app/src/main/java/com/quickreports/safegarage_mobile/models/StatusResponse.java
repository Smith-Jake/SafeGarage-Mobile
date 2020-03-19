package com.quickreports.safegarage_mobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusResponse {
    @Expose
    @SerializedName("co")
    public boolean co;

    @Expose
    @SerializedName("smoke")
    public boolean smoke;

    @Expose
    @SerializedName("close_time")
    public String close_time;

    @Expose
    @SerializedName("state")
    public int state;

    @Expose
    @SerializedName("temp")
    public double temp;
}
