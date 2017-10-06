package com.example.admiralareospeedwag.catcountry;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Selmo on 2017-09-24.
 */

public class AnimationTest extends Activity {

    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
    }

    class GameView extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder surfaceHolder;
        volatile boolean playing;

        Canvas canvas;
        Paint paint;

        long fps;

        private long timePerFrame;

        Bitmap bitmapAnim;

        boolean isMoving = false;

        float walkSPeedPerSecond = 250;

        float animXPos = 10;

        private int frameWidth = 100;
        private int frameHeight = 50;

        private int frameCount = 5;
        private int currentFrame = 0;

        private long lastFrameChangeTime = 0;

        private int frameLengthInMilliseconds = 100;

        private Rect frameToDraw = new Rect(0, 0, frameWidth, frameHeight);

        RectF whereToDraw = new RectF(animXPos, 0,  animXPos + frameWidth, frameHeight);

        public GameView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint = new Paint();
            InputStream inputStream = null;
            try {
                inputStream = getAssets().open("spritestrip.png");
            } catch (IOException e)
            {}
            bitmapAnim = BitmapFactory.decodeStream(inputStream );
        bitmapAnim = Bitmap.createScaledBitmap(bitmapAnim, frameWidth * frameCount, frameHeight, false);
        }

        @Override
        public void run()
        {
            while(playing)
            {
                long startFrameTime = System.currentTimeMillis();
                update();
                draw();
                timePerFrame = System.currentTimeMillis() - startFrameTime;
                if(timePerFrame >= 1)
                {
                    fps = 1000/timePerFrame;
                }
            }
        }

        public void update()
        {
            if(isMoving)
            {
                animXPos = animXPos + (walkSPeedPerSecond / fps);
            }
        }

        public void draw()
        {
            if(surfaceHolder.getSurface().isValid())
            {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.argb(255,26,128, 182));
                paint.setColor(Color.argb(255, 249, 129, 0));
                paint.setTextSize(45);
                canvas.drawText("FPS: " + fps, 20, 40, paint);

                whereToDraw.set((int)animXPos, 0, (int)animXPos + frameWidth, frameHeight);
                getCurrentFrame();
                canvas.drawBitmap(bitmapAnim, frameToDraw, whereToDraw, paint);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause()
        {
            playing = false;
            try {
                gameThread.join();
            }
            catch(InterruptedException e)
            {
                Log.e("Error:", "joining thread");
            }
        }

        public void resume()
        {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        public boolean onTouchEvent(MotionEvent motionEvent)
        {
            switch(motionEvent.getAction() & motionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_DOWN:
                    isMoving = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isMoving = false;
                    break;
            }
            return true;
        }

        public void getCurrentFrame()
        {
            long time = System.currentTimeMillis();
            if(isMoving)
            {
                if(time > lastFrameChangeTime + frameLengthInMilliseconds)
                {
                    lastFrameChangeTime = time;
                    currentFrame++;
                    if(currentFrame >= frameCount)
                    {
                        currentFrame = 0;
                    }
                }
            }

            frameToDraw.left = currentFrame * frameWidth;
            frameToDraw.right = frameToDraw.left + frameWidth;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        gameView.pause();
    }


}
