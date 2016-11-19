package me.jouin.lionel.tarkigates.pages;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import me.jouin.lionel.tarkigates.R;
import me.jouin.lionel.tarkigates.Resources;

/**
 * Created by lione on 12/11/2016.
 */

public class Informations extends Page {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_informations, container, false);

        LinearLayout informationsLayout = (LinearLayout) root.findViewById(R.id.informations);
        ImageView backImageView = (ImageView) root.findViewById(R.id.back);

        int[] colors = {ResourcesCompat.getColor(getResources(), R.color.skyStart, null), ResourcesCompat.getColor(getResources(), R.color.skyEnd, null)};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        informationsLayout.setBackgroundDrawable(gd);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources.getInstance().playClickSound();
                changePage(PageName.HOME);
            }
        });

        return root;
    }

}
