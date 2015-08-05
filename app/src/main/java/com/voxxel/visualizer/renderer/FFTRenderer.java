package com.voxxel.visualizer.renderer;

import android.graphics.Paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.voxxel.visualizer.AudioData;
import com.voxxel.visualizer.FFTData;

/**
 * Created by dc on 8/5/15.
 */
public class FFTRenderer extends Renderer {

    private Paint mPaint;
    private boolean mCycleColor;
    private boolean mFlashOnAmp;
    private float amplitude = 0;
    private float colorCounter = 0;

    public FFTRenderer(Paint paint) {
        this(paint, false);
    }

    public FFTRenderer(Paint paint, boolean cycleColor) {
        super();
        mPaint = paint;
        mCycleColor = cycleColor;
    }

    @Override
    public void onRender(Canvas canvas, AudioData data, Rect rect) {

    }

    @Override
    public void onRender(Canvas canvas, FFTData data, Rect rect) {
        if (mCycleColor) {
            cycleColor();
        }

        canvas.drawColor(Color.BLACK); // clear and draw

        float numBytes = data.bytes.length - 1;
        for (int i = 0; i < numBytes; i++) {
            byte rfk = data.bytes[i];
            byte ifk = data.bytes[i + 1];
            float magnitude = (rfk * rfk + ifk * ifk);
            float dbValue = (float) (10 * Math.log10(magnitude));

            mFFTPoints[i*4] = rect.width() * i/numBytes;
            mFFTPoints[i*4+2] = rect.width() * (i+1) / numBytes;
            mFFTPoints[i*4 + 1] = rect.height();
            mFFTPoints[i*4 + 3] = rect.height() - (dbValue * 2 - 10) * 10;
        }

        canvas.drawLines(mFFTPoints, mPaint);
    }

    private void logDrawPoints(int i) {
        Log.i("Draw Sample",
                mFFTPoints[i*4] + " " +
                mFFTPoints[i*4+2] + " " +
                mFFTPoints[i*4+1] + " " +
                mFFTPoints[i*4+3]);
    }

    private void cycleColor() {
        int r = (int)Math.floor(128*(Math.sin(colorCounter) + 3));
        int g = (int)Math.floor(128*(Math.sin(colorCounter + 2) + 1));
        int b = (int)Math.floor(128*(Math.sin(colorCounter + 7) + 1));
        mPaint.setColor(Color.argb(128, r, g, b));
        colorCounter += 0.050;
        //TODO: reset colorCounter if exceeds max?
    }


}
