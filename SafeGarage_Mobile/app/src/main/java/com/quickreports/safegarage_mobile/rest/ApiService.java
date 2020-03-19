package com.quickreports.safegarage_mobile.rest;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.quickreports.safegarage_mobile.models.StatusResponse;

public interface ApiService {
    // Get status replaces message received - Nick 2/27/20202
    @GET("getStatus")
    Single<StatusResponse> getStatus();

    /* Toggles the door to either open or close based on the remote, will return a boolean and report whether or not the
    door was successfully closed when attempted. */
    @GET ("toggleGarageDoor")
    Single<StatusResponse> toggleDoor();

    // Sets the automatic closing time of the garage door based on the time that the user picks within the app.
    @GET ("setCloseTime")
    Single<StatusResponse> setClosingTime(@Query("closeTime") String closingTime);
}

