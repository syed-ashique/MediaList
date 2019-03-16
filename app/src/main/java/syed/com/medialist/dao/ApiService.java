package syed.com.medialist.dao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import syed.com.medialist.entity.Media;

public interface ApiService {

    @GET("/videos.json")
    Call<List<Media>> getAllMedia();
}
