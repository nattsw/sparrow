package io.natalietay.sparrow;

import io.natalietay.sparrow_service.*;

import static io.natalietay.sparrow.BuildConfig.FLAVOR;

/**
 * Created by Natalie on 20/9/17.
 */

public class SparrowApplicationConfig implements GithubConfig {
    // in the app build.gradle file,
    // we've defined an endpoint 'dimension' to differentiate between when we're running tests and when we're not.
    // in most cases, we do this to segregate the production and staging endpoints
    @Override
    public String getBaseUrl() {
        switch (FLAVOR) {
            case "espresso":
                // the mock web server runs on port 8008
                return "http://localhost:8008";
            default:
                return "https://api.github.com";

        }
    }
}
