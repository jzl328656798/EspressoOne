package two.example.shen.yue.espressoprojectone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import two.example.shen.yue.espressoprojectone.study.StudyHomeActivity;
import two.example.shen.yue.espressoprojectone.test18.Test18Activity1;
import two.example.shen.yue.espressoprojectone.test6.Test6Activity2;
import two.example.shen.yue.espressoprojectone.test6.Test6Activity3;
import two.example.shen.yue.espressoprojectone.test7.Test7Activity2;
import two.example.shen.yue.espressoprojectone.test8.Test8Activity1;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyHomeActivity.class));
            }
        });


        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test6Activity2.class));
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test6Activity3.class));
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test7Activity2.class));
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test8Activity1.class));
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("queen", "onTouchEvent:" + event.getAction());
        return super.onTouchEvent(event);
    }
}
