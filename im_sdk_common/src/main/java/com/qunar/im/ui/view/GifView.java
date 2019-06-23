package com.qunar.im.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.qunar.im.common.R;
import java.io.InputStream;

public class GifView extends View {
    private static final int DEFAULT_MOVIE_DURATION = 1000;
    private int mCurrentAnimationTime;
    private float mLeft;
    private int mMeasuredMovieHeight;
    private int mMeasuredMovieWidth;
    private Movie mMovie;
    private int mMovieResourceId;
    private long mMovieStart;
    private volatile boolean mPaused;
    private float mScale;
    private float mTop;
    private boolean mVisible;
    private int multiFrame;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attrs) {
        this(context, attrs, R.styleable.CustomTheme_gifViewStyle);
    }

    public GifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentAnimationTime = 0;
        this.mVisible = true;
        this.mPaused = false;
        setViewAttributes(context, attrs, defStyle);
    }

    @SuppressLint({"NewApi"})
    private void setViewAttributes(Context context, AttributeSet attrs, int defStyle) {
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GifView, defStyle, R.style.Widget_GifView);
        this.mMovieResourceId = array.getResourceId(R.styleable.GifView_gif, -1);
        this.mPaused = array.getBoolean(R.styleable.GifView_paused, false);
        array.recycle();
        if (this.mMovieResourceId != -1) {
            this.mMovie = Movie.decodeStream(getResources().openRawResource(this.mMovieResourceId));
        }
    }

    public void setMovieStream(InputStream in, int mf) {
        this.multiFrame = mf;
        this.mMovie = Movie.decodeStream(in);
        requestLayout();
    }

    public Movie getMovie() {
        return this.mMovie;
    }

    public void setMovieTime(int time) {
        this.mCurrentAnimationTime = time;
        invalidate();
    }

    public void setPaused(boolean paused) {
        this.mPaused = paused;
        if (!paused) {
            this.mMovieStart = SystemClock.uptimeMillis() - ((long) this.mCurrentAnimationTime);
        }
        invalidate();
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mMovie != null) {
            int movieWidth = this.mMovie.width();
            int movieHeight = this.mMovie.height();
            int maximumWidth = MeasureSpec.getSize(widthMeasureSpec);
            this.mScale = 1.0f / (((float) movieWidth) / ((float) maximumWidth));
            this.mMeasuredMovieWidth = maximumWidth;
            this.mMeasuredMovieHeight = (int) (((float) movieHeight) * this.mScale);
            setMeasuredDimension(this.mMeasuredMovieWidth, this.mMeasuredMovieHeight);
            return;
        }
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.mLeft = ((float) (getWidth() - this.mMeasuredMovieWidth)) / 2.0f;
        this.mTop = ((float) (getHeight() - this.mMeasuredMovieHeight)) / 2.0f;
        this.mVisible = getVisibility() == 0;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.mMovie == null) {
            return;
        }
        if (this.multiFrame <= 1 || this.mPaused) {
            drawMovieFrame(canvas);
            return;
        }
        updateAnimationTime();
        drawMovieFrame(canvas);
        invalidateView();
    }

    @SuppressLint({"NewApi"})
    private void invalidateView() {
        if (!this.mVisible) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    private void updateAnimationTime() {
        long now = SystemClock.uptimeMillis();
        if (this.mMovieStart == 0) {
            this.mMovieStart = now;
        }
        int dur = this.mMovie.duration();
        if (dur == 0) {
            dur = 1000;
        }
        this.mCurrentAnimationTime = (int) ((now - this.mMovieStart) % ((long) dur));
    }

    private void drawMovieFrame(Canvas canvas) {
        this.mMovie.setTime(this.mCurrentAnimationTime);
        canvas.save(1);
        canvas.scale(this.mScale, this.mScale);
        this.mMovie.draw(canvas, this.mLeft / this.mScale, this.mTop / this.mScale);
        canvas.restore();
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int screenState) {
        boolean z = true;
        super.onScreenStateChanged(screenState);
        if (screenState != 1) {
            z = false;
        }
        this.mVisible = z;
        invalidateView();
    }

    /* Access modifiers changed, original: protected */
    @SuppressLint({"NewApi"})
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        this.mVisible = visibility == 0;
        invalidateView();
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.mVisible = visibility == 0;
        invalidateView();
    }
}
