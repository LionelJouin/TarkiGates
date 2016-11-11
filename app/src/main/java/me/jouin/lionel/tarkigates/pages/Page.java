package me.jouin.lionel.tarkigates.pages;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import me.jouin.lionel.tarkigates.levels.LevelList;

/**
 * Created by lione on 11/11/2016.
 */

public abstract class Page extends Fragment {

    private List<PageListener> pageListener = new ArrayList<>();

    public void addPageListeners(PageListener l) {
        pageListener.add(l);
    }

    public void changePage(PageName pageName) {
        for (PageListener l : pageListener) {
            l.pageChange(pageName);
        }
    }

    public void changeLevel(LevelList levelList) {
        for (PageListener l : pageListener) {
            l.selectLevel(levelList);
        }
    }

}
