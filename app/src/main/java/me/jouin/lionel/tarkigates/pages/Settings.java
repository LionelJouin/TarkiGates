package me.jouin.lionel.tarkigates.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.jouin.lionel.tarkigates.R;

/**
 * Created by lione on 12/11/2016.
 */

public class Settings extends Page {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);
        return root;
    }

}
