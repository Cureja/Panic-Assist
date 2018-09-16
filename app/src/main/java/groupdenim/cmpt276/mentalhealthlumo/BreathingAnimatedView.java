package groupdenim.cmpt276.mentalhealthlumo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

//source: https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-12-animations/12-1-p-property-animation/12-1-p-property-animation.html

public class BreathingAnimatedView extends View {

    private float mRadius;
    private static final int ANIMATION_DURATION = 3500;
    private static final long ANIMATION_DELAY = 3500;

    private AnimatorSet mPulseAnimatorSet = new AnimatorSet();

    private float mX;
    private float mY;

    private Paint mPaint = new Paint();
    private Context mContext;

    private static String TAG = "BreathingAnimatedView";

    public BreathingAnimatedView(Context context) {
        this(context, null);
        mContext = context;
        Log.d(TAG, "default constructor entered");
    }

    public BreathingAnimatedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Log.d(TAG, "constructor entered w attrs");
    }

    public void setRadius(float radius) {
        mRadius = radius;
        invalidate();
    }

    public void animationActivated(int x, int y) {
        Log.d(TAG, "animationActivated entered");

        mX = x;
        mY = y;

        Log.d(TAG, "value of mx =" + x);
        Log.d(TAG, "value of my =" + y);

        Log.d(TAG, "mPulseAnimatorSet = " + mPulseAnimatorSet);
        Log.d(TAG, "mPulseAnimatorSet.isRunning = " + mPulseAnimatorSet.isRunning());

        if(mPulseAnimatorSet != null && mPulseAnimatorSet.isRunning()) {
            Log.d(TAG, "if loop hit");
            mPulseAnimatorSet.cancel();
        }

        mPulseAnimatorSet.start();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {


        Log.d(TAG, "onSizeChanged hit");
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this,
                "radius", (getWidth()/4), (getWidth()/3));
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this,
                "radius", (getWidth()/3), (getWidth()/4));
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        ObjectAnimator repeatAnimator = ObjectAnimator.ofFloat(this,
                "radius", (getWidth()/4), (getWidth()/3));
        repeatAnimator.setStartDelay(ANIMATION_DELAY);
        repeatAnimator.setDuration(ANIMATION_DURATION);
        repeatAnimator.setRepeatCount(1);
        repeatAnimator.setRepeatMode(ValueAnimator.REVERSE);

        mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
        mPulseAnimatorSet.play(repeatAnimator).after(shrinkAnimator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw activated");
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, mPaint);
    }

}
