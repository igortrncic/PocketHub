package com.github.pockethub.core.release;

import com.alorma.github.sdk.bean.dto.response.Release;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ReleaseService {

    //Async
    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo, Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/releases/{id}")
    void detail(@Path("owner") String owner, @Path("name") String repo, @Path("id") String id,
                Callback<Release> callback);

    //Sync
    @GET("/repos/{owner}/{name}/releases")
    List<Release> releases(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/releases/{id}")
    Release detail(@Path("owner") String owner, @Path("name") String repo, @Path("id") String id);
}
