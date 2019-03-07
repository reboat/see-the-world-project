package com.daily.see.world.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.daily.see.world.bean.NewsBean;
import com.daily.see.world.fragment.NormalFragment;

import cn.daily.stack.card.adapter.BaseStackCardAdapter;


/**
 * 左层叠卡片适配器
 * <p>
 * Created by yyp on 2019/2/18
 */
public class NewsCardAdapter extends BaseStackCardAdapter<NewsBean> {

    public NewsCardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getFragment(int pos) {
        if(getData() == null){
            return NormalFragment.getInstance(null);
        }else{
            return NormalFragment.getInstance(getData().get(pos));
        }
    }

}
