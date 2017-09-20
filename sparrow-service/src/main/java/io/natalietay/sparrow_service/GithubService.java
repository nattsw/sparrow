package io.natalietay.sparrow_service;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Natalie on 19/9/17.
 */

public interface GithubService {
    @GET("/zen")
    Observable<String> getZen();
}
