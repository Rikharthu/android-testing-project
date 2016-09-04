package com.teamtreehouse.testingbase;


import android.graphics.Color;

/**
 *  Presenter layer. Responsible for coordinating data with the UI
 */
public class MainActivityPresenter {
    // reference to MainActivityView implementation (MainActivity).
    // Main Activity implements this interface methods , which the presenter can use
    MainActivityView view;

    public MainActivityPresenter(MainActivityView view){
        this.view=view;
    }

    public void editTextUpdated(String text){
        // use MainActivityView's method to modify view
        view.changeTextViewText(text);
    }

    public void colorSelected(int color){
        switch (color) {
            case 0:
                view.changeBackgroundColor(Color.WHITE);
                break;
            case 1:
                view.changeBackgroundColor(Color.MAGENTA);
                break;
            case 2:
                view.changeBackgroundColor(Color.GREEN);
                break;
            case 3:
                view.changeBackgroundColor(Color.CYAN);
                break;
        }
    }

    public void launchOtherActivityButtonClicked(Class activity){
        view.launchOtherActivity(activity);
    }

}
