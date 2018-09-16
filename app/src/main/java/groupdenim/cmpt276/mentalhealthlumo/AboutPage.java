package groupdenim.cmpt276.mentalhealthlumo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        Button goToImage = findViewById(R.id.happyOrSadButton);
        Button goToDragNDrop = findViewById(R.id.DragNDrop);
        Button goToResouces = findViewById(R.id.rssButton);
        Button gotoBreathing = findViewById(R.id.breathingAssist);


    }
}
