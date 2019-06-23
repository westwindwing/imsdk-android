package com.qunar.im.ui.view.emojiconEditView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.qunar.im.base.util.Utils;
import com.qunar.im.common.R;
import com.qunar.im.ui.view.emojiconTextView.EmojiconHandler;

public class EmojiconEditText extends AppCompatEditText {
    private int mEmojiconSize;
    private int mEmojiconTextSize;
    private boolean mUseSystemDefault;

    public EmojiconEditText(Context context) {
        super(context);
        this.mUseSystemDefault = false;
        this.mEmojiconSize = (int) getTextSize();
        this.mEmojiconTextSize = (int) getTextSize();
    }

    public EmojiconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUseSystemDefault = false;
        init(attrs);
    }

    public EmojiconEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mUseSystemDefault = false;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emojicon);
        this.mEmojiconSize = (int) a.getDimension(R.styleable.Emojicon_emojiconSize, getTextSize());
        this.mUseSystemDefault = a.getBoolean(R.styleable.Emojicon_emojiconUseSystemDefault, false);
        a.recycle();
        this.mEmojiconTextSize = (int) getTextSize();
        this.mEmojiconSize += Utils.sp2px(getContext(), 3.0f);
        this.mEmojiconTextSize += Utils.sp2px(getContext(), 3.0f);
        setText(getText());
    }

    /* Access modifiers changed, original: protected */
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        updateText();
    }

    public void setEmojiconSize(int pixels) {
        this.mEmojiconSize = pixels;
        updateText();
    }

    private void updateText() {
        EmojiconHandler.addEmojisForEdit(getContext(), getText(), this.mEmojiconSize, this.mEmojiconTextSize, 0, -1, this.mUseSystemDefault);
    }

    public void setUseSystemDefault(boolean useSystemDefault) {
        this.mUseSystemDefault = useSystemDefault;
    }
}
