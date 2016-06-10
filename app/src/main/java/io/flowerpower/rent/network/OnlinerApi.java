package io.flowerpower.rent.network;

import io.flowerpower.rent.model.apartment.ApartmentsResponse;
import io.flowerpower.rent.model.clustering.PointsResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public class OnlinerApi {

    private final static String API_URL = "https://ak.api.onliner.by/search/";
    private OnlinerService service;

    private static volatile OnlinerApi Instance = null;

    public static OnlinerApi getInstance() {
        OnlinerApi localInstance = Instance;
        if (localInstance == null) {
            synchronized (OnlinerApi.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new OnlinerApi();
                }
            }
        }
        return localInstance;
    }

    public OnlinerApi() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("OkHttp").d(message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();
        service = retrofit.create(OnlinerService.class);
    }

    interface OnlinerService {
        @Headers("Accept: application/json")
        @GET("apartments/")
        Observable<ApartmentsResponse> getApartments(@Query("bounds[lb][lat]") double lbLat, @Query("bounds[lb][long]") double lbLong,
                                                     @Query("bounds[rt][lat]") double rtLat, @Query("bounds[rt][long]") double rtLong
        );

        @Headers("Accept: application/vnd.geo+json; charset=utf-8")
        @GET("points/")
        Observable<PointsResponse> getPoints(@Query("bounds[lb][lat]") double lbLat, @Query("bounds[lb][long]") double lbLong,
                                             @Query("bounds[rt][lat]") double rtLat, @Query("bounds[rt][long]") double rtLong
        );
    }

    public Observable<ApartmentsResponse> getApartments(double lbLat, double lbLong, double rtLat, double rtLong) {
        return service.getApartments(lbLat, lbLong, rtLat, rtLong);
    }

    public Observable<PointsResponse> getPoints(double lbLat, double lbLong, double rtLat, double rtLong) {
        return service.getPoints(lbLat, lbLong, rtLat, rtLong);
    }
}
