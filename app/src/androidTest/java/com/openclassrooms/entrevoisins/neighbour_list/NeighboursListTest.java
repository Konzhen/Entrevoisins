
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ClickOnNeighbour;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    private NeighbourApiService service;

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        service = DI.getNewInstanceApiService();
        service.getNeighbours().get(0).setFavorite(true);
        service.getNeighbours().get(6).setFavorite(true);
        service.getNeighbours().get(9).setFavorite(true);
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void displayNeighboursActivity_isLaunched() {
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, new ClickOnNeighbour()));
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(matches(isDisplayed()));
    }

    @Test
    public void neighbourName_isDisplayed() {
        assertThat(allOf(withId(R.id.list_neighbours), hasFocus()), notNullValue());
    }

    @Test
    public void onlyFavorites_areDisplayed() {
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickOnNeighbour())); //click sur un neighbour
        onView(withId(R.id.favorite))
                .perform(click()); //click sur l'étoile
        onView(withId(R.id.back_button))
                .perform(click()); //retour arrière
        onView(withId(R.id.tabItem2))
                .perform(click()); //click sur le second onglet
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .check(matches(hasChildCount(1))); //check si le neighbour est présent
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickOnNeighbour())); //click sur un neighbour
        onView(withId(R.id.favorite))
                .perform(click()); //click sur l'étoile
        onView(withId(R.id.back_button))
                .perform(click()); //retour arrière
        onView(withId(R.id.tabItem2))
                .perform(click()); //click sur le second onglet
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .check(matches(hasChildCount(0))); //puis l'enlever

    }

}