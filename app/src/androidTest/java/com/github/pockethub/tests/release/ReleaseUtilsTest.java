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
import com.alorma.github.sdk.bean.dto.response.User;
import com.github.pockethub.core.release.ReleaseUtils;
import com.github.pockethub.util.TimeUtils;

import java.util.Date;

/**
 * Test of {@link com.github.pockethub.core.release.ReleaseUtils}
 */
public class ReleaseUtilsTest extends AndroidTestCase {

    /**
     * Test release author name parsing from release
     */
    public void testGetAuthorName() {
        assertNull(ReleaseUtils.getAuthor((Release) null));
        assertEquals("", ReleaseUtils.getAuthor(new Release()));
        Release release = new Release();
        release.author = new User();
        String result =  "Igor Trncic";
        release.author.name = result;
        assertEquals(result, ReleaseUtils.getAuthor(release));
    }

    /**
     * Test parsing publishing date from release
     */
    public void testGetReleaseDate() {
        Release release = new Release();
        assertNull(ReleaseUtils.getReleaseDate(release));
        release.published_at = TimeUtils.dateToString(new Date(12000));
        assertEquals(new Date(12000), ReleaseUtils.getReleaseDate(release));
    }
}
