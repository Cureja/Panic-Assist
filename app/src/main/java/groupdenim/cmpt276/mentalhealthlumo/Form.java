package groupdenim.cmpt276.mentalhealthlumo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Form extends AppCompatActivity {

    SeekBar Intensity_sb;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    EditText what_Happened_tb;

    String time;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Intensity_sb = findViewById(R.id.Intensity_sb);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        what_Happened_tb = findViewById(R.id.What_Happened_tb);

        checkBox1.isChecked();

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                SQLiteHelper helper = new SQLiteHelper(Form.this);
                SQLiteDatabase database = helper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(SQLiteHelper.COL_CHECK1, checkBox1.isChecked() ? 1 : 0);
                contentValues.put(SQLiteHelper.COL_CHECK2, checkBox2.isChecked() ? 1 : 0);
                contentValues.put(SQLiteHelper.COL_CHECK3, checkBox3.isChecked() ? 1 : 0);
                contentValues.put(SQLiteHelper.COL_CHECK4, checkBox4.isChecked() ? 1 : 0);
                contentValues.put(SQLiteHelper.COL_CHECK5, checkBox5.isChecked() ? 1 : 0);
                contentValues.put(SQLiteHelper.COL_CHECK6, checkBox6.isChecked() ? 1 : 0);

                contentValues.put(SQLiteHelper.COL_WHAT, what_Happened_tb.getText().toString());

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

                SimpleDateFormat tf = new SimpleDateFormat("KK:mm:ss a");

                contentValues.put(SQLiteHelper.COL_DATE, df.format(c));
                contentValues.put(SQLiteHelper.COL_TIME, tf.format(c));
                contentValues.put(SQLiteHelper.COL_LOCATION, "SFU Burnaby");



                database.insert(SQLiteHelper.TAB_NAME, null, contentValues);
                finish();
            }
        });



    }
}
