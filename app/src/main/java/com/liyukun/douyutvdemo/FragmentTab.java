package com.liyukun.douyutvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTab extends Fragment {
    private TextView mShowText;
    private View mViewContent; // 缓存视图内容

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mViewContent == null) {
            mViewContent = inflater.inflate(R.layout.fragment_recommend, container, false);
        }

        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }

//        ButterKnife.bind(this, mViewContent);
        return mViewContent;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 显示Fragment的Tag信息
        mShowText = (TextView) mViewContent.findViewById(R.id.tab_show_text);
        mShowText.setText(String.valueOf("Page: " + getTag()));
    }

//    @Override public void onDestroyView() {
//        super.onDestroyView();
////        ButterKnife.unbind(this);
//    }
}
