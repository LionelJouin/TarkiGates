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
        System.out.println("koukou abc");
        if (backgroundMusic != null) {
            if (b) {
                System.out.println("koukou "+backgroundMusic.getStatus());
                if (backgroundMusic.getStatus() != AsyncTask.Status.RUNNING)
                    backgroundMusic.execute();
            } else {
                System.out.println("koukou");
                backgroundMusic.cancel(true);
            }
        }
    }

    public class BackgroundMusic extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(mainActivity, R.raw.music);
            player.setLooping(true);
            player.setVolume(1.0f, 1.0f);
            player.start();

            return null;
        }

    }

}
