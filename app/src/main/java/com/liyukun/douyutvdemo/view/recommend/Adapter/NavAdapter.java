package com.liyukun.douyutvdemo.view.recommend.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liyukun.douyutvdemo.R;
import com.liyukun.douyutvdemo.view.recommend.RecommendNav;

import java.util.List;

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {
    private Context context; //上下文
    private List<RecommendNav> navList; //存放数据

    public NavAdapter(Context context, List<RecommendNav> list){ //构造函数
        this.context = context;
        this.navList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View navView;
        TextView navName;
        public ViewHolder(View view){
            super(view);
            navView = view;
            navName = (TextView) view.findViewById(R.id.recommend_nav_name);
        }
    }

    // 在这里对获取对象进行操作
    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
    //position是点击位置
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_nav_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.navView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取点击的位置
                int position = holder.getAdapterPosition();
                //根据位置实例化子项
                RecommendNav recommendNav = navList.get(position);
                //对子项操作
                Toast.makeText(view.getContext(),"你点击了"+recommendNav.getName(),Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecommendNav recommendNav = navList.get(position);
        //为子项添加资源
        holder.navName.setText(recommendNav.getName());
    }

    @Override
    public int getItemCount() {
        return navList.size();
    }

    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, String name){
        if(position < navList.size() && position >= 0){
            RecommendNav recommendNav = new RecommendNav(name);
            navList.add(position, recommendNav);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }

    //去除指定位置子项
    public boolean removeItem(int position){
        if(position < navList.size() && position >= 0){
            navList.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    //清空数据
    public void clearAll(){
        navList.clear();
        notifyDataSetChanged();
    }
}
