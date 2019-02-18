package com.example.admin.lesson7json;

public class Owner {
    private String mAvatarURL;
    private String mId;
    private String mType;

    public Owner(String avatarURL, String id, String type) {
        mAvatarURL = avatarURL;
        mId = id;
        mType = type;
    }

    public String getAvatarURL() {
        return mAvatarURL;
    }

    public String getId() {
        return mId;
    }

    public String getType() {
        return mType;
    }
}
