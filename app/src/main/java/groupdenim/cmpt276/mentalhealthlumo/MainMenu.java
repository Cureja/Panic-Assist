package groupdenim.cmpt276.mentalhealthlumo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button goToImage = findViewById(R.id.toImageChecker);
        Button goToDragNDrop = findViewById(R.id.dragNDrop);
        Button goToSettings = findViewById(R.id.settingsButton);
        Button goToAbout = findViewById(R.id.aboutButton);
        Button goToResouces = findViewById(R.id.resButton);
        Button gotoBreathing = findViewById(R.id.breatheButton);

        goToImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, TestYourHappiness.class);
                startActivity(intent);
            }
        });

        goToDragNDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, DragDropNumbers.class);
                startActivity(intent);
            }
        });

        goToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AboutPage.class);
                startActivity(intent);
            }
        });

    }
}
