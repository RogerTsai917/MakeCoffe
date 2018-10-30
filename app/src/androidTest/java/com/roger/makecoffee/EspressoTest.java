package com.roger.makecoffee;

import android.support.test.espresso.*;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    @Rule
    public ActivityTestRule<MakeCoffeeActivity> mActivityRule = new ActivityTestRule(MakeCoffeeActivity.class);

    @Test
    public void test() throws InterruptedException {
        Espresso.onView(withId(R.id.navigation_articles)).perform(click());
            Espresso.onView(withId(R.id.recyclerView_articles_list)).perform(swipeDown());
        Thread.sleep(1000);
        Espresso.onView(withId(R.id.recyclerView_articles_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(
                        0,
                        click()));

        Thread.sleep(5000);
    }

}
