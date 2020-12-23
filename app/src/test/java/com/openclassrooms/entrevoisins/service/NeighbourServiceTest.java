package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void changeFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(2);
        service.changeFavoriteNeighbour(neighbour.getId());
        assertTrue(service.getNeighbours().get(2).isFavorites());
        service.changeFavoriteNeighbour(neighbour.getId());
        assertFalse(service.getNeighbours().get(2).isFavorites());
    }

    @Test
    public void getFavoriteNeighbourWithSuccess() {
        service.changeFavoriteNeighbour(service.getNeighbours().get(0).getId());
        service.changeFavoriteNeighbour(service.getNeighbours().get(3).getId());
        service.changeFavoriteNeighbour(service.getNeighbours().get(6).getId());
        List<Neighbour> favoriteList = service.getFavoriteNeighbours();
        for (Neighbour neighbour : favoriteList)
            assertTrue(neighbour.isFavorites());
            assertEquals(favoriteList.size(), 3);
    }

    @Test
    public void CreateNeighbourWithSuccess() {
        int listSize = service.getNeighbours().size();
        Neighbour neighbourToCreate = new Neighbour(32, "Jean", "", "2 rue Bigorneau", "0123456789", "Je suis un gros bigorneau.");
        service.createNeighbour(neighbourToCreate);
        assertEquals(service.getNeighbours().size(), listSize+1);

    }
}
