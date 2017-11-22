package com.yiqi.recycleviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_recyclerview)
    RecyclerView mMainRecyclerview;
    ArrayList<String> arrayList;
    ArrayList<Integer> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       initView();
        initData();
    }
    private void initView(){
      RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());

       // RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
        mMainRecyclerview.setLayoutManager(layoutManager);


    }

    private void initData() {
        arrayList=new ArrayList<String>();
        for(char i='1';i<'Z';i++){
            arrayList.add(i+"");
        }
        imageViewList=new ArrayList<Integer>();
        for(int j=0;j<10;j++){
            imageViewList.add(R.mipmap.ic_launcher);
        }
        System.out.println("imageViewList====="+imageViewList.size());
        MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(arrayList,MainActivity.this,imageViewList);
        mMainRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                System.out.println("positio==="+position);
            }

            @Override
            public void OnItemImageViewClick(View view, int position) {
                System.out.println("item==="+position);
            }
        });
    }
}
