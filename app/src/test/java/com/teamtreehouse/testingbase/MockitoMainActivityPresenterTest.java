package com.teamtreehouse.testingbase;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MockitoMainActivityPresenterTest {

    MainActivityPresenter presenter;

    @Mock
    MainActivityView view;


    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void editTextUpdated() throws Exception {
        // Arrange
        String givenString = "test123";


        // Act
        presenter.editTextUpdated(givenString);

        //Assert
        // Assert that this method above was called once with the givenString
        Mockito.verify(view).changeTextViewText(givenString);
    }

    @Test
    public void colorSelected() throws Exception {
        // Arrange
        int index = 2;
        int givenColor = Color.GREEN;

        // Act
        presenter.colorSelected(index);

        //Assert
        // Check if changeBackgroundColor() in the MainActivityView was called with givenColor
        Mockito.verify(view).changeBackgroundColor(givenColor);
        // This one will fail
//        Mockito.verify(view).changeBackgroundColor(Color.RED);
    }

    @Test
    public void launchOtherActivityButtonClicked() throws Exception {
        // Arrange
        Class clazz = OtherActivity.class;

        // Act
        presenter.launchOtherActivityButtonClicked(clazz);

        //Assert
        Mockito.verify(view).launchOtherActivity(clazz);

    }
}
