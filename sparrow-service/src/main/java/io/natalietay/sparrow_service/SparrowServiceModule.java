package io.natalietay.sparrow_service;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Natalie on 19/9/17.
 */

@Module
public class SparrowServiceModule {
    // 1. we are providing this zen service to the app by indicating @Provides
    // 2. dagger knows that the OkHttpClient and ScalarsConverterFactory is provided
    // (in this file), thus knows how to construct this ZenService object
    // 3. the ZenConfig is implemented in the app (see NinjaApplicationConfig),
    // it is provided in a separate module (see NinjaApplicationModule), which dagger constructs for the app
    @Provides
    GithubService provideZenService(OkHttpClient loggingInterceptor,
                                    ScalarsConverterFactory scalarFactory,
                                    GithubConfig githubConfig) {
        return new Retrofit.Builder()

                // this allows us to see the OkHttp logs in the
                // Android Monitor when we make API calls,
                // we can change the log level in the provider below
                .client(loggingInterceptor)

                // the ZenConfig interface is defined in the service,
                // and implemented in the application.
                .baseUrl(githubConfig.getBaseUrl())

                // the scalar factory allows us to easily use a string as the body,
                // for the "/zen" endpoint
                .addConverterFactory(scalarFactory)

                // this converts the typical call to an observable (see zen service response signature)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build()
                .create(GithubService.class);
    }

    @Provides
    OkHttpClient provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    @Provides
    ScalarsConverterFactory getScalarsConverterFactory() {
        return ScalarsConverterFactory.create();
    }
}
