package syed.com.medialist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import syed.com.medialist.entity.Media;
import syed.com.medialist.repository.MediaRepo;

public class MediaViewModel extends AndroidViewModel {
    public static final String TAG = "MediaViewModel";

    MutableLiveData<List<Media>> mMediaList;

    public MediaViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "constructor -> ----------- <-");
        MediaRepo mediaRepo = new MediaRepo(application);
        mMediaList = mediaRepo.getAllMediaListLiveData();
    }

    public MutableLiveData<List<Media>> getMediaList () {
        return mMediaList;
    }
}
