package com.example.admiralareospeedwag.catcountry;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Selmo on 2017-09-24.
 */

public class SwipeTest extends GestureDetector.SimpleOnGestureListener {

static String currentGestureDetected;

    @Override
    public boolean onSingleTapUp(MotionEvent event)
    {
        currentGestureDetected = event.toString();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event)
    {
        currentGestureDetected = event.toString();
    }

    @Override
    public void onLongPress(MotionEvent event)
    {
        currentGestureDetected = event.toString();
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY)
    {
        currentGestureDetected = event1.toString() +" "+event2.toString();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event)
    {
        currentGestureDetected = event.toString();

        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
    {
        currentGestureDetected = event1.toString() + " " + event2.toString();

        return true;
    }

}
