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
package com.github.pockethub.tests.release;

import android.test.AndroidTestCase;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.github.pockethub.core.release.ReleaseStore;
import com.github.pockethub.util.InfoUtils;

/**
 * Unit tests of {@link ReleaseStore}
 */
public class ReleaseStoreTest extends AndroidTestCase {

    /**
     * Verify release is stored
     */
    public void testReuseRelease() {
        ReleaseStore store = new ReleaseStore(mContext);
        Repo repo = InfoUtils.createRepoFromData("owner", "name");

        assertNull(store.getRelease(repo, "1"));

        Release release = new Release();

        release.id = "1";
        release.body = "body";
        assertSame(release, store.addRelease(repo, release));
        assertSame(release, store.getRelease(repo, "1"));
    }
}
