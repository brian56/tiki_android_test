package vn.huynh.tikitest;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vn.huynh.tikitest.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by duong on 3/30/2019.
 *
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkViewVisibility() {
        // verify menu item is displayed
        onView(withId(R.id.action_refresh)).check(matches(isDisplayed()));

        // verify progressbar is not displayed
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())));

        // verify recycler view is displayed
        onView(withId(R.id.rv_list_keyword)).check(matches(isDisplayed()));

        //number child item display on screen,
        //in my case it's 6 items, maybe different on other devices
        onView(withId(R.id.rv_list_keyword)).check(matches(hasChildCount(6)));
        //total child item
        onView(withId(R.id.rv_list_keyword)).check(new RecyclerViewItemCountAssertion(20));
    }

    @Test
    public void checkRefreshMenuClick() {
        // perform click
        onView(withId(R.id.action_refresh)).check(matches(isDisplayed()));
        onView(withId(R.id.action_refresh)).perform(click());

        // verify progressbar is not displayed
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())));

        // verify recycler view is displayed
        onView(withId(R.id.rv_list_keyword)).check(matches(isDisplayed()));

        //number child item display on screen,
        //in my case it's 6 items, maybe different on other devices
        onView(withId(R.id.rv_list_keyword)).check(matches(hasChildCount(6)));
        //total child item
        onView(withId(R.id.rv_list_keyword)).check(new RecyclerViewItemCountAssertion(20));

        // verify the toast text
        MainActivity activity = activityTestRule.getActivity();
        onView(withText(R.string.load_successful)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }
}
