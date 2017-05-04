package com.edmond.jyview.animation;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by edmond on 17-3-19.
 */

public class FailJumpAnimation {
    private View view;
    private int time;
    private float stopHeight;
    private float stopWidth;

    public FailJumpAnimation(View view, float stopHeight, float stopWidth, int time){
        this.view = view;
        this.stopHeight = stopHeight;
        this.stopWidth = stopWidth;
        this.time = time;
    }

    public void start(){
        ValueAnimator animator = new ValueAnimator();
        animator.setTarget(view);
        animator.setObjectValues(new PointF(0,0),new PointF(0,stopHeight));
        animator.setDuration(time);
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            boolean isFail = true;
            float g =  (stopHeight*8.0f)/5.0f/0.5f/0.5f;
            float vp = stopWidth-100.0f;
            float vh = 0.0f;
            float vhtemp = 0.0f;
            float f = 0.0f;
            float ftemp = 0.0f;
            float y = 0.0f;
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                if(vhtemp<=0&&!isFail){
                    y = view.getY();
                    isFail = true;
                    f = fraction;
                }
                if(y+0.5f * g * ((fraction-f) * 4) * ((fraction-f) * 4)>=stopHeight*4/5&&isFail){
                    isFail = false;
                    vh = (float) (vhtemp * Math.sqrt(2)/2);
                    f = fraction;
                }

                if(isFail){
                    point.y = y + 0.5f * g * ((fraction - f) * 4) * ((fraction -f)* 4);//下落
                    vhtemp = vhtemp + g*(fraction-ftemp)*4.0f;
                }else{
                    //上升
                    point.y = stopHeight*4/5 - vh*((fraction-f)*4)+0.5f * g * ((fraction - f) * 4) * ((fraction -f )* 4);
                    vhtemp = vhtemp - (fraction - ftemp)*g*4.0f;
                }
                ftemp = fraction;
                point.x = vp*fraction;
                return point;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setY(point.y);
                RelativeLayout.LayoutParams layoutParams =  new RelativeLayout.LayoutParams(200, 200);
                layoutParams.setMargins((int) point.x,0,0,0);
                view.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }
}
