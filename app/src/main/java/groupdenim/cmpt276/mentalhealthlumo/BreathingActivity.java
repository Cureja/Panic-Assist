package groupdenim.cmpt276.mentalhealthlumo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BreathingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        breathingExercise();
    }

    private void breathingExercise() {
        //exhale for 2 sec (expand from outerCircle)
        //check heartbeat
        checkHeartbeat();

        //start loop:
        //pause for 4 sec (pause animation)
        //breathe in for 4 sec (collapse towards innerCircle)
        //pause for 4 sec (pause animation)
        //breathe out for 4 sec (expand towards and outside of outerCircle)
        //check heartbeat
        checkHeartbeat();
            //if heartbeat is under acceptable levels, give them a notice that their heartbeat is at acceptable levels, then give a button that lets them exit out of the activity
    }

    private void checkHeartbeat() {
        //use a heartbeat api or build own to check heartbeat regularly
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, BreathingActivity.class);
        return intent;
    }
}