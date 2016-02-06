package com.example.jacobosternberg.prog1;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    String[] exercises;
    Spinner spinner;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final String[] exercises = getResources().getStringArray(R.array.exercise_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exercises);
        spinner.setAdapter(adapter);

        final String[] work = {""};
        final EditText input = (EditText) findViewById(R.id.quantity);

        TextView conversionMessage = (TextView) findViewById(R.id.equivalences);
        conversionMessage.setVisibility(View.INVISIBLE);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int caloriesBurned = 0;
                if (work[0] != "") {
                    if (input.getText().toString().equals("")) {
                        return;
                    }
                    int inputInt = Integer.parseInt(input.getText().toString());
                    System.out.println(work[0]);
                    if (work[0].equals("Pushups")) {
                        caloriesBurned = inputInt * 2 / 7;
                    } else if (work[0].equals("Situps")) {
                        caloriesBurned = inputInt / 2;
                    } else if (work[0].equals("Jumping Jacks")) {
                        caloriesBurned = inputInt * 10;
                    } else if (work[0].equals("Jogging")) {
                        caloriesBurned = inputInt * 100 / 12;
                    }
                    TextView calorieDisplay = (TextView) findViewById(R.id.calories);
                    calorieDisplay.setText("Calories Burned: " + new Integer(caloriesBurned).toString());
                    equivalence(caloriesBurned);
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView inputPrompt = (TextView) findViewById(R.id.reps);
                TextView calorieDisplay = (TextView) findViewById(R.id.calories);
                int caloriesBurned;
                if (position >= 2) {
                    inputPrompt.setText("How many minutes?");
                    if (input.getText().toString().equals("")) {
                        work[0] = exercises[position];
                        return;
                    }
                    int inputInt = Integer.parseInt(input.getText().toString());
                    if (position == 2) {
                        caloriesBurned = inputInt * 10;
                        calorieDisplay.setText("Calories Burned: " + new Integer(caloriesBurned).toString());
                        equivalence(caloriesBurned);
                    } else {
                        caloriesBurned = inputInt * 100 / 12;
                        calorieDisplay.setText("Calories Burned: " + new Integer(caloriesBurned).toString());
                        equivalence(caloriesBurned);
                    }
                } else {
                    inputPrompt.setText("How many reps?");
                    if (input.getText().toString().equals("")) {
                        work[0] = exercises[position];
                        return;
                    }
                    int inputInt = Integer.parseInt(input.getText().toString());
                    if (position == 0) {
                        caloriesBurned = inputInt * 2/7;
                        calorieDisplay.setText("Calories Burned: " + new Integer(caloriesBurned).toString());
                        equivalence(caloriesBurned);
                    } else {
                        caloriesBurned = inputInt / 2;
                        calorieDisplay.setText("Calories Burned: " + new Integer(caloriesBurned).toString());
                        equivalence(caloriesBurned);
                    }
                }
                work[0] = exercises[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                input.setVisibility(View.INVISIBLE);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.jacobosternberg.prog1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.jacobosternberg.prog1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void equivalence(int calorie) {
        TextView equivalences = (TextView) findViewById(R.id.equivalences);
        int pushups;
        int situps;
        int jogging;
        int jjacks;
        pushups = calorie * 350 / 100;
        situps = calorie * 2;
        jogging = calorie * 12 / 100;
        jjacks = calorie / 10;
        String pushupS = (pushups != 1) ? Integer.toString(pushups) + " pushups" : "1 pushup";
        String situpS = (situps != 1) ? Integer.toString(situps) + " situps" : "1 situp";
        String joggingS = (jogging != 1) ? Integer.toString(jogging) + " minutes of jogging" : "1 minute of jogging";
        String jjacksS = (jjacks != 1) ? Integer.toString(jjacks) + " minutes of jumping jacks" : "1 minute of jumping jacks";
        String message = "This is equivalent to:\n\n" + pushupS + "\n\n" + situpS + "\n\n" +
                joggingS + "\n\n" + jjacksS;
        equivalences.setText(message);
        equivalences.setVisibility(View.VISIBLE);
    }

}
