package two.example.shen.yue.espressoprojectone.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Created by queen on 2018/7/25.
 * Author: Queen
 * Date: 2018/7/25
 * Time: 上午10:45
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity8Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_activity,container,false);

        return view;
    }
}
