package groupdenim.cmpt276.mentalhealthlumo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FormViewing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_viewing);

        TextView tv = findViewById(R.id.textView);

        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        String[] columns = {SQLiteHelper.COL_CHECK1,
                SQLiteHelper.COL_CHECK2,
                SQLiteHelper.COL_CHECK3,
                SQLiteHelper.COL_CHECK4,
                SQLiteHelper.COL_CHECK5,
                SQLiteHelper.COL_CHECK6,
                SQLiteHelper.COL_WHAT,
                SQLiteHelper.COL_DATE,
                SQLiteHelper.COL_TIME,
                SQLiteHelper.COL_LOCATION};

        Cursor cursor = database.query(SQLiteHelper.TAB_NAME, columns, null, null, null, null, null);

        String newText = "";

        while (cursor.moveToNext()) {
            int check1 = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COL_CHECK1));
            int check2 = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COL_CHECK2));
            int check3 = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COL_CHECK3));
            int check4 = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COL_CHECK4));
            int check5 = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COL_CHECK5));
            int check6 = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COL_CHECK6));
            String what = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_WHAT));
            String date = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_DATE));
            String time = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_TIME));
            String location = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_LOCATION));


            newText += ("what " + what +"\n");
            newText += ("date " + date +"\n");
            newText += ("time " + time +"\n");
            newText += ("location " + location +"\n");
            newText += ("symptoms: " + "\n");


            if (check1 == 1)
                newText += ("\t" + "Heart Racing/Pounding/Fluttering" + "\n");
            if (check2 == 1)
                newText += ("\t" + "Dizzyness/Lightheadness" + "\n");
            if (check3 == 1)
                newText += ("\t" + "Difficult Breathing" + "\n");
            if (check4 == 1)
                newText += ("\t" + "Chest Pain/Discomfort" + "\n");
            if (check5 == 1)
                newText += ("\t" + "Sweating" + "\n");
            if (check6 == 1)
                newText += ("\t" + "Other" + "\n");

            newText += "\n";
        }

        tv.setText(newText);
    }
}
