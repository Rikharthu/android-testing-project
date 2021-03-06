package com.teamtreehouse.testingbase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/*================================================================
"View Layer".Host all the Android related code here. Implement
View-Interface methods via Android code. Presenter will use
this activity's methods through the implemented interface and call
implemented methods. That way presenter has access to the UI, but knows
nothing about the Activity and Android code
================================================================*/
public class MainActivity extends AppCompatActivity implements MainActivityView{
    LinearLayout linearLayout;
    EditText editText;
    TextView textView;
    Spinner colorSpinner;
    Button launchActivityButton;

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Presenter. It accepts MainActivityView as parameter => pass this activity
        presenter = new MainActivityPresenter(this);

        // Initialize Views
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        launchActivityButton = (Button) findViewById(R.id.launchActivityButton);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        colorSpinner = (Spinner) findViewById(R.id.colorSpinner);

        // Setup Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String text = tv.getText().toString();
                    // Instead of having activity update our views ,let presenter do this
//                    textView.setText(text);
                    // delegate View events to the presenter
                    presenter.editTextUpdated(text);
                }
                return false;
            }
        });

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                presenter.colorSelected(index);
//                switch (index) {
//                    case 0:
//                        linearLayout.setBackgroundColor(Color.WHITE);
//                        break;
//                    case 1:
//                        linearLayout.setBackgroundColor(Color.MAGENTA);
//                        break;
//                    case 2:
//                        linearLayout.setBackgroundColor(Color.GREEN);
//                        break;
//                    case 3:
//                        linearLayout.setBackgroundColor(Color.CYAN);
//                        break;
//                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        launchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.launchOtherActivityButtonClicked(OtherActivity.class);
//                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
//                startActivity(intent);
            }
        });
    }

    /* For Presenter - interface implementation with android code */
    @Override
    public void changeTextViewText(String text) {
        textView.setText(text);
    }

    @Override
    public void changeBackgroundColor(int color) {
        linearLayout.setBackgroundColor(color);
    }

    @Override
    public void launchOtherActivity(Class Activity) {
        Intent intent = new Intent(MainActivity.this, Activity);
        startActivity(intent);
    }
}
