package com.edmond.jyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.edmond.jyview.animation.FailJumpAnimation;

public class MainActivity extends AppCompatActivity {

    private TextView view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (TextView) findViewById(R.id.text);

        FailJumpAnimation failJumpAnimation = new FailJumpAnimation(view,1920,1080,10000);
        failJumpAnimation.start();
    }
}
