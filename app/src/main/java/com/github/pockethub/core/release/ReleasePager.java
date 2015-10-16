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

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.github.pockethub.core.ResourcePager;

/**
 * Helper class for showing more and more pages of releases
 */
public abstract class ReleasePager extends ResourcePager<Release> {

    private final Repo repository;

    private final ReleaseStore store;

    /**
     * Create pager
     *
     * @param repository
     * @param store
     */
    public ReleasePager(final Repo repository, final ReleaseStore store) {
        this.repository = repository;
        this.store = store;
    }

    @Override
    protected Object getId(final Release resource) {
        return resource.id;
    }

    @Override
    protected Release register(final Release resource) {
        return store.addRelease(repository, resource);
    }
}
