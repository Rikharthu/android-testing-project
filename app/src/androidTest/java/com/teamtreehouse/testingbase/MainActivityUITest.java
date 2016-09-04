package com.teamtreehouse.testingbase;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.LinearLayout;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.collect.MapConstraints.notNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    // TODO do not forget to turn off animations

    // Tell espresso which activity are we testing
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editTextUpdatesTextView() throws Exception {
        // Arrange
        String givenString = "test123";
        // create a view interaction with the view for given id
        // will type givenString in our editText
        // in other words - on the EditText with id editText we will perform an action of writing
        // text givenString
        onView(withId(R.id.editText)).perform(typeText(givenString));

        // Act
        // IME - input method editor
        // Tell espresso that we finished our action
        onView(withId(R.id.editText)).perform(pressImeActionButton());

        // Assert
        onView(withId(R.id.editText)).check(matches(withText(givenString)));
    }

    @Test
    public void spinnerUpdatesBackgroundColor() throws Exception {
        // Arrange
        final int givenColor = Color.GREEN;
        String spinnerItemText = "Green";

        // Act
        // Click on our spinner (it is closed by default)
        onView(withId(R.id.colorSpinner)).perform(click());
        // Click on item where text is "Green"
//        onView(withText(spinnerItemText)).perform(click());
        // find View with data of String class and is "Green", then perform click on that view
        onData(allOf(is(instanceOf(String.class)), is(spinnerItemText))).perform(click());

        // Assert
        // Create our own matcher
        BoundedMatcher backgroundColorMatcher = new BoundedMatcher<View, LinearLayout>(LinearLayout.class) {
            // tell matcher to look only on VIEWS and only match LINEARLAYOUT ^^^

            @Override
            protected boolean matchesSafely(LinearLayout linearLayout) {
                // Проведи аналогию с MainActivityTest (там также примерно)
                int actualColor = ((ColorDrawable)linearLayout.getBackground()).getColor();

                return givenColor==actualColor;
            }

            @Override
            public void describeTo(Description description) {
                // Describe errors if something doesnt match
                description.appendText("background color should equal: "+givenColor);
            }
        };

        onView(withId(R.id.linearLayout)).check(matches(backgroundColorMatcher));
    }

    @Test
    public void buttonLaunchesOtherActivity() throws Exception {
        // Arrange
        // OtherActivity has a textView with text "Other Activity"
        // if that text is found => Other activity launched successfully
        String otherActivityString = "Other Activity";

        // Act
        onView(withId(R.id.launchActivityButton)).perform(click());

        // Assert
        // check if given string exists
        onView(withText(otherActivityString)).check(matches(notNullValue()));

    }


}
