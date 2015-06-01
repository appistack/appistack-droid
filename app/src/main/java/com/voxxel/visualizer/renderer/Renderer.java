package com.voxxel.visualizer.renderer;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.voxxel.visualizer.AudioData;
import com.voxxel.visualizer.FFTData;

abstract public class Renderer {
    protected float[] mPoints;
    protected float[] mFFTPoints;

    public Renderer() {

    }

    abstract public void onRender(Canvas canvas, AudioData data, Rect rect);

    abstract public void onRender(Canvas canvas, FFTData data, Rect rect);

    final public void render(Canvas canvas, AudioData data, Rect rect) {
        if (mPoints == null || mPoints.length < data.bytes.length * 4) {
            mPoints = new float[data.bytes.length * 4];
        }

        onRender(canvas, data, rect);
    }

    final public void render(Canvas canvas, FFTData data, Rect rect) {
        if (mPoints == null || mFFTPoints.length < data.bytes.length * 4) {
            mFFTPoints = new float[data.bytes.length * 4];
        }

        onRender(canvas, data, rect);
    }
}
