package com.yiqi.recycleviewtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by moumou on 17/11/15.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    ArrayList<String> list;
    private OnItemClickListener mOnItemClickListener;
    private Context context;
    private List<Integer> imageViewList;
    public static final int ITEM_TYPE_THEME = 0;
    public static final int ITEM_TYPE_RECYCLEVIEW = 1;
    private RecyclerView.RecycledViewPool viewPool;


    public MyRecyclerViewAdapter(ArrayList<String> list, Context context, List<Integer> imageViewList) {
        this.list = list;
        this.context = context;
        this.imageViewList = imageViewList;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_THEME:
                return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));

            case ITEM_TYPE_RECYCLEVIEW:
                RecycleHolder holder = new RecycleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false));
                holder.item_recycleview.setRecycledViewPool(viewPool);

                return holder;

            default:
                Log.d("error", "viewholder is null");
                return null;

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        System.out.println("onBindViewHolder");
        if (holder instanceof MyHolder) {
            System.out.println("onBindViewHolder---MyHolder");
            ((MyHolder) holder).mTextView.setText(list.get(position));
        } else if (holder instanceof RecycleHolder) {
            System.out.println("onBindViewHolder----RecycleHolder");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((RecycleHolder) holder).item_recycleview.setLayoutManager(linearLayoutManager);
            ItemRecycleViewAdapter adapter=new ItemRecycleViewAdapter(imageViewList,context);
            ((RecycleHolder) holder).item_recycleview.setAdapter(adapter);
            adapter.setOnItemImageViewClickListener(new ItemRecycleViewAdapter.OnItemImageViewClickListener() {
                @Override
                public void OnItemImageViewClick(View view, int position) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemImageViewClick(view,position);
                    }

                }
            });
        }


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println("positon===" + position);
        return position % 5 == 0 ? ITEM_TYPE_RECYCLEVIEW : ITEM_TYPE_THEME;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        // public TextView mTextView;
        @BindView(R.id.tv_item)
        TextView mTextView;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // mTextView = itemView.findViewById(R.id.tv_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    class RecycleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_recycleview)
        RecyclerView item_recycleview;

        public RecycleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);

        void OnItemImageViewClick(View view,int position);
    }
}
