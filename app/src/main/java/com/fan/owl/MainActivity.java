package com.fan.owl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private View leftHandView;// the left hand view
    private View rightHandView;// the right hand view
    private View leftArmView;// the left arm view, initial state is gone
    private View rightArmView;// the right arm view, initial state is gone
    private EditText passwordEdit;
    private View owlHeadView;// the head of owl

    private int handWidth;// the small and round hand's width
    private int handHeight;// the small and round hand's height

    private int leftHandViewLeft;// the left edge of left small round hand view
    private int leftHandViewRight;// the right edge of left small round hand view
    private int leftHandViewBottom;//  the bottom edge of left small round hand view
    private int leftHandViewTop;// the top edge of left small round hand view

    private int rightHandViewLeft;
    private int rightHandViewRight;
    private int rightHandViewBottom;
    private int rightHandViewTop;

    private int owlHeadViewLeft;// the left edge of owl head view
    private int owlHeadViewRight;// the right edge of owl head view
    private int owlHeadViewBottom;// the bottom edge of owl head view
    private int owlHeadViewTop;// the top edge of owl head view

    private int leftArmViewLeft;// the left edge of left arm view
    private int leftArmViewRight;
    private int leftArmViewTop;
    private int leftArmViewBottom;

    private int rightArmViewLeft;
    private int rightArmViewRight;
    private int rightArmViewTop;
    private int rightArmViewBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        owlHeadView = findViewById(R.id.owl_head_view);
        owlHeadView.setFocusable(true);
        owlHeadView.requestFocus();
        leftHandView = findViewById(R.id.left_hand_view);
        rightHandView = findViewById(R.id.right_hand_view);
        leftArmView = findViewById(R.id.left_arm_view);
        rightArmView = findViewById(R.id.right_arm_view);
        passwordEdit = (EditText) findViewById(R.id.password_edit);

        // listen to the changes in the focus of the passwordEdit
        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    hideEyes();
                } else {
                    showEyes();
                }
            }
        });

        //////////////////////////////////////////////////////////
        ////// Get all view's position
        //////////////////////////////////////////////////////////
        
        ViewTreeObserver vto1 = leftHandView.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                leftHandView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                handHeight = leftHandView.getHeight();
                handWidth = leftHandView.getWidth();

                leftHandViewLeft = leftHandView.getLeft();
                leftHandViewRight = leftHandView.getRight();
                leftHandViewTop = leftHandView.getTop();
                leftHandViewBottom = leftHandView.getBottom();
            }
        });

        ViewTreeObserver vto2 = rightHandView.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rightHandView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                handHeight = rightHandView.getHeight();
                handWidth = rightHandView.getWidth();

                rightHandViewLeft = rightHandView.getLeft();
                rightHandViewRight = rightHandView.getRight();
                rightHandViewTop = rightHandView.getTop();
                rightHandViewBottom = rightHandView.getBottom();
            }
        });

        ViewTreeObserver vto3 = owlHeadView.getViewTreeObserver();
        vto3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                owlHeadView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                owlHeadViewLeft = owlHeadView.getLeft();
                owlHeadViewRight = owlHeadView.getRight();
                owlHeadViewBottom = owlHeadView.getBottom();
                owlHeadViewTop = owlHeadView.getTop();
            }
        });
        ViewTreeObserver vto4 = leftArmView.getViewTreeObserver();
        vto4.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                leftArmView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                leftArmViewLeft = leftArmView.getLeft();
                leftArmViewRight = leftArmView.getRight();
                leftArmViewBottom = leftArmView.getBottom();
                leftArmViewTop = leftArmView.getTop();
            }
        });
        ViewTreeObserver vto5 = rightArmView.getViewTreeObserver();
        vto5.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rightArmView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                rightArmViewLeft = rightArmView.getLeft();
                rightArmViewRight = rightArmView.getRight();
                rightArmViewBottom = rightArmView.getBottom();
                rightArmViewTop = rightArmView.getTop();
            }
        });
    }


    /**
     * show owl's eyes when the passwordEditText focused
     */
    private void showEyes() {
        float currentX = leftHandView.getTranslationX();
        float toX = leftArmViewLeft - leftHandViewLeft - 24;

        ObjectAnimator a1 = ObjectAnimator.ofFloat(leftHandView, "translationX", currentX, currentX - toX);
        a1.setDuration(250);

        float currentY = leftHandView.getTranslationY();
        final float toY = DensityUtil.dp2px(MainActivity.this, 8f);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(leftHandView, "translationY", currentY, currentY + toY);
        a2.setDuration(250);

        ObjectAnimator a3 = ObjectAnimator.ofFloat(leftHandView, "alpha", 0f, 1f);
        a3.setDuration(250);

        ObjectAnimator a4 = ObjectAnimator.ofFloat(leftHandView, "scaleY", 0.7f, 1f);
        a4.setDuration(250);

        ObjectAnimator a5 = ObjectAnimator.ofFloat(leftHandView, "scaleX", 0.7f, 1f);
        a5.setDuration(250);

        float leftArmCurrentX = leftArmView.getTranslationX();
        float leftArmCurrentY = leftArmView.getTranslationY();
        ObjectAnimator a6 = ObjectAnimator.ofFloat(leftArmView, "translationX", leftArmCurrentX, leftArmCurrentX - 44);
        ObjectAnimator a7 = ObjectAnimator.ofFloat(leftArmView, "translationY", leftArmCurrentY, leftArmCurrentY + toY);
        a6.setDuration(100);
        a7.setDuration(100);

        ObjectAnimator a8 = ObjectAnimator.ofFloat(leftArmView, "alpha", 1f, 0.6f);
        a8.setDuration(100);
        a7.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                leftArmView.setVisibility(View.INVISIBLE);
                float x = leftArmView.getTranslationX();
                float y = leftArmView.getTranslationY();
                ObjectAnimator b1 = ObjectAnimator.ofFloat(leftArmView, "translationX", x, x + 44);
                ObjectAnimator b2 = ObjectAnimator.ofFloat(leftArmView, "translationY", y, y - toY);
                b1.setDuration(20);
                b2.setDuration(20);
                ObjectAnimator b3 = ObjectAnimator.ofFloat(leftArmView, "alpha", 1f, 0.6f);
                b3.setDuration(20);
                AnimatorSet set = new AnimatorSet();
                set.play(b1).with(b2).with(b3);
                set.start();
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.play(a1).with(a2).with(a3).with(a4).with(a5).with(a6).with(a7);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationStart(Animator animation) {
                leftHandView.setAlpha(0.0f);
                leftHandView.setVisibility(View.VISIBLE);
            }
        });
        set.start();


        float currentX2 = rightHandView.getTranslationX();
        float toX2 = rightHandViewRight - rightArmViewRight - 24;

        ObjectAnimator o1 = ObjectAnimator.ofFloat(rightHandView, "translationX", currentX2, currentX2 + toX2);
        o1.setDuration(250);

        float currentY2 = rightHandView.getTranslationY();
        final float toY2 = DensityUtil.dp2px(MainActivity.this, 8f);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(rightHandView, "translationY", currentY2, currentY2 + toY2);
        o2.setDuration(250);

        ObjectAnimator o3 = ObjectAnimator.ofFloat(rightHandView, "alpha", 0f, 1f);
        o3.setDuration(250);

        ObjectAnimator o4 = ObjectAnimator.ofFloat(rightHandView, "scaleY", 0.7f, 1f);
        o4.setDuration(250);

        ObjectAnimator o5 = ObjectAnimator.ofFloat(rightHandView, "scaleX", 0.7f, 1f);
        o5.setDuration(250);

        float rightArmCurrentX = rightArmView.getTranslationX();
        float rightArmCurrentY = rightArmView.getTranslationY();
        ObjectAnimator o6 = ObjectAnimator.ofFloat(rightArmView, "translationX", rightArmCurrentX, rightArmCurrentX + 44);
        ObjectAnimator o7 = ObjectAnimator.ofFloat(rightArmView, "translationY", rightArmCurrentY, rightArmCurrentY + toY2);
        o6.setDuration(100);
        o7.setDuration(100);

        ObjectAnimator o8 = ObjectAnimator.ofFloat(rightArmView, "alpha", 1f, 0.6f);
        o8.setDuration(100);
        o7.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rightArmView.setVisibility(View.INVISIBLE);
                float x = rightArmView.getTranslationX();
                float y = rightArmView.getTranslationY();
                ObjectAnimator b1 = ObjectAnimator.ofFloat(rightArmView, "translationX", x, x - 44);
                ObjectAnimator b2 = ObjectAnimator.ofFloat(rightArmView, "translationY", y, y - toY);
                b1.setDuration(20);
                b2.setDuration(20);
                ObjectAnimator b3 = ObjectAnimator.ofFloat(rightArmView, "alpha", 1f, 0.6f);
                b3.setDuration(20);
                AnimatorSet set = new AnimatorSet();
                set.play(b1).with(b2).with(b3);
                set.start();
            }
        });

        AnimatorSet set2 = new AnimatorSet();
        set2.play(o1).with(o2).with(o3).with(o4).with(o5).with(o6).with(o7);
        set2.setInterpolator(new DecelerateInterpolator());
        set2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationStart(Animator animation) {
                rightHandView.setAlpha(0.0f);
                rightHandView.setVisibility(View.VISIBLE);
            }
        });
        set2.start();
    }


    /**
     * hide the owl's eyes when the passwordEditText lose focus
     */
    private void hideEyes() {
        float currentX = leftHandView.getTranslationX();
        float toX = leftArmViewLeft - leftHandViewLeft - 24;

        ObjectAnimator a1 = ObjectAnimator.ofFloat(leftHandView, "translationX", currentX, currentX + toX);
        a1.setDuration(250);

        float currentY = leftHandView.getTranslationY();
        float toY = DensityUtil.dp2px(MainActivity.this, 8f);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(leftHandView, "translationY", currentY, currentY - toY);
        a2.setDuration(250);


        ObjectAnimator a3 = ObjectAnimator.ofFloat(leftHandView, "alpha", 1f, 0.6f);
        a3.setDuration(250);

        ObjectAnimator a4 = ObjectAnimator.ofFloat(leftHandView, "scaleY", 1f, 0.7f);
        a4.setDuration(250);

        ObjectAnimator a5 = ObjectAnimator.ofFloat(leftHandView, "scaleX", 1f, 0.7f);
        a5.setDuration(250);

        ObjectAnimator a6 = ObjectAnimator.ofFloat(leftArmView, "alpha", 0f, 1f);
        a6.setDuration(100);
        a6.setStartDelay(150);

        AnimatorSet set = new AnimatorSet();
        set.play(a1).with(a2).with(a3).with(a4).with(a5).with(a6);
        set.setInterpolator(new AccelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                leftHandView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                leftArmView.setAlpha(0.0f);
                leftArmView.setVisibility(View.VISIBLE);
            }
        });


        float currentX2 = rightHandView.getTranslationX();
        int toX2 = rightHandViewRight - rightArmViewRight - 24;

        ObjectAnimator o1 = ObjectAnimator.ofFloat(rightHandView, "translationX", currentX2, currentX2 - toX2);
        o1.setDuration(250);


        int toY2 = DensityUtil.dp2px(MainActivity.this, 8f);
        float currentY2 = rightHandView.getTranslationY();

        ObjectAnimator o2 = ObjectAnimator.ofFloat(rightHandView, "translationY", currentY2, currentY2 - toY2);
        o2.setDuration(250);


        ObjectAnimator o3 = ObjectAnimator.ofFloat(rightHandView, "alpha", 1f, 0.6f);
        o3.setDuration(250);

        ObjectAnimator o4 = ObjectAnimator.ofFloat(rightHandView, "scaleY", 1f, 0.7f);
        o4.setDuration(250);

        ObjectAnimator o5 = ObjectAnimator.ofFloat(rightHandView, "scaleX", 1f, 0.7f);
        o5.setDuration(250);

        ObjectAnimator o6 = ObjectAnimator.ofFloat(rightArmView, "alpha", 0.0f, 1f);
        o6.setDuration(100);
        o6.setStartDelay(150);

        AnimatorSet set2 = new AnimatorSet();
        set2.play(o1).with(o2).with(o3).with(o4).with(o5).with(o6);
        set2.setInterpolator(new AccelerateInterpolator());
        set2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rightHandView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                rightArmView.setAlpha(0.0f);
                rightArmView.setVisibility(View.VISIBLE);
            }
        });

        set.start();
        set2.start();
    }
}