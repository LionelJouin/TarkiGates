package me.jouin.lionel.tarkigates.levels;

/**
 * Created by lione on 11/11/2016.
 */

public enum LevelList {
    LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5;

    public static Level createLevel(LevelList levelList) {
        switch (levelList) {
            case LEVEL_1:
                return new Level_1();
            case LEVEL_2:
                return new Level_2();
            case LEVEL_3:
                return new Level_3();
            case LEVEL_4:
                return new Level_4();
            case LEVEL_5:
                return new Level_5();
            default:
                return null;
        }
    }
}
