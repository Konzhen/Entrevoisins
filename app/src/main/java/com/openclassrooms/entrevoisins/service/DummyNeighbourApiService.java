package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    public DummyNeighbourApiService () {
        neighbours = DummyNeighbourGenerator.generateNeighbours();
    }

    private List<Neighbour> neighbours;

    @Override
    public void changeFavoriteNeighbour(long id) {
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getId() == id)
                if (neighbour.isFavorites())
                    neighbour.setFavorite(false);
                else
                    neighbour.setFavorite(true);


        }
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteList = new ArrayList<>();
            for (Neighbour neighbour : neighbours) {
                if (neighbour.isFavorites())
                    favoriteList.add(neighbour);
            }
            return favoriteList;
        }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
