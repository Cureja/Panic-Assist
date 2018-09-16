package groupdenim.cmpt276.mentalhealthlumo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.easyandroidanimations.library.ExplodeAnimation;

public class welcomeScreen extends AppCompatActivity {
    public static int SPLASH_SCREEN_TIMER = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },SPLASH_SCREEN_TIMER);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);



    }
}
