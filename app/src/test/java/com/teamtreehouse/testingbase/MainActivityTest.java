package com.teamtreehouse.testingbase;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.inputmethod.EditorInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

// Use methods from org.junit package!
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Use Roboelectric
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {

    MainActivity activity;

    /** Method, where we setup everything for testing */
    @Before
    public void setUp(){
        // Create an Activity
        // Thanks to roboelectric, activity has gone through it's required lifecycle methods
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    /** Check if EditText edits TextView */
    @Test
    public void editTextUpdatesTextView() throws Exception{
        // Arrange
        String givenString = "test123";
        activity.editText.setText(givenString);

        // Act
        // Trigger EditText onEditorAction()
        // Comment this line to see how error reporting works
        activity.editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

        //Assert
        // Check if TextView was updated with the correct text
        String actualString = activity.textView.getText().toString();

        assertEquals(givenString,actualString);
    }

    @Test
    public void spinnerUpdatesBackgroundColor() throws Exception{
        // Arrange
        int index = 2;
        int givenColor = Color.GREEN;

        // Act
        activity.colorSpinner.setSelection(index);

        //Assert
        // retrieve background color
        int actualColor = ((ColorDrawable)activity.linearLayout.getBackground()).getColor();
        // check that givenColor matches expectedColor
        assertEquals(givenColor,actualColor);
    }

    @Test
    public void buttonLaunchesOtherActivity() throws Exception{
        // Arrange
        Class clazz = OtherActivity.class;
        Intent expectedIntent = new Intent(activity, clazz);

        // Act
        activity.launchActivityButton.callOnClick();

        // Assert
        ShadowActivity shadowActivity= Shadows.shadowOf(activity);
        // return the next intent on the started activity stack
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

}
