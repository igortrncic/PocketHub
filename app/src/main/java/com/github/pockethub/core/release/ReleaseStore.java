/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.core.release;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.github.pockethub.core.ItemStore;
import com.github.pockethub.util.InfoUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Store of loaded releases
 */
public class ReleaseStore extends ItemStore {

    private final Map<String, ItemReferences<Release>> releases = new HashMap<>();

    private Context context;

    /**
     * Create release store
     *
     * @param context
     */
    public ReleaseStore(final Context context) {
        this.context = context;
    }

    /**
     * Get release
     *
     * @param repo
     * @param id
     * @return release or null if not in store
     */
    public Release getRelease(Repo repo, String id) {
        ItemReferences<Release> repoIssues = releases.get(InfoUtils.createRepoId(repo));
        return repoIssues != null ? repoIssues.get(id) : null;
    }

    /**
     * Add release to store
     *
     * @param release
     * @return release
     */
    public Release addRelease(Repo repo, Release release) {
        Release current = getRelease(repo, release.id);
        if (current != null) {
            current.body = release.body;
            current.assets = release.assets;
            current.assets_url = release.assets_url;
            current.author = release.author;
            current.created_at = release.created_at;
            current.name = release.name;
            current.published_at = release.published_at;
            current.tag_name = release.tag_name;
            return current;
        } else {
            String repoId = InfoUtils.createRepoId(repo);
            ItemReferences<Release> repoReleases = releases.get(repoId);
            if (repoReleases == null) {
                repoReleases = new ItemReferences<>();
                releases.put(repoId, repoReleases);
            }
            repoReleases.put(release.id, release);
            return release;
        }
    }

    /**
     * Refresh release
     *
     * @param repo
     * @param id
     * @return refreshed release
     * @throws IOException
     */
    public Release refresh(final Repo repo, final String id) throws IOException {
        return null;
    }
}
