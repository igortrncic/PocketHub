package com.github.pockethub.core.release;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

public class GetReleasesClient extends GithubClient<List<Release>> {

    private ReleaseInfo releaseInfo;

    public GetReleasesClient(Context context, ReleaseInfo releaseInfo) {
        super(context);
        this.releaseInfo = releaseInfo;
    }

    private GetReleasesClient(Context context) {
        super(context);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        ReleaseService releaseService = restAdapter.create(ReleaseService.class);
        releaseService.releases(releaseInfo.repoInfo.owner, releaseInfo.repoInfo.name, this);
    }

    @Override
    protected List<Release> executeServiceSync(RestAdapter restAdapter) {
        ReleaseService releaseService = restAdapter.create(ReleaseService.class);
        return releaseService.releases(releaseInfo.repoInfo.owner, releaseInfo.repoInfo.name);
    }
}
