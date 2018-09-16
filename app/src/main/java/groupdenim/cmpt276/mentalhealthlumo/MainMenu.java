package groupdenim.cmpt276.mentalhealthlumo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        if (preferences.getBoolean("firstLogin", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstLogin", false);
            editor.apply();
            Intent welcomeScreen = new Intent(MainMenu.this, groupdenim.cmpt276.mentalhealthlumo.welcomeScreen.class);
            startActivity(welcomeScreen);
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainMenu", "entered onClick for button");
                Intent intent = BreathingActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });

    }
}
