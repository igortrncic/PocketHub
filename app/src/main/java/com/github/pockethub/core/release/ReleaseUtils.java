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

import android.widget.ImageView;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.User;
import com.github.pockethub.util.AvatarLoader;
import com.github.pockethub.util.TimeUtils;

import java.util.Date;

/**
 * Utilities for working with releases
 */
public class ReleaseUtils {

    /**
     * Get author of release
     *
     * @param release
     * @return author name or null if missing
     */
    public static String getAuthor(final Release release) {
        User author = release.author;
        return author.login;
    }

    /**
     * Get author date of release
     *
     * @param release
     * @return author name or null if missing
     */
    public static Date getReleaseDate(final Release release) {
        return release != null && release.published_at != null ? TimeUtils.stringToDate(release.published_at) :
                null;
    }

    /**
     * Bind release author avatar to image view
     *
     * @param release
     * @param avatars
     * @param view
     * @return view
     */
    public static ImageView bindAuthor(final Release release,
            final AvatarLoader avatars, final ImageView view) {
        User author = release.author;
        if (author != null)
            avatars.bind(view, author);
        return view;
    }
}
