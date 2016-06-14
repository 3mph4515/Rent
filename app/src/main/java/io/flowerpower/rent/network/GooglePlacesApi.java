package io.flowerpower.rent.network;

import io.flowerpower.rent.model.AutoCompleteAddressResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public class GooglePlacesApi {

    private final static String API_URL = "https://maps.googleapis.com/maps/api/";
    private final static String GOOGLE_PLACES_API_KEY = "AIzaSyCzqzSq7Z02_4pfayqTzOaFk0gUh0YiI30";

    private GooglePlacesService service;

    private static volatile GooglePlacesApi Instance = null;

    public static GooglePlacesApi getInstance() {
        GooglePlacesApi localInstance = Instance;
        if (localInstance == null) {
            synchronized (GooglePlacesApi.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new GooglePlacesApi();
                }
            }
        }
        return localInstance;
    }

    public GooglePlacesApi() {
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
        service = retrofit.create(GooglePlacesService.class);
    }

    interface GooglePlacesService {
        @GET("place/autocomplete/json")
        Observable<AutoCompleteAddressResponse> getAutocompleteAddress(@Query("key") String apiKey, @Query("input") String query);
    }

    public Observable<AutoCompleteAddressResponse> getAutocompleteAddress(String query) {
        return service.getAutocompleteAddress(GOOGLE_PLACES_API_KEY, query);
    }

}
