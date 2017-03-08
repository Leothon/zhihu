package demo.com.zhihu.UI.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import demo.com.zhihu.R;
import demo.com.zhihu.UI.Fragment.descovery.DescoveryFragment;
import demo.com.zhihu.UI.Fragment.home.HomeFragment;
import demo.com.zhihu.UI.Fragment.message.MessageFragment;
import demo.com.zhihu.UI.Fragment.more.MoreFragment;
import demo.com.zhihu.UI.Fragment.notice.NoticeFragment;


public class MainActivity extends AppCompatActivity {

    private static final String HOMEFRAGMENT="home";
    private static final String NOTICEFRAGMENT="notice";
    private static final String MESSAGEFRAGMENT="message";
    private static final String DESCOVERYFRAGMENT="descovery";
    private static final String MOREFRAGMENT="more";

    private FragmentTransaction mTransaction;
    private Button home_button;
    private Button descovery_button;
    private Button notice_button;
    private Button message_button;
    private Button more_button;

    private HomeFragment home_fragment;
    private DescoveryFragment descovery_fragment;
    private NoticeFragment notice_fragment;
    private MessageFragment message_fragment;
    private MoreFragment more_fragment;

    private FragmentManager manager_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        home_button = (Button) findViewById(R.id.home_button);
        descovery_button = (Button) findViewById(R.id.descovery_button);
        notice_button = (Button) findViewById(R.id.notice_button);
        message_button = (Button) findViewById(R.id.message_button);
        more_button = (Button) findViewById(R.id.more_button);


        setUpListener();


    }
    /*此方法为底部五个按钮的响应动作*/
    public void setUpListener(){

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(HOMEFRAGMENT);

            }
        });
        descovery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switchFragment(DESCOVERYFRAGMENT);
            }
        });
        notice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(NOTICEFRAGMENT);

            }
        });
        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(MESSAGEFRAGMENT);

            }
        });
        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(MOREFRAGMENT);

            }
        });
    }
    public void switchFragment(String index){
        mTransaction = manager_fragment.beginTransaction();
        hideAllFragments(mTransaction);
        switch (index) {
            case HOMEFRAGMENT:
                showHomeFragment();
                break;
            case MESSAGEFRAGMENT:
                showMessageFragment();
                break;
            case DESCOVERYFRAGMENT:
                showDescoveryFragment();
                break;
            case NOTICEFRAGMENT:
                showNoticeFragment();
                break;
            case MOREFRAGMENT:
                showMoreFragment();
        }
        mTransaction.commit();
    }

    public void slip_top(){
        //该方法为点击后回到listview顶部
    }
    public void refresh(){
        //该方法为刷新listView的内容
    }
    private void hideAllFragments(FragmentTransaction transaction) {
        if (home_fragment != null) {
            transaction.hide(home_fragment);
        }
        if (manager_fragment != null) {
            transaction.hide(message_fragment);
        }

        if (descovery_fragment != null) {
            transaction.hide(descovery_fragment);
        }
        if (notice_fragment != null) {
            transaction.hide(notice_fragment);
        }
        if (more_fragment!=null){
            transaction.hide(more_fragment);
        }
        home_button.setSelected(false);
        message_button.setSelected(false);
        descovery_button.setSelected(false);
        more_button.setSelected(false);
        notice_button.setSelected(false);
    }


    private void showHomeFragment() {
        home_button.setSelected(true);
        if (home_fragment == null) {
            home_fragment = new HomeFragment();
            mTransaction.add(R.id.contentlayout, home_fragment, HOMEFRAGMENT);
        } else {
            mTransaction.show(home_fragment);

        }
    }
    private void showNoticeFragment() {
        notice_button.setSelected(true);
        if (notice_fragment == null) {
            notice_fragment = new NoticeFragment();
            mTransaction.add(R.id.contentlayout, notice_fragment, NOTICEFRAGMENT);
        } else {
            mTransaction.show(notice_fragment);

        }}
    private void showMessageFragment() {
        message_button.setSelected(true);
        if (message_fragment == null) {
            message_fragment = new MessageFragment();
            mTransaction.add(R.id.contentlayout, message_fragment, MESSAGEFRAGMENT);
        } else {
            mTransaction.show(message_fragment);

        }}
    private void showMoreFragment() {
        more_button.setSelected(true);
        if (more_fragment == null) {
            more_fragment = new MoreFragment();
            mTransaction.add(R.id.contentlayout, more_fragment, MOREFRAGMENT);
        } else {
            mTransaction.show(more_fragment);

        }}
    private void showDescoveryFragment() {
        descovery_button.setSelected(true);
        if (descovery_fragment == null) {
            descovery_fragment = new DescoveryFragment();
            mTransaction.add(R.id.contentlayout, descovery_fragment, DESCOVERYFRAGMENT);
        } else {
            mTransaction.show(descovery_fragment);

        }}

}















