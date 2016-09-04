package com.teamtreehouse.testingbase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

// Use methods from org.junit package!
public class MainActivityPresenterTest {

    MainActivityPresenter presenter;
    MainActivityView view;

    // Симуляция нашей MainActivity как имплементация MainActivityView
    class MockedView implements MainActivityView{
        String textViewText;

        @Override
        public void changeTextViewText(String text) {
            // We just want to make sure that UI change is called with the correct value
            // Since TextView.setText(...) is android code, it is already proven
            // however our presenter logic is not...
            textViewText=text;
        }

        @Override
        public void changeBackgroundColor(int color) {

        }

        @Override
        public void launchOtherActivity(Class Activity) {

        }
    }


    @Before
    public void setUp() throws Exception {
        view =new MockedView();
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void editTextUpdated() throws Exception {
        // Arrange
        String givenString = "test123";


        // Act
        presenter.editTextUpdated(givenString);

        //Assert
        assertEquals(givenString,((MockedView)view).textViewText);
    }

    @Test
    public void colorSelected() throws Exception {

    }

    @Test
    public void launchOtherActivityButtonClicked() throws Exception {

    }
}