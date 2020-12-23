package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisplayNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.name_onPic)
    TextView name_onPic;
    @BindView(R.id.name_des)
    TextView name_des;
    @BindView(R.id.number_des)
    TextView number_des;
    @BindView(R.id.adress_des)
    TextView adress_des;
    @BindView(R.id.about_me_des)
    TextView about_me_des;
    @BindView(R.id.favorite)
    FloatingActionButton favorite;
    @BindView(R.id.neighbourAvatarDisplay)
    ImageView neighbourAvatarDisplay;
    @BindView(R.id.back_button)
    Toolbar back_button;

    Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_neighbour);
        Intent intent = getIntent();
        ButterKnife.bind(this);
        neighbour = (Neighbour) intent.getSerializableExtra("neighbour");
        name_onPic.setText(neighbour.getName());
        name_des.setText(neighbour.getName());
        number_des.setText(neighbour.getPhoneNumber());
        adress_des.setText(neighbour.getAddress());
        about_me_des.setText(neighbour.getAboutMe());
        changeFavoriteStar(neighbour.isFavorites());

        setSupportActionBar(back_button);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(neighbourAvatarDisplay.getContext())
                .load(neighbour.getAvatarUrl())
                .into(neighbourAvatarDisplay);

    }

    @OnClick(R.id.favorite)
    void beFavorite() {
        DI.getNeighbourApiService().changeFavoriteNeighbour(neighbour.getId());
        neighbour.setFavorite(!neighbour.isFavorites());
        changeFavoriteStar(neighbour.isFavorites());
    }

    void changeFavoriteStar(boolean star){
        if (star)
            favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_white_24dp));
        else
            favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_border_white_24dp));
    }


}

