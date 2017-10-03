package co.in.dreamguys.littlekids.Custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user5 on 11-09-2017.
 */

public class SF_CartoonTextView extends TextView {
    public SF_CartoonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SF_CartoonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SF_CartoonTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "SF_CARTOONIST_HAND_SC.ttf");
            setTypeface(tf);
        }
    }
}
