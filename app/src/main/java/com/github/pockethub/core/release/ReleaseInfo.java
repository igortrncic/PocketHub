package com.github.pockethub.core.release;

import android.os.Parcel;
import android.os.Parcelable;

import com.alorma.github.sdk.bean.info.RepoInfo;

public class ReleaseInfo implements Parcelable {
    public RepoInfo repoInfo;
    public String id;

    public ReleaseInfo() {

    }

    protected ReleaseInfo(Parcel in) {
        repoInfo = in.readParcelable(RepoInfo.class.getClassLoader());
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(repoInfo, flags);
        dest.writeString(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ReleaseInfo> CREATOR = new Parcelable.Creator<ReleaseInfo>() {
        @Override
        public ReleaseInfo createFromParcel(Parcel in) {
            return new ReleaseInfo(in);
        }

        @Override
        public ReleaseInfo[] newArray(int size) {
            return new ReleaseInfo[size];
        }
    };
}
