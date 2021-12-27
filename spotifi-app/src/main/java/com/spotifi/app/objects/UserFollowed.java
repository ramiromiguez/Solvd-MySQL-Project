package com.spotifi.app.objects;

public class UserFollowed {
    private int userId;
    private int userFollowedId;
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserFollowedId() {
        return userFollowedId;
    }
    public void setUserFollowedId(int userFollowedId) {
        this.userFollowedId = userFollowedId;
    }
    @Override
    public String toString() {
	return "UserFollowed [userId=" + userId + ", userFollowedId=" + userFollowedId + "]";
    }
    
    
}
