package me.arkadii.gumenniy.ledzeppelinalbums.data.di;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.arkadii.gumenniy.ledzeppelinalbums.data.Constants;
import me.arkadii.gumenniy.ledzeppelinalbums.data.ResponseErrorHandler;
import me.arkadii.gumenniy.ledzeppelinalbums.data.SessionDataRepository;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.RestApi;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.RetrofitApi;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.SessionRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public RetrofitApi provideRetrofitApi(Retrofit retrofit) {
        return retrofit.create(RetrofitApi.class);
    }

    @Provides
    @Singleton
    public RestApi provideRestApi(RetrofitApi api) {
        return new RestApi(api);
    }

    @Provides
    @Singleton
    public SessionRepository provideRepository(RestApi restApi) {
        return new SessionDataRepository(restApi);
    }

    @Provides
    @Singleton
    public ResponseErrorHandler provideErrorHandler(Context context) {
        return new ResponseErrorHandler(context);
    }
}
