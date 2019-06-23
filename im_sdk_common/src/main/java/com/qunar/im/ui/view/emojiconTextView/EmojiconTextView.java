package com.qunar.im.ui.view.emojiconTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.qunar.im.common.R;

public class EmojiconTextView extends TextView {
    private int mEmojiconSize;
    private int mEmojiconTextSize;
    private long mLastActionDownTime = -1;
    private int mTextLength = -1;
    private int mTextStart = 0;
    private boolean mUseSystemDefault = false;

    public EmojiconTextView(Context context) {
        super(context);
        init(null);
    }

    public EmojiconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmojiconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.mEmojiconTextSize = (int) getTextSize();
        if (attrs == null) {
            this.mEmojiconSize = (int) getTextSize();
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emojicon);
            this.mEmojiconSize = (int) a.getDimension(R.styleable.Emojicon_emojiconSize, getTextSize());
            this.mTextStart = a.getInteger(R.styleable.Emojicon_emojiconTextStart, 0);
            this.mTextLength = a.getInteger(R.styleable.Emojicon_emojiconTextLength, -1);
            this.mUseSystemDefault = a.getBoolean(R.styleable.Emojicon_emojiconUseSystemDefault, false);
            a.recycle();
        }
        setText(getText());
    }

    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            EmojiconHandler.addEmojis(getContext(), builder, this.mEmojiconSize, this.mEmojiconTextSize, this.mTextStart, this.mTextLength, this.mUseSystemDefault);
            text = builder;
        }
        super.setText(text, type);
    }

    public void setEmojiconSize(int pixels) {
        this.mEmojiconSize = pixels;
        super.setText(getText());
    }

    public void setUseSystemDefault(boolean useSystemDefault) {
        this.mUseSystemDefault = useSystemDefault;
    }

    public boolean onTouchEvent(MotionEvent event) {
        CharSequence text = getText();
        if (text != null && (text instanceof Spannable)) {
            handleLinkMovementMethod(this, (Spannable) text, event);
        }
        return super.onTouchEvent(event);
    }

    private boolean handleLinkMovementMethod(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();
        if (action == 1 || action == 0) {
            int x = (((int) event.getX()) - widget.getTotalPaddingLeft()) + widget.getScrollX();
            int y = (((int) event.getY()) - widget.getTotalPaddingTop()) + widget.getScrollY();
            Layout layout = widget.getLayout();
            int off = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
            ClickableSpan[] link = (ClickableSpan[]) buffer.getSpans(off, off, ClickableSpan.class);
            if (link.length != 0) {
                if (action == 1) {
                    if (System.currentTimeMillis() - this.mLastActionDownTime > ((long) ViewConfiguration.getLongPressTimeout())) {
                        return false;
                    }
                    link[0].onClick(widget);
                    Selection.removeSelection(buffer);
                } else if (action == 0) {
                    Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                    this.mLastActionDownTime = System.currentTimeMillis();
                }
            }
        }
        return false;
    }
}
