package com.github.pockethub.core.release;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

public class GetReleaseClient extends GithubClient<Release> {
    private String owner;
    private String repo;
    private String id;

    public GetReleaseClient(Context context, ReleaseInfo releaseInfo) {
        super(context);
        this.owner = releaseInfo.repoInfo.owner;
        this.repo = releaseInfo.repoInfo.name;
        this.id = releaseInfo.id;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(ReleaseService.class).detail(owner, repo, id, this);
    }

    @Override
    protected Release executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(ReleaseService.class).detail(owner, repo, id);
    }
}
