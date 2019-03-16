package syed.com.medialist.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import syed.com.medialist.dao.ApiService;

public class RetroClient {

    private static final String ROOT_URL = "https://yent-mobile-take-home.herokuapp.com";

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
