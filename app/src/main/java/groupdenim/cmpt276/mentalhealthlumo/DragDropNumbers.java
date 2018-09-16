package groupdenim.cmpt276.mentalhealthlumo;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class DragDropNumbers extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    TextView randA;
    TextView randB;
    TextView randC;
    TextView randD;
    TextView randE;

    TextView numbA;
    TextView numbB;
    TextView numbC;
    TextView numbD;
    TextView numbE;

    int numFinished = 0;
    int maxNumbers = 5;

    ArrayList<Integer> numList = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop_numbers);

        randA = findViewById(R.id.randA);
        randB = findViewById(R.id.randB);
        randC = findViewById(R.id.randC);
        randD = findViewById(R.id.randD);
        randE = findViewById(R.id.randE);

        numbA = findViewById(R.id.numbA);
        numbB = findViewById(R.id.numbB);
        numbC = findViewById(R.id.numbC);
        numbD = findViewById(R.id.numbD);
        numbE = findViewById(R.id.numbE);

        randA.setOnTouchListener(this);
        randB.setOnTouchListener(this);
        randC.setOnTouchListener(this);
        randD.setOnTouchListener(this);
        randE.setOnTouchListener(this);

        numbA.setOnDragListener(this);
        numbB.setOnDragListener(this);
        numbC.setOnDragListener(this);
        numbD.setOnDragListener(this);
        numbE.setOnDragListener(this);

        genRand();
    }

    public void genRand() {

        numList.clear();

        int num;

        num = (int) (Math.random() * 10);
        numList.add(num);
        randA.setText(num + "");

        num = (int) (Math.random() * 10);
        numList.add(num);
        randB.setText(num + "");

        num = (int) (Math.random() * 10);
        numList.add(num);
        randC.setText(num + "");

        num = (int) (Math.random() * 10);
        numList.add(num);
        randD.setText(num + "");

        num = (int) (Math.random() * 10);
        numList.add(num);
        randE.setText(num + "");

        Collections.sort(numList);


        numbA.setText("-");
        numbB.setText("-");
        numbC.setText("-");
        numbD.setText("-");
        numbE.setText("-");

        numFinished = 0;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        TextView dragged = (TextView) dragEvent.getLocalState();

        TextView container = (TextView) view;

        String text = dragged.getText().toString();
        if (text.equals("-")) {
            return false;
        }
        int viewNumber = Integer.parseInt(text);
        int targetNumber = 0;

        if (container.getId() == R.id.numbA) {
            targetNumber = numList.get(0);
        } else if (container.getId() == R.id.numbB) {
            targetNumber = numList.get(1);
        } else if (container.getId() == R.id.numbC) {
            targetNumber = numList.get(2);
        } else if (container.getId() == R.id.numbD) {
            targetNumber = numList.get(3);
        } else if (container.getId() == R.id.numbE) {
            targetNumber = numList.get(4);
        }

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DROP:
                if (!container.getText().toString().equals("-")) {
                    return false;
                } else if (viewNumber == targetNumber) {
                    container.setText(viewNumber + "");
                    dragged.setText("-");
                    container.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    numFinished++;
                    if (numFinished >= maxNumbers)
                        genRand();
                    return true;
                } else {
                    return false;
                }
            case DragEvent.ACTION_DRAG_ENDED:
                container.setBackgroundColor(Color.argb(0, 0, 0, 0));


                break;
            case DragEvent.ACTION_DRAG_ENTERED:



                if (viewNumber != targetNumber || !container.getText().toString().equals("-")) {
                    container.setBackgroundColor(Color.rgb(255, 0, 0));
                } else {
                    container.setBackgroundColor(Color.rgb(0, 255, 0));
                }

                break;
            case DragEvent.ACTION_DRAG_EXITED:
                container.setBackgroundColor(Color.argb(0, 0, 0, 0));
                break;
            case DragEvent.ACTION_DRAG_STARTED:

                break;
            default:
                break;

        }
        return true;


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);


            TextView tv = (TextView) view;
            if (tv.getText().toString().equals("-"))
                return false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0);
            } else {
                view.startDrag(data, shadowBuilder, view, 0);
            }
            return true;
        } else

            return false;
    }

}
