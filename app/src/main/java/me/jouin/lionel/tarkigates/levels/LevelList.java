package me.jouin.lionel.tarkigates.levels;

/**
 * Created by lione on 11/11/2016.
 */

public enum LevelList {
    LEVEL_1(1), LEVEL_2(2), LEVEL_3(3), LEVEL_4(4), LEVEL_5(5), LEVEL_6(6), LEVEL_7(7), LEVEL_8(8), LEVEL_9(9), LEVEL_10(10), LEVEL_11(11), LEVEL_12(12);

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
            case LEVEL_6:
                return new Level_6();
            case LEVEL_7:
                return new Level_7();
            case LEVEL_8:
                return new Level_8();
            case LEVEL_9:
                return new Level_9();
            case LEVEL_10:
                return new Level_10();
            case LEVEL_11:
                return new Level_11();
            case LEVEL_12:
                return new Level_12();
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
        if (level instanceof Level_6)
            return LEVEL_6;
        if (level instanceof Level_7)
            return LEVEL_7;
        if (level instanceof Level_8)
            return LEVEL_8;
        if (level instanceof Level_9)
            return LEVEL_9;
        if (level instanceof Level_10)
            return LEVEL_10;
        if (level instanceof Level_11)
            return LEVEL_11;
        if (level instanceof Level_12)
            return LEVEL_12;
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
