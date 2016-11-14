package me.jouin.lionel.tarkigates.levels;

/**
 * Created by lione on 11/11/2016.
 */

public enum LevelList {
    LEVEL_1(1), LEVEL_2(2), LEVEL_3(3), LEVEL_4(4), LEVEL_5(5);

    int number;
    LevelList(int n) {
        number = n;
    }

    public int getNumber() {
        return number;
    }

    public Level createLevel(LevelList levelList) {
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

    public LevelList whichLevel(Level level) {
        if (level instanceof Level_1)
            return LEVEL_1;
        if (level instanceof Level_2)
            return LEVEL_2;
        if (level instanceof Level_3)
            return LEVEL_3;
        if (level instanceof Level_4)
            return LEVEL_4;
        if (level instanceof Level_5)
            return LEVEL_5;
        else
            return null;
    }

    public LevelList nextLevel(LevelList levelList) {
        for(LevelList l : LevelList.values()){
            if(levelList.getNumber() == l.getNumber()-1) return l;
        }
        return null;
    }
}
