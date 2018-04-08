package two.example.shen.yue.espressoprojectone.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by queen on 2018/3/29.
 */

public class BaseActivity extends AppCompatActivity {




    public void startActivity(Class mClass){
        Intent intent = new Intent(this,mClass);
        startActivity(intent);
    }
}
