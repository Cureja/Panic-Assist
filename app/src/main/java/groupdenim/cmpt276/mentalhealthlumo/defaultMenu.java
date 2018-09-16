package groupdenim.cmpt276.mentalhealthlumo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class defaultMenu extends AppCompatActivity {

    final private Button mainMenuButton = findViewById(R.id.mainMenuButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        if (preferences.getBoolean("firstLogin", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstLogin", false);
            editor.apply();
            Intent welcomeScreen = new Intent(defaultMenu.this, welcomeScreen.class);
            startActivity(welcomeScreen);
        }

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(defaultMenu.this, MainMenu.class);
                startActivity(mainMenu);
                finish();
            }
        });

    }
}
