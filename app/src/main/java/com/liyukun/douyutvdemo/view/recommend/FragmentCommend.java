package com.liyukun.douyutvdemo.view.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liyukun.douyutvdemo.R;
import com.liyukun.douyutvdemo.view.recommend.Adapter.NavAdapter;


import java.util.ArrayList;
import java.util.List;

public class FragmentCommend extends Fragment {
    private View myView;
    private List<RecommendNav> navList = new ArrayList<>();
    private int[] cardImageList = {
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active,
            R.drawable.bg_recommend_active
    };
    private String[] cardTextList = {"推荐1","推荐2","推荐3","推荐4","推荐5","推荐6","推荐7","推荐8"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_recommend,container,false);
        initData();
        initRecyclerView();
        initCardView();
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initData(){
        if(navList.size() > 0) return;
        //初始化显示数据
        for(int i = 0; i<1; i++){
            RecommendNav all = new RecommendNav("全都");
            navList.add(all);
            RecommendNav recommend = new RecommendNav("推荐");
            navList.add(recommend);
        }
    }

    private void initRecyclerView(){
        //获取 RecyclerView
        RecyclerView recyclerView = (RecyclerView) myView.findViewById(R.id.recommend_recycler_view);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); //表示布局纵向排列
        //瀑布流布局
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //创建 adapter
        NavAdapter adapter = new NavAdapter(getActivity(),navList);
        //给 RecyclerView 添加适配器 adapter
        recyclerView.setAdapter(adapter);
    }

    private void initCardView(){
        ViewGroup cardLayout = (ViewGroup) myView.findViewById(R.id.card_layout);
        cardLayout.removeAllViews();
        for(int i = 0; i < cardImageList.length; i++){
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.recommend_card_item, cardLayout, false);
            ImageView cardImage = (ImageView) view.findViewById(R.id.recommend_card_image);
            TextView cardText = (TextView) view.findViewById(R.id.recommend_card_text);
            cardImage.setImageResource(cardImageList[i]);
            cardText.setText(cardTextList[i]);
            cardLayout.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView cardText = (TextView) view.findViewById(R.id.recommend_card_text);
                    String text = cardText.getText().toString();
                    Toast.makeText(view.getContext(),"你点击了"+text,Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
