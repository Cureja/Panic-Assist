package groupdenim.cmpt276.mentalhealthlumo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ModelVersion;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Prediction;
import okhttp3.OkHttpClient;

public class TestYourHappiness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_your_happiness);

        Button getUrl = findViewById(R.id.inputButton);
        final EditText inputText = findViewById(R.id.inputUrl);

        final TextView feelingOne = findViewById(R.id.feelingOne);
        final TextView feelingOneP = findViewById(R.id.feelingPercentOne);
        final TextView feelingTwo = findViewById(R.id.feelingTwo);
        final TextView feelingTwoP = findViewById(R.id.feelingPercentTwo);

        final ClarifaiClient client = new ClarifaiBuilder(getString(R.string.api_key))
                .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                .buildSync(); // or use .build() to get a Future<ClarifaiClient>

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    ModelVersion modelVersion = client.getModelVersionByID("happy", getString(R.string.api_key_2))
                            .executeSync()
                            .get();
                    List<ClarifaiOutput<Prediction>> test =  client.predict("happy")
                            .withVersion(modelVersion)
                            .withInputs(ClarifaiInput.forImage(inputText.getText().toString()))
                            .executeSync().get();

                    final String name = "Mood: " + test.get(0).data().get(0).asConcept().name();
                    final String percentage = "Percent: " + Float.toString(test.get(0).data().get(0).asConcept().value());

                    final String nameTwo = "Mood: " + test.get(0).data().get(1).asConcept().name();
                    final String percentageTwo = "Percent: " + Float.toString(test.get(0).data().get(1).asConcept().value());

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            feelingOne.setText(name);
                            feelingOneP.setText(percentage);
                            feelingTwo.setText(nameTwo);
                            feelingTwoP.setText(percentageTwo);

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        getUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputText.getText().toString().equals("Enter your image url")) {
                    Toast.makeText(TestYourHappiness.this, "Please Enter a valid url",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    thread.start();
                }
            }
        });

    }
}
