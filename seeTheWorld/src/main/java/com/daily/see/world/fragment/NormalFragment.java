package com.daily.see.world.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daily.see.world.R;
import com.daily.see.world.bean.NewsBean;


/**
 * 左层叠卡片子页面
 */
public class NormalFragment extends Fragment {

    private NewsBean mNewsBean;

    private CardView mCardView;
    private ImageView mImageView;
    private TextView mTitle;
    private TextView mContent;
    private TextView mReadCount;


    public static NormalFragment getInstance(NewsBean news) {
        NormalFragment cardFragment = new NormalFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        cardFragment.setArguments(bundle);
        return cardFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, container, false);

        mCardView = view.findViewById(R.id.card_view);
        mImageView = view.findViewById(R.id.image);
        mTitle = view.findViewById(R.id.title);
        mContent = view.findViewById(R.id.content);
        mReadCount = view.findViewById(R.id.read_count);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了" + mTitle.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mNewsBean = (NewsBean) getArguments().getSerializable("news");
        }

        if(mNewsBean != null){ //显示数据
            mTitle.setText(mNewsBean.getTitle());
            mContent.setText(mNewsBean.getContent());
            mReadCount.setText(String.format("%d人已读", mNewsBean.getReadCount()));
        }

    }
}
