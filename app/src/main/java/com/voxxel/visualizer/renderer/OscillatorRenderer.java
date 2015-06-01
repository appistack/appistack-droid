package com.voxxel.visualizer.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.voxxel.visualizer.AudioData;
import com.voxxel.visualizer.FFTData;

public class OscillatorRenderer extends Renderer {
    private Paint mPaint;
    private Paint mFlashPaint;
    private boolean mCycleColor;
    private boolean mFlashOnAmp;
    private float amplitude = 0;
    private float colorCounter = 0;

    public OscillatorRenderer(Paint paint, Paint flashPaint) {
        this(paint, flashPaint, true, false);
    }

    public OscillatorRenderer(Paint paint, Paint flashPaint, boolean flashOnAmp) {
        this(paint, flashPaint, flashOnAmp, false);
    }

    public OscillatorRenderer(Paint paint, Paint flashPaint, boolean flashOnAmp, boolean cycleColor) {
        super();
        mPaint = paint;
        mFlashPaint = flashPaint;
        mFlashOnAmp = flashOnAmp;
        mCycleColor = cycleColor;
    }

    @Override
    public void onRender(Canvas canvas, AudioData data, Rect rect) {
        if (mCycleColor) {
            cycleColor();
        }

        canvas.drawColor(Color.BLACK); // clear and draw

        float accumulator = 0;
        float numBytes = data.bytes.length - 1;
        for (int i = 0; i < numBytes; i++) {
            mPoints[i*4] = rect.width() * i/numBytes;
            mPoints[i*4+2] = rect.width() * (i+1) / numBytes;
            mPoints[i*4+1] = rect.height()/2f + (data.bytes[i] * (rect.height() / 2f) / 128f);
            mPoints[i*4+3] = rect.height()/2f + (data.bytes[i+1] * (rect.height() / 2f) / 128f);
            accumulator += Math.abs(data.bytes[i]);
        }

        float amp = accumulator/(128 * data.bytes.length);
        if ((amp > amplitude) && mFlashOnAmp) {
            amplitude = amp;
            canvas.drawLines(mPoints, mFlashPaint);
        } else {
            amplitude *= 0.99;
            canvas.drawLines(mPoints, mPaint);
        }
    }

    @Override
    public void onRender(Canvas canvas, FFTData data, Rect rect) {
        //do nothing for FFTData on oscillator
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
