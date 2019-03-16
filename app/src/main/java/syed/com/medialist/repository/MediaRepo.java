package syed.com.medialist.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import syed.com.medialist.dao.ApiService;
import syed.com.medialist.entity.Media;
import syed.com.medialist.retrofit.RetroClient;
import syed.com.medialist.util.InternetConnection;

public class MediaRepo {
    private static final String TAG = "MediaRepo";

    MutableLiveData<List<Media>> mMediaListLiveData;
    List<Media> mMediaList;

    public MediaRepo(Application application) {
        mMediaListLiveData = new MutableLiveData<>();
        loadUsersData(application);
    }

    private void loadUsersData(Application application) {
        Log.d(TAG,"Loading user data");
        if (InternetConnection.checkConnection(application)) {
            Log.d(TAG,"internet connection is available");
            ApiService api = RetroClient.getApiService();

            Call<List<Media>> call = api.getAllMedia();

            call.enqueue(new Callback<List<Media>>() {
                @Override
                public void onResponse(Call<List<Media>> call, Response<List<Media>> response) {

                    if(response.isSuccessful()) {
                      mMediaList = response.body();
                        Log.d(TAG, "response is okay");
                        printAllMediaIntoLogs(mMediaList);
                        mMediaListLiveData.setValue(mMediaList);
                    } else {
                        Log.d(TAG, "Something went wrong" + response.raw());

                    }
                }

                @Override
                public void onFailure(Call<List<Media>> call, Throwable t) {
                    t.printStackTrace();
                    Log.d(TAG, "callback failed");

                }
            });

        } else {
            Log.d(TAG, "check internet connection");
        }
    }

    //TODO need to cleanup
    private void printAllMediaIntoLogs(List<Media> mMediaList) {
        Log.d(TAG,"=========================================");
        for (int i = 0; i < mMediaList.size(); i++) {
            Log.d(TAG,"url: " + mMediaList.get(i).getUrl());
            Log.d(TAG,"title: " + mMediaList.get(i).getTitle());
            Log.d(TAG,"description: " + mMediaList.get(i).getDescription());
            Log.d(TAG,"duration: " + mMediaList.get(i).getDuration());
            Log.d(TAG,"thumbnail: " + mMediaList.get(i).getThumbnail());
        }
        Log.d(TAG,"=========================================\n\n\n");
    }

    public MutableLiveData<List<Media>> getAllMediaListLiveData () {
        return mMediaListLiveData;
    }

}
