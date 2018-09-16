package groupdenim.cmpt276.mentalhealthlumo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//source for animation: https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-12-animations/12-1-p-property-animation/12-1-p-property-animation.html

public class BreathingActivity extends AppCompatActivity {
    private int x;
    private int y;

    private int currHeartBeat = 101;
    private static int CALM_HEART_BEAT = 100;

    private boolean continueExercise = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        breathingExercise();
    }

    private void breathingExercise() {
        x = (this.getResources().getDisplayMetrics().widthPixels) / 2;
        y = (this.getResources().getDisplayMetrics().heightPixels) / 2;
        //exhale for 2 sec (expand from outerCircle)
        //check heartbeat
        checkHeartbeat();

        while (continueExercise == true) {
            BreathingAnimatedView mBreathingAnimatedView = (BreathingAnimatedView) findViewById(R.id.breatherView);
            mBreathingAnimatedView.animationActivated(x, y);
            //start loop:
            //pause for 4 sec (pause animation)
            //breathe in for 4 sec (collapse towards innerCircle)
            //pause for 4 sec (pause animation)
            //breathe out for 4 sec (expand towards and outside of outerCircle)
            //check heartbeat
            checkHeartbeat();
            //if heartbeat is under acceptable levels, give them a notice that their heartbeat is at acceptable levels, then give a button that lets them exit out of the activity
        }

    }

    private void checkHeartbeat() {
        //use a heartbeat api or build own to check heartbeat regularly
        if (currHeartBeat < CALM_HEART_BEAT) {
            Button button = (Button) findViewById(R.id.btnFinishedExercise);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    continueExercise = false;
                    finish();
                }
            });
        }
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, BreathingActivity.class);
        return intent;
    }
}