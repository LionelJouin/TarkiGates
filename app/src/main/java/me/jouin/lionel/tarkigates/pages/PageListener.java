package me.jouin.lionel.tarkigates.pages;

import me.jouin.lionel.tarkigates.levels.LevelList;

/**
 * Created by lione on 11/11/2016.
 */

public interface PageListener {

    public void pageChange(PageName pageName);

    public void selectLevel(LevelList levelList);

}
