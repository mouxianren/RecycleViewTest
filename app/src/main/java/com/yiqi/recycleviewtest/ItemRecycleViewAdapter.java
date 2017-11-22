package com.yiqi.recycleviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by moumou on 17/11/22.
 */

public class ItemRecycleViewAdapter extends RecyclerView.Adapter {
    List<Integer> mList;
    private Context context;
    private OnItemImageViewClickListener mOnItemImageViewClickListener;

    public ItemRecycleViewAdapter(List<Integer> list, Context context) {
        if (list != null) {
            this.mList = list;
        } else {
            mList = new ArrayList<Integer>();
        }
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycleview_imageview, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder) holder).item_recycleview_imageview.setImageResource(mList.get(position));
        ((ItemViewHolder) holder).item_recycleview_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemImageViewClickListener != null) {
                    mOnItemImageViewClickListener.OnItemImageViewClick(v, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        System.out.println("------------getItemCount==" + mList.size());
        return mList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_recycleview_imageview)
        ImageView item_recycleview_imageview;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemImageViewClickListener(OnItemImageViewClickListener onItemImageViewClickListener) {
        this.mOnItemImageViewClickListener = onItemImageViewClickListener;
    }

    public interface OnItemImageViewClickListener {
        void OnItemImageViewClick(View view, int position);
    }
}
