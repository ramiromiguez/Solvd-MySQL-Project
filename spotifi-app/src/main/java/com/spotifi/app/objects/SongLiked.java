package com.spotifi.app.objects;

import java.util.Date;

public class SongLiked {
    private Date date;
    private int songId;
    private int userId;
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
	return "SongLiked [date=" + date + ", songId=" + songId + ", userId=" + userId + "]";
    }
    
    
}
