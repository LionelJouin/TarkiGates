package me.jouin.lionel.tarkigates;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by lione on 17/11/2016.
 */

public class Resources {

    private static Resources resources = null;

    public int wire;
    public int wireActivated = Color.parseColor("#e74c3c");

    private MediaPlayer switchClick;
    private MediaPlayer click;

    private BackgroundMusic backgroundMusic;

    private MainActivity mainActivity;

    private Resources() {
    }

    private Resources(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        switchClick = MediaPlayer.create(mainActivity, R.raw.switchclick);
        click = MediaPlayer.create(mainActivity, R.raw.click);
        wire = ResourcesCompat.getColor(mainActivity.getResources(), R.color.wire, null);
        wireActivated = ResourcesCompat.getColor(mainActivity.getResources(), R.color.wireActivated, null);

        backgroundMusic = new BackgroundMusic();
        backgroundMusic.execute();
    }

    public static Resources getInstance(MainActivity mainActivity) {
        if (resources == null) {
            resources = new Resources(mainActivity);
        }
        return resources;
    }

    public static Resources getInstance() {
        if (resources == null) {
            resources = new Resources();
        }
        return resources;
    }

    public void playSwitchSound() {
        switchClick.start();
    }
    public void playClickSound() {
        click.start();
    }

    public void playBackgroundMusic(boolean b) {
        if (backgroundMusic != null) {
            if (b) {
                backgroundMusic.start();
            } else {
                backgroundMusic.cancel(true);
                backgroundMusic.stop();
            }
        }
    }

    public class BackgroundMusic extends AsyncTask<Void, Void, Void> {

        private boolean running = true;
        private MediaPlayer player;

        public BackgroundMusic() {
            super();
            player = MediaPlayer.create(mainActivity.getApplicationContext(), R.raw.music);
            player.setLooping(true);
            player.setVolume(1.0f, 1.0f);
        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        public void start(){
            player.start();
        }

        public void stop(){
            player.pause();
        }
    }

}
