package dontwan.alertify;

import android.graphics.Color;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Alertify extends BaseTransientBottomBar<Alertify> {

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    private Alertify(@NonNull ViewGroup parent, @NonNull View content, @NonNull ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static Alertify make(@NonNull ViewGroup parent, @NonNull int layoutId, @Duration int duration) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = getLayout(layoutId);
        View content = inflater.inflate(layout, parent, false);
        ContentViewCallback viewCallback = new ContentViewCallback(content);
        Alertify alertify = new Alertify(parent, content, viewCallback);

        if (duration > getDuration(LENGTH_INFINITE)) {
            alertify.setDuration(duration);
        }

        alertify.getView().setPadding(0, 0, 0, 0); // Padding fix
        alertify.getView().setBackgroundColor(Color.TRANSPARENT); // Background fix
        return alertify;
    }

    private static int getLayout(int layoutId) {
        switch (layoutId) {
            case 0:
                return R.layout.snackbar_view;
            case 1:
                return R.layout.snackbar_view_button;
            case 2:
                return R.layout.snackbar_view_icon;
            case 3:
                return R.layout.snackbar_view_alertify_default;
            case 4:
                return R.layout.snackbar_view_alertify_succeed;
            case 5:
                return R.layout.snackbar_view_alertify_error;
            case 6:
                return R.layout.snackbar_view_custom;
            default:
                return R.layout.snackbar_view_alertify_default;
        }
    }

    private static int getDuration(int duration) {
        switch (duration) {
            case 0:
                return 2000; // 2 seconds
            case 1:
                return 3500; // 3,5 seconds
            case 2:
                return -1; // Inifite
            default:
                return 2000;
        }
    }

    public Alertify setPosition(int position) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getView().getLayoutParams();
        params.gravity = position;
        getView().setLayoutParams(params);
        return this;
    }

    public Alertify setBackground(@ColorInt int color) {
        getView().setBackgroundColor(color);
        return this;
    }

    public Alertify setText(CharSequence text) {
        TextView textView = (TextView) getView().findViewById(R.id.snackbar_text);
        textView.setText(text);
        return this;
    }

    public Alertify setTitle(CharSequence title) {
        TextView textView = (TextView) getView().findViewById(R.id.tvTitle);
        textView.setText(title);
        return this;
    }

    public Alertify setSubTitle(CharSequence subTitle) {
        TextView textView = (TextView) getView().findViewById(R.id.tvSubTitle);
        textView.setText(subTitle);
        return this;
    }

    public Alertify setTextSize(float size) {
        TextView textView = (TextView) getView().findViewById(R.id.snackbar_text);
        textView.setTextSize(size);
        return this;
    }

    public Alertify setTextColor(int color) {
        TextView textView = (TextView) getView().findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
        return this;
    }

    public Alertify setButtonText(CharSequence text) {
        Button button = (Button) getView().findViewById(R.id.btnCustom);
        button.setText(text);
        return this;
    }

    public Alertify setButtonColor(@ColorInt int color) {
        Button button = (Button) getView().findViewById(R.id.btnCustom);
        button.setTextColor(color);
        return this;
    }

    public Alertify setButtonColor(String color) {
        Button button = (Button) getView().findViewById(R.id.btnCustom);
        button.setTextColor(Color.parseColor(color));
        return this;
    }

    public Alertify setButtonEnabled(boolean enabled) {
        Button button = (Button) getView().findViewById(R.id.btnCustom);
        button.setEnabled(enabled);
        return this;
    }

    public Alertify setIcon(int resourceId) {
        ImageView imageView = (ImageView) getView().findViewById(R.id.ivIcon);
        imageView.setImageResource(resourceId);
        Animation pulse = AnimationUtils.loadAnimation(imageView.getContext(), R.anim.pulse);
        imageView.startAnimation(pulse);
        return this;
    }

    public Alertify setIconAnimation(@AnimRes int animation) {
        ImageView imageView = (ImageView) getView().findViewById(R.id.ivIcon);
        Animation anim = AnimationUtils.loadAnimation(getView().getContext(), getAnimation(animation));
        anim.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(anim);
        return this;
    }

    private int getAnimation(int animation) {
        switch (animation) {
            case 0:
                return R.anim.pulse;
            case 1:
                return R.anim.rotate;
            case 2:
                return R.anim.bounce;
            case 3:
                return R.anim.fade_in;
            default:
                return R.anim.pulse;
        }
    }

    public Alertify setButtonAction(CharSequence text, final View.OnClickListener listener) {
        Button actionView = (Button) getView().findViewById(R.id.snackbar_action);
        actionView.setText(text);
        actionView.setVisibility(View.VISIBLE);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                dismiss();
            }
        });
        return this;
    }

    public Alertify setSwipeDismiss(boolean enabled) {
        if(enabled) {
            getView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x1 = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            x2 = event.getX();
                            float deltaX = x2 - x1;
                            if (Math.abs(deltaX) > MIN_DISTANCE) {
                                if (x2 > x1) {
                                    // Left to Right swipe action
                                    dismiss();
                                } else {
                                    // Right to left swipe action
                                    dismiss(); 
                                }
                            }
                            break;
                    }
                    return false;
                }
            });
        }
        return this;
    }

    public Alertify setOnTouchDismiss(boolean enabled){
        if(enabled){
            getView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dismiss();
                    return false;
                }
            });
        }
        return this;
    }

    private static class ContentViewCallback implements BaseTransientBottomBar.ContentViewCallback {
        private View content;

        public ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            Animation anim = AnimationUtils.loadAnimation(content.getContext(), R.anim.slide_in_from_top);
            content.startAnimation(anim);
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            Animation anim = AnimationUtils.loadAnimation(content.getContext(), R.anim.slide_out_from_top);
            content.startAnimation(anim);
        }
    }

    public static final int LENGTH_SHORT = 1; // 3,5 seconds
    public static final int LENGTH_LONG = 2; // 2 seconds
    public static final int LENGTH_INFINITE = 3; // endless
    public static final int POSITION_TOP = Gravity.TOP;
    public static final int POSITION_BOTTOM = Gravity.BOTTOM;
    public static final int POSITION_CENTER = Gravity.CENTER;
    public static final int LAYOUT_NORMAL = 0;
    public static final int LAYOUT_WITH_BUTTON = 1;
    public static final int LAYOUT_WITH_ICON = 2;
    public static final int LAYOUT_ALERTIFY_DEFAULT = 3;
    public static final int LAYOUT_ALERTIFY_SUCCEED = 4;
    public static final int LAYOUT_ALERTIFY_ERROR = 5;
    public static final int LAYOUT_ALERTIFY_CUSTOM_EXAMPLE = 6;
    public static final int ANIMATION_PULSE = 0;
    public static final int ANIMATION_ROTATE = 1;
    public static final int ANIMATION_BOUNCE = 2;
    public static final int ANIMATION_FADE_IN = 3;
}
