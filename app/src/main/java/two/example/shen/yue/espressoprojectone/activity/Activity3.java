package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.adapter.SimpleExpandableListViewAdapter;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.vo.Classes;
import two.example.shen.yue.espressoprojectone.vo.College;

/**
 * Created by queen on 2018/3/29.
 */

public class Activity3 extends BaseActivity {

    private ExpandableListView listview;

    private List<College> colleges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);


        initData();

        // 查找控件
        listview = (ExpandableListView) findViewById(R.id.tree_view_simple);


        SimpleExpandableListViewAdapter adapter = new SimpleExpandableListViewAdapter(colleges, this);

        // 设置适配器
        listview.setAdapter(adapter);
/*
        // 初始化数据
        List<Classes> classesList = new ArrayList<>();

        for(int i = 1 ;i<3;i++) {
            Classes classes = new Classes();

            classes.name = "计算机"+i+"班";

            List<String> list = new ArrayList<>();

            list.add("mm");
            list.add("dd");
            classes.students = list;

            classesList.add(classes);
        }*/

        // 构造适配器
        // ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(classesList,this);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        colleges = new ArrayList<>();

        for (int j = 0; j < 5; j++) {

            College college = new College();

            college.name = "科技大学";

            List<Classes> classesList = new ArrayList<>();

            for (int i = 1; i < 3; i++) {
                Classes classes = new Classes();

                classes.name = "计算机" + i + "班";

                List<String> list = new ArrayList<>();

                list.add("mm");
                list.add("dd");
                classes.students = list;

                classesList.add(classes);
            }

            college.classList = classesList;


//            colleges = new ArrayList<>();
            colleges.add(college);
        }
    }
}
