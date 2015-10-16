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
package com.github.pockethub.ui.release;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.github.pockethub.R;
import com.github.pockethub.ThrowableLoader;
import com.github.pockethub.core.PageIterator;
import com.github.pockethub.core.ResourcePager;
import com.github.pockethub.core.release.GetReleasesClient;
import com.github.pockethub.core.release.ReleasePager;
import com.github.pockethub.core.release.ReleaseStore;
import com.github.pockethub.ui.ItemListFragment;
import com.github.pockethub.ui.PagedItemFragment;
import com.github.pockethub.util.AvatarLoader;
import com.github.pockethub.util.InfoUtils;
import com.google.inject.Inject;

import java.util.List;

import static com.github.pockethub.Intents.EXTRA_REPOSITORY;

/**
 * Fragment to display a list of repository commits
 */
public class ReleaseListFragment extends PagedItemFragment<Release> {

    /**
     * Avatar loader
     */
    @Inject
    protected AvatarLoader avatars;

    @Inject
    private ReleaseStore store;

    private Repo repository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        repository = activity.getIntent().getParcelableExtra(EXTRA_REPOSITORY);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_releases);
    }

    @Override
    public Loader<List<Release>> onCreateLoader(int id, Bundle bundle) {
        final ThrowableLoader<List<Release>> parentLoader = (ThrowableLoader<List<Release>>) super
                .onCreateLoader(id, bundle);
        return new ThrowableLoader<List<Release>>(getActivity(), items) {

            @Override
            public List<Release> loadData() throws Exception {
                return parentLoader.loadData();
            }
        };
    }

    public void onLoadFinished(Loader<List<Release>> loader, List<Release> items) {
        super.onLoadFinished(loader, items);
    }

    @Override
    protected ResourcePager<Release> createPager() {
        return new ReleasePager(repository, store) {

            @Override
            protected Release register(Release resource) {
                return super.register(resource);
            }

            @Override
            public PageIterator<Release> createIterator(int page, int size) {

                return new PageIterator<>(new PageIterator.GitHubRequest<List<Release>>() {
                    @Override
                    public GithubClient<List<Release>> execute(int page) {
                            return new GetReleasesClient(getActivity(), InfoUtils
                                    .createReleaseInfo(repository));
                    }
                }, page);
            }

            @Override
            public ResourcePager<Release> clear() {
                return super.clear();
            }
        };
    }

    @Override
    protected int getLoadingMessage() {
        return R.string.loading_commits;
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        return R.string.error_commits_load;
    }

    @Override
    protected SingleTypeAdapter<Release> createAdapter(
            List<Release> items) {
        return new ReleaseListAdapter(R.layout.release_item, getActivity()
                .getLayoutInflater(), items, avatars);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Object item = l.getItemAtPosition(position);
        // TODO: 10/16/15  Add code for expanding list item, and animating near cells
    }

    @Override
    public ItemListFragment<Release> setListShown(boolean shown,
            boolean animate) {
        return super.setListShown(shown, animate);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.release_list, container, false);
    }
}
