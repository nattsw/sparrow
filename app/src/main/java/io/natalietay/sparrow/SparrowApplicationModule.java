package io.natalietay.sparrow;

import dagger.Module;
import dagger.Provides;
import io.natalietay.sparrow_service.GithubConfig;

/**
 * Created by Natalie on 20/9/17.
 */

@Module
public class SparrowApplicationModule {
    @Provides
    GithubConfig provideZenConfig() {
        return new SparrowApplicationConfig();
    }
}
