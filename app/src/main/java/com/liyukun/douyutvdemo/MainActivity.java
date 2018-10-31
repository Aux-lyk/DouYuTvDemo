package com.liyukun.douyutvdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.liyukun.douyutvdemo.view.fishBar.FragmentBar;
import com.liyukun.douyutvdemo.recommend.FragmentCommend;
import com.liyukun.douyutvdemo.view.discover.FragmentDiscover;
import com.liyukun.douyutvdemo.entertainment.FragmentEntertainment;
import com.liyukun.douyutvdemo.focus.FragmentFocus;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost fragmentTabHost;
    private Fragment mFragmentTab;
    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {
            FragmentCommend.class,
            FragmentEntertainment.class,
            FragmentFocus.class,
            FragmentBar.class,
            FragmentDiscover.class,
    };
    // 图片
    private int[] imageButton = {
            R.drawable.tab_recommend_btn,
            R.drawable.tab_recommend_btn,
            R.drawable.tab_recommend_btn,
            R.drawable.tab_recommend_btn,
            R.drawable.tab_recommend_btn
    };
    // 标题
    private String[] texts = {"推荐","娱乐","关注","鱼吧","发现"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 创建和获取Fragment实例
//        mFragmentTab = new FragmentTab();
//        // 获取到FragmentManager对象
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        // 开启一个事务
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        // 添加Fragment
//        fragmentTransaction.add(R.id.main_content, mFragmentTab);
//        // 提交事务
//        fragmentTransaction.commit();

        // 实例化tabhost
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.main_content);
//        fragmentTabHost.getTabWidget().setDividerDrawable(null); // 去掉分割线

        for (int i = 0; i < texts.length; i++) {
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(texts[i]).setIndicator(getImageView(i));
            // 添加Fragment
            fragmentTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景(必须在addTab之后，由于需要子节点（底部菜单按钮）否则会出现空指针异常)
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.white);
        }
    }

    // 获得图片资源
    private View getImageView(int index) {
        //取得布局实例
        View view = getLayoutInflater().inflate(R.layout.tab_content, null);
//        View view = View.inflate(MainActivity.this, R.layout.tab_content, null);
        //取得布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_bar_image);
        TextView textView = (TextView) view.findViewById(R.id.tab_bar_text);
        //设置图标
        imageView.setImageResource(imageButton[index]);
        //设置标题
        textView.setText(texts[index]);
        return view;
    }
}
