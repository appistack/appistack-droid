package com.voxxel.visualizer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.AttributeSet;
import android.view.View;

public class VisualizerView extends View {
    private byte[] mBytes;
    private byte[] mFFTBytes;
    private Visualizer mVisualizer;
    private Rect mRect = new Rect();
    //private Renderer mRenderer;

    private Paint mFlashPaint = new Paint();
    private boolean mFlash = false;
    private Bitmap mCanvasBitmap;
    private Canvas mCanvas;

    private Integer sx;
    private Integer sy;

    public VisualizerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public VisualizerView(Context context) {
        super(context, null, 0);
        init();
    }

    private void init() {
        mBytes = null;
        mFFTBytes = null;

        mFlashPaint.setColor(Color.argb(122, 255, 255, 255));

        sx = getWidth();
        sy = getHeight();
    }

    public void link(MediaPlayer player) {
        if (player == null) {
            throw new NullPointerException("Cannot link to null MediaPlayer");
        }

        mVisualizer = new Visualizer(player.getAudioSessionId());
        mVisualizer.setCaptureSize(player.getAudioSessionId());
    }

    public void release() {
        mVisualizer.release();
    }

    public void flash() {
        mFlash = true;
        invalidate();
    }

    public void updateVisualizer(byte[] bytes) {
        mBytes = bytes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRect.set(0,0,sx,sy);
        if (mCanvasBitmap == null) {
            mCanvasBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        }

        if (mCanvas == null) {
            mCanvas = new Canvas(mCanvasBitmap);
        }

        if(mFlash) {
            mFlash = false;
            mCanvas.drawPaint(mFlashPaint);
        }

        canvas.drawLine(0,0,canvas.getWidth(), canvas.getHeight(), mFlashPaint);
//        canvas.drawBitmap(mCanvasBitmap, new Matrix(), null);
    }

}
