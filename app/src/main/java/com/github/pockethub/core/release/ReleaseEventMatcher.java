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

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.events.EventType;
import com.alorma.github.sdk.bean.dto.response.events.payload.ReleaseEventPayload;
import com.google.gson.Gson;

/**
 * Helper to find an release to open for an event
 */
public class ReleaseEventMatcher {

    /**
     * Get release from event
     *
     * @param event
     * @return release or null if event doesn't apply
     */
    public Release getRelease(GithubEvent event) {
        if (event == null)
            return null;
        if (event.payload == null)
            return null;

        Gson gson = new Gson();
        String json = gson.toJson(event.payload);

        EventType type = event.getType();
        if (EventType.ReleaseEvent.equals(type))
            return (gson.fromJson(json, ReleaseEventPayload.class)).release;
        else
            return null;
    }
}
