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
import android.util.Log;
import android.view.View;

import com.voxxel.visualizer.renderer.FFTRenderer;
import com.voxxel.visualizer.renderer.OscillatorRenderer;
import com.voxxel.visualizer.renderer.Renderer;

public class VisualizerView extends View {
    private byte[] mBytes;
    private byte[] mFFTBytes;
    private Visualizer mVisualizer;
    private Rect mRect = new Rect();

    private OscillatorRenderer oscillatorRenderer;
    private FFTRenderer fftRenderer;

    private Paint mFlashPaint = new Paint();
    private boolean mFlash = false;
    private Bitmap mCanvasBitmap;
    private Canvas mCanvas;
    public boolean renderFFT = false;

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
        mBytes = new byte[0];
        mFFTBytes = new byte[0];

        mFlashPaint.setColor(Color.argb(122, 255, 255, 255));

        setOscillatorRenderer();
        setFFTRenderer();
    }

    private void setOscillatorRenderer() {
        Paint paint = new Paint();
        paint.setStrokeWidth(4.0f);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(200, 56, 138, 252));

        //TODO: fix flash animation - it doesn't draw this color
        Paint lineFlashPaint = new Paint();
        paint.setStrokeWidth(8f);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(188, 254, 254, 254));
        oscillatorRenderer = new OscillatorRenderer(paint, lineFlashPaint, false, true);
    }

    private void setFFTRenderer() {
        Paint paint = new Paint();
        paint.setStrokeWidth(4.0f);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(200, 56, 138, 252));

        fftRenderer = new FFTRenderer(paint, true);
    }

    public void link(MediaPlayer player) {
        if (player == null) {
            throw new NullPointerException("Cannot link to null MediaPlayer");
        }

        mVisualizer = new Visualizer(player.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                updateVisualizer(bytes);
            }
            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                updateVisualizerFFT(bytes);
            }
        };

        mVisualizer.setDataCaptureListener(captureListener, Visualizer.getMaxCaptureRate()/2, true, true);

        mVisualizer.setEnabled(true);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVisualizer.setEnabled(false);
            }
        });
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

    public void updateVisualizerFFT(byte[] bytes) {
        mFFTBytes = bytes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRect.set(0,0,canvas.getWidth(), canvas.getHeight());
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

        //TODO: apparently I should preallocate these byte[]'s since their length won't change
        AudioData audioData = new AudioData(mBytes);
        FFTData fftData = new FFTData(mFFTBytes);

        if (renderFFT) {
            fftRenderer.render(mCanvas, fftData, mRect);
        } else {
            oscillatorRenderer.render(mCanvas, audioData, mRect);
        }

        canvas.drawBitmap(mCanvasBitmap, new Matrix(), null);
    }

}
