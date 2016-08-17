package com.trimh.nuannuan.ui.pineed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.trimh.nuannuan.R;
import com.trimh.nuannuan.ui.pineed.adapter.BaseHeaderAdapter;
import com.trimh.nuannuan.ui.pineed.entitiy.PinnedHeaderEntity;
import com.trimh.nuannuan.utils.pinned.SmallPinnedHeaderItemDecoration;
import com.trimh.nuannuan.utils.pinned.callback.OnHeaderClickAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private BaseHeaderAdapter<PinnedHeaderEntity<Integer>> mAdapter;

    private int[] mDogs = {R.mipmap.dog0, R.mipmap.dog1, R.mipmap.dog2, R.mipmap.dog3, R.mipmap.dog4, R.mipmap.dog5, R.mipmap.dog6, R.mipmap.dog7, R.mipmap.dog8};
    private int[] mCats = {R.mipmap.cat0, R.mipmap.cat1, R.mipmap.cat2, R.mipmap.cat3, R.mipmap.cat4, R.mipmap.cat5, R.mipmap.cat6, R.mipmap.cat7, R.mipmap.cat8};
    private int[] mRabbits = {R.mipmap.rabbit0, R.mipmap.rabbit1, R.mipmap.rabbit2, R.mipmap.rabbit3, R.mipmap.rabbit4, R.mipmap.rabbit5, R.mipmap.rabbit6, R.mipmap.rabbit7, R.mipmap.rabbit8};
    private int[] mPandas = {R.mipmap.panda0, R.mipmap.panda1, R.mipmap.panda2, R.mipmap.panda3, R.mipmap.panda4, R.mipmap.panda5, R.mipmap.panda6, R.mipmap.panda7, R.mipmap.panda8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("SmallPinnedHeader");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        final List<PinnedHeaderEntity<Integer>> data = new ArrayList<>();
        int i = 0;
        for (int dog : mDogs) {
            data.add(new PinnedHeaderEntity<>(dog, i == 0 ? BaseHeaderAdapter.TYPE_HEADER : BaseHeaderAdapter.TYPE_DATA, "Dog"));
            i++;
        }
        i = 0;
        for (int cat : mCats) {
            data.add(new PinnedHeaderEntity<>(cat, i == 0 ? BaseHeaderAdapter.TYPE_HEADER : BaseHeaderAdapter.TYPE_DATA, "Cat"));
            i++;
        }
        i = 0;
        for (int rabbit : mRabbits) {
            data.add(new PinnedHeaderEntity<>(rabbit, i == 0 ? BaseHeaderAdapter.TYPE_HEADER : BaseHeaderAdapter.TYPE_DATA, "Rabbit"));
            i++;
        }
        i = 0;
        for (int panda : mPandas) {
            data.add(new PinnedHeaderEntity<>(panda, i == 0 ? BaseHeaderAdapter.TYPE_HEADER : BaseHeaderAdapter.TYPE_DATA, "Panda"));
            i++;
        }

        mAdapter = new BaseHeaderAdapter<PinnedHeaderEntity<Integer>>(data) {

            @Override
            protected void addItemTypes() {
                addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_data_with_small_pinned_header);
                addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_data);
            }

            @Override
            protected void convert(BaseViewHolder holder, PinnedHeaderEntity<Integer> item) {
                switch (holder.getItemViewType()) {
                    case BaseHeaderAdapter.TYPE_HEADER:
                        Glide.with(SecondActivity.this).load(item.getData()).into((ImageView) holder.getView(R.id.iv_small_pinned_header));
                        Glide.with(SecondActivity.this).load(item.getData()).into((ImageView) holder.getView(R.id.iv_animal));

                        holder.setOnClickListener(R.id.iv_small_pinned_header, new OnItemChildClickListener());
                        holder.setOnClickListener(R.id.iv_animal, new OnItemChildClickListener());

                        break;
                    case BaseHeaderAdapter.TYPE_DATA:
                        holder.setText(R.id.tv_pos, holder.getLayoutPosition() + "");
                        Glide.with(SecondActivity.this).load(item.getData()).into((ImageView) holder.getView(R.id.iv_animal));

                        holder.setOnClickListener(R.id.iv_animal, new OnItemChildClickListener());

                        break;
                }
            }

            @Override
            public boolean isPinnedHeaderType(int viewType) {
                return viewType == BaseHeaderAdapter.TYPE_HEADER;
            }

            @Override
            public PinnedHeaderEntity<Integer> getPinnedHeaderInfo(int position) {
                return (PinnedHeaderEntity<Integer>) getData().get(position);
            }
        };

        mAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PinnedHeaderEntity<Integer> entity = (PinnedHeaderEntity<Integer>) mAdapter.getItem(i);
                switch (view.getId()) {
                    case R.id.iv_small_pinned_header:
                        Toast.makeText(SecondActivity.this, "click tag: " + entity.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv_animal:
                        Toast.makeText(SecondActivity.this, entity.getPinnedHeaderName() + ", position " + i + ", id " + entity.getData(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        OnHeaderClickAdapter<PinnedHeaderEntity<Integer>> headerClickAdapter = new OnHeaderClickAdapter<PinnedHeaderEntity<Integer>>() {

            @Override
            public void onHeaderClick(int id, int position, PinnedHeaderEntity<Integer> data) {
                if (id == R.id.iv_small_pinned_header) {
                    Toast.makeText(SecondActivity.this, "click tag: " + data.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        mRecyclerView.addItemDecoration(
                new SmallPinnedHeaderItemDecoration.Builder<PinnedHeaderEntity<Integer>>(R.id.iv_small_pinned_header)
                        .enableDivider(true)
                        .setDividerId(R.drawable.divider)
                        .disableHeaderClick(true)
                        .setClickIds(R.id.iv_small_pinned_header)
                        .setHeaderClickListener(headerClickAdapter)
                        .create());
        mRecyclerView.setAdapter(mAdapter);

    }

}
