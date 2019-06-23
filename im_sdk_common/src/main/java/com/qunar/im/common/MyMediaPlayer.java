package com.qunar.im.common;

import android.media.MediaPlayer;

public class MyMediaPlayer extends MediaPlayer {
    private static volatile MyMediaPlayer instance;

    protected MyMediaPlayer() {
    }

    public static MyMediaPlayer getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    protected static synchronized MyMediaPlayer createInstance() {
        MyMediaPlayer myMediaPlayer;
        synchronized (MyMediaPlayer.class) {
            if (instance == null) {
                instance = new MyMediaPlayer();
            }
            myMediaPlayer = instance;
        }
        return myMediaPlayer;
    }
}
