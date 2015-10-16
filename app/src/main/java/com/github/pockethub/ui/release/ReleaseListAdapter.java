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

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.github.pockethub.R;
import com.github.pockethub.core.release.ReleaseUtils;
import com.github.pockethub.ui.StyledText;
import com.github.pockethub.util.AvatarLoader;
import com.github.pockethub.util.TypefaceUtils;

import java.util.Collection;

/**
 * Adapter to display Releases
 */
public class ReleaseListAdapter extends SingleTypeAdapter<Release> {

    private final AvatarLoader avatars;

    /**
     * @param viewId
     * @param inflater
     * @param elements
     * @param avatars
     */
    public ReleaseListAdapter(int viewId, LayoutInflater inflater,
                              Collection<Release> elements, AvatarLoader avatars) {
        super(inflater, viewId);

        this.avatars = avatars;
        setItems(elements);
    }

    @Override
    public long getItemId(int position) {
        String id = getItem(position).id;
        if (!TextUtils.isEmpty(id))
            return id.hashCode();
        else
            return super.getItemId(position);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_release_date, R.id.tv_release_name, R.id.iv_avatar,
                R.id.tv_release_author, R.id.tv_release_body};
    }

    @Override
    protected View initialize(View view) {
        view = super.initialize(view);

        TypefaceUtils.setOcticons((TextView) view
                .findViewById(R.id.tv_tag_icon));
        return view;
    }

    @Override
    protected void update(int position, Release item) {

        StyledText dateText = new StyledText();
        dateText.append(ReleaseUtils.getReleaseDate(item));
        dateText.append(' ');

        setText(0, dateText);

        setText(1, item.name);

        ReleaseUtils.bindAuthor(item, avatars, imageView(2));

        StyledText authorText = new StyledText();
        authorText.bold(ReleaseUtils.getAuthor(item));
        authorText.append(' ');
        authorText.append("tegged this on ");
        authorText.bold(item.target_commitish);
        setText(3, authorText);

        setText(4, item.body);
    }
}
