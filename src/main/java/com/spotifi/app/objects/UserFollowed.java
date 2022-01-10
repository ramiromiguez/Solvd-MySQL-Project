package com.spotifi.app.objects;

public class UserFollowed {
    private User user;
    private User userFollowed;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public User getUserFollowed() {
	return userFollowed;
    }

    public void setUserFollowed(User userFollowed) {
	this.userFollowed = userFollowed;
    }

    @Override
    public String toString() {
	return "UserFollowed [user=" + user + ", userFollowed=" + userFollowed + "]";
    }

}
