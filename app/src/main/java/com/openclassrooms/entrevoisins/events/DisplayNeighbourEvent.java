package com.openclassrooms.entrevoisins.events;

import android.content.Intent;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

public class DisplayNeighbourEvent {

    public Neighbour neighbour;

    public DisplayNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
