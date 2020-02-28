package com.quickreports.safegarage_mobile;


/* Currently a skeleton of the methods that need added across all fragments, all will be worked on as the corrisponding
   firmware code has been created. Class should be able to be called from all fragments. -- Nick */


import com.quickreports.safegarage_mobile.models.StatusResponse;
import com.quickreports.safegarage_mobile.rest.ApiService;
import com.quickreports.safegarage_mobile.rest.interfaces.apiError;
import com.quickreports.safegarage_mobile.rest.interfaces.apiSuccess;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackEnd {
// FIRE BASE CODE IS GOING HERE TOO
//	FirebaseDatabase database = FirebaseDatabase.getInstance();




	public static final String BASE_URL = "https://sg.milligan.dev/";

	public static Retrofit retroFit = null;

	private ApiService apiService;

	// This method creates an instance of Retrofit - Nick
	public void retrofitSetup(){

		if(retroFit == null)
		{
			retroFit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build();
			apiService = retroFit.create(ApiService.class);
		}
	}

	// Method replaces messageReceived() - Nick 2/27/2020
	public void getStatus(final apiError err , final apiSuccess success){
		final Single<StatusResponse> status = apiService.getStatus();
		status
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new SingleObserver<StatusResponse>() {
					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onSuccess(StatusResponse statusResponse) {
						if(success == null)
						{return;}
						success.run(statusResponse);
					}

					@Override
					public void onError(Throwable e) {
						if(err == null)
						{return;}
						err.run(e.getMessage());
					}
				});
	}
	
	/* Toggles the door to either open or close based on the remote, will return a boolean and report whether or not the 
	door was successfully closed when attempted. */
	public void toggleDoor(){}
	
	// Sets the automatic closing time of the garage door based on the time that the user picks within the app.
	public void setClosingTime(String closingTime){}
}