package groupdenim.cmpt276.mentalhealthlumo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//source for animation: https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-12-animations/12-1-p-property-animation/12-1-p-property-animation.html

public class BreathingActivity extends AppCompatActivity {
    private int x;
    private int y;

    private int currHeartBeat = 101;
    private static int CALM_HEART_BEAT = 100;

    private boolean continueExercise = true;
    final int[] counter = new int[1];

    private static String TAG = "Breathing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "entered onCreate");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "finished super.onCreate");
        setContentView(R.layout.activity_breathing);
        Log.d(TAG, "finished setContentView");

        breathingExercise();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final BreathingAnimatedView mBreathingAnimatedView = (BreathingAnimatedView) findViewById(R.id.breatherView);

        final Handler handler = new Handler();
        //in milliseconds
        final int delay = 14000;

        mBreathingAnimatedView.animationActivated(x, y);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                counter[0] = 0;
                textChange();
                TextView instructionText = (TextView) findViewById(R.id.textInstructions);
                instructionText.setText("Breathe Out");
                mBreathingAnimatedView.animationActivated(x, y);
                handler.postDelayed(this, delay);
            }
        }, delay);

    }

    private void textChange() {
        final Handler handler = new Handler();
        final int delay = 3500;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                counter[0]++;
                if (counter[0] % 4 == 2) {
                    TextView instructionText = (TextView) findViewById(R.id.textInstructions);
                    instructionText.setText("");
                    instructionText.setText("Breathe In");
                } else if (counter[0] % 4 == 1 || counter[0] % 4 == 3) {
                    TextView instructionText = (TextView) findViewById(R.id.textInstructions);
                    instructionText.setText("");
                    instructionText.setText("Hold Breath");
                } else {
                    //
                    TextView instructionText = (TextView) findViewById(R.id.textInstructions);
                    instructionText.setText("");
                    instructionText.setText("Breathe out");
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private void breathingExercise() {
        Log.d (TAG, "entered breathingExercise");
        x = (this.getResources().getDisplayMetrics().widthPixels) / 2;
        y = (this.getResources().getDisplayMetrics().heightPixels) / 2;
        Log.d(TAG, "found values of x and y");
        Log.d(TAG, "value of x is: " + x);
        Log.d(TAG, "value of y is: " + y);
        //exhale for 2 sec (expand from outerCircle)
        //check heartbeat
        checkHeartbeat();

//        while (continueExercise == true) {
//            Log.d(TAG, "while loop entered");


            //start loop:
            //pause for 4 sec (pause animation)
            //breathe in for 4 sec (collapse towards innerCircle)
            //pause for 4 sec (pause animation)
            //breathe out for 4 sec (expand towards and outside of outerCircle)
            //check heartbeat
            checkHeartbeat();
            //if heartbeat is under acceptable levels, give them a notice that their heartbeat is at acceptable levels, then give a button that lets them exit out of the activity
//        }

    }

    private void checkHeartbeat() {
        Log.d(TAG, "checkHeartbeat entered");
        //use a heartbeat api or build own to check heartbeat regularly
        if (currHeartBeat < CALM_HEART_BEAT) {
            Log.d(TAG, "if loop entered");
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