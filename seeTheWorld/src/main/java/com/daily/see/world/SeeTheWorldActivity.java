package com.daily.see.world;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daily.see.world.adapter.NewsCardAdapter;
import com.daily.see.world.bean.NewsBean;
import com.zjrb.core.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import cn.daily.stack.card.StackCardViewPager;
import cn.daily.stack.card.config.PageTransformerConfig;
import cn.daily.stack.card.transformer.StackCardPageTransformer;
import cn.daily.stack.card.view.ticker.TickerUtils;
import cn.daily.stack.card.view.ticker.TickerView;

public class SeeTheWorldActivity extends AppCompatActivity {

    private StackCardViewPager mStackCardViewPager;
    private NewsCardAdapter mNewsCardAdapter;

    private ImageView mBackImageView;
    private TickerView mPositionView;
    private TextView mTotalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_the_world);

        mStackCardViewPager = findViewById(R.id.stack_card_vp);
        mBackImageView = findViewById(R.id.image_back);
        mPositionView = findViewById(R.id.image_show_position);
        mTotalView=findViewById(R.id.show_total_num);
        mPositionView.setCharacterLists(TickerUtils.provideNumberList());

        configLeftStackCardViewPager();
        loadData();

        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 配置左层叠 StackCardViewPager
     */
    private void configLeftStackCardViewPager() {
        mStackCardViewPager.setPageTransformer(true, StackCardPageTransformer.getBuild()
                .setViewType(PageTransformerConfig.LEFT) //层叠方向
                .setTranslationOffset(UIUtils.dip2px(45f)) //左右位置偏移量
                .setScaleOffset(UIUtils.dip2px(50f)) //缩放偏移量
                .setAlphaOffset(0.5f) //卡片透明度偏移量
                .setRotationOffset(10) //卡片滑动时的最大旋转角度
                .setMaxShowPage(3) //最大显示的页数
                .create(mStackCardViewPager));

        //创建适配器
        mNewsCardAdapter = new NewsCardAdapter(getSupportFragmentManager());
        mStackCardViewPager.setAdapter(mNewsCardAdapter);

        mStackCardViewPager.addOnPageChangeListener(new StackCardViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //显示浏览到第几张
                mPositionView.setText(String.valueOf(mNewsCardAdapter.toRealShowPosition(position)));
                mTotalView.setText("/"+mNewsCardAdapter.getData().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 加载左层叠卡片数据
     */
    private void loadData(){
        List<NewsBean> list = new ArrayList<>();
        for(int i = 1; i<=10; i++){
            list.add(new NewsBean("", "你好！外星人" + i, getResources().getString(R.string.content),
                    "在浙里", 100 + i));
        }

        mNewsCardAdapter.setList(list, true);
        mStackCardViewPager.setCurrentItem(mNewsCardAdapter.getMiddlePosition(), false); //刚开始显示最后一个，也就是第一条数据
    }
}
