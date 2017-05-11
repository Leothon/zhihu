package demo.com.zhihu.UI.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.com.zhihu.R;

public class searcherActivity extends AppCompatActivity implements View.OnClickListener{


    private Button search_back;
    private ViewPager viewPager;
    private List<View> viewList;
    private TextView rearch_all;
    private TextView rearch_user;
    private TextView rearch_talk;
    private TextView rearch_zhuanlan;
    private TextView rearch_live;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcher);
        viewPager = (ViewPager) findViewById(R.id.rearch_pager);
        LayoutInflater inflater = getLayoutInflater();
        viewList = new ArrayList();
        View view1 = inflater.inflate(R.layout.all_pager, null);
        View view2 = inflater.inflate(R.layout.user_pager, null);
        View view3 = inflater.inflate(R.layout.talk_pager, null);
        View view4 = inflater.inflate(R.layout.zhuanlan_pager, null);
        View view5 = inflater.inflate(R.layout.live_pager, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);

        rearch_all=(TextView)findViewById(R.id.rearch_all);
        rearch_user=(TextView)findViewById(R.id.rearch_user);
        rearch_talk=(TextView)findViewById(R.id.rearch_talk);
        rearch_zhuanlan=(TextView)findViewById(R.id.rearch_zhuanlan);
        rearch_live=(TextView)findViewById(R.id.rearch_live);
        rearch_all.setOnClickListener(this);
        rearch_user.setOnClickListener(this);
        rearch_talk.setOnClickListener(this);
        rearch_zhuanlan.setOnClickListener(this);
        rearch_live.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                setTextColorBlueWhite();
                int currentItem=viewPager.getCurrentItem();
                switch (currentItem){
                    case 0:
                        rearch_all.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 1:
                        rearch_user.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 2:
                        rearch_talk.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 3:
                        rearch_zhuanlan.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 4:
                        rearch_live.setTextColor(getResources().getColor(R.color.white));
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        //viewPager.setOnPageChangeListener(new SearchOnPageChangeListener());
        //viewPager.setCurrentItem(0);
        search_back = (Button) findViewById(R.id.search_back);
        weightListener();

        rearch_all.setTextColor(getResources().getColor(R.color.white));
    }

    /*public class SearchOnClickListener implements View.OnClickListener {

        private int index = 0;

        public SearchOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }*/




    public void onClick(View v){
        setTextColorBlueWhite();
        switch (v.getId()){
            case R.id.rearch_all:
                viewPager.setCurrentItem(0);
                rearch_all.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.rearch_user:
                viewPager.setCurrentItem(1);
                rearch_user.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.rearch_talk:
                viewPager.setCurrentItem(2);
                rearch_talk.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.rearch_zhuanlan:
                viewPager.setCurrentItem(3);
                rearch_zhuanlan.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.rearch_live:
                viewPager.setCurrentItem(4);
                rearch_live.setTextColor(getResources().getColor(R.color.white));
                break;

            default:
                break;
        }
    }

    private void setTextColorBlueWhite(){
        rearch_all.setTextColor(getResources().getColor(R.color.bluewhite));
        rearch_user.setTextColor(getResources().getColor(R.color.bluewhite));
        rearch_talk.setTextColor(getResources().getColor(R.color.bluewhite));
        rearch_zhuanlan.setTextColor(getResources().getColor(R.color.bluewhite));
        rearch_live.setTextColor(getResources().getColor(R.color.bluewhite));
    }

    public void weightListener() {
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }




}
