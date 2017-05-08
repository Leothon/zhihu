package demo.com.zhihu.UI.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import demo.com.zhihu.R;
import demo.com.zhihu.UI.Fragment.descovery.DescoveryFragment;
import demo.com.zhihu.UI.Fragment.home.HomeFragment;
import demo.com.zhihu.UI.Fragment.message.MessageFragment;
import demo.com.zhihu.UI.Fragment.more.MoreFragment;
import demo.com.zhihu.UI.Fragment.notice.NoticeFragment;

/*
* 待解决问题：
* 怎么设置按键图片为灰色，选中之后为蓝色*/
public class MainActivity extends FragmentActivity {

    private static final String HOMEFRAGMENT="home";
    private static final String NOTICEFRAGMENT="notice";
    private static final String MESSAGEFRAGMENT="message";
    private static final String DESCOVERYFRAGMENT="descovery";
    private static final String MOREFRAGMENT="more";


    private static final int deepgray=R.color.deepgray;
    private static final int blue=R.color.bluewhite;

    private FragmentTransaction mTransaction;
    private LinearLayout home_button;
    private LinearLayout descovery_button;
    private LinearLayout notice_button;
    private LinearLayout message_button;
    private LinearLayout more_button;

    private ImageView homeimage;
    private ImageView descoveryimage;
    private ImageView noticeimage;
    private ImageView messageimage;
    private ImageView moreimage;

    private HomeFragment home_fragment;
    private DescoveryFragment descovery_fragment;
    private NoticeFragment notice_fragment;
    private MessageFragment message_fragment;
    private MoreFragment more_fragment;
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private FragmentManager manager_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        home_button = (LinearLayout) findViewById(R.id.home_button);
        descovery_button = (LinearLayout) findViewById(R.id.descovery_button);
        notice_button = (LinearLayout) findViewById(R.id.notice_button);
        message_button = (LinearLayout) findViewById(R.id.message_button);
        more_button = (LinearLayout) findViewById(R.id.more_button);

        homeimage=(ImageView)findViewById(R.id.home_image);
        descoveryimage=(ImageView)findViewById(R.id.descovery_image);
        noticeimage=(ImageView)findViewById(R.id.notice_image);
        messageimage=(ImageView)findViewById(R.id.message_image);
        moreimage=(ImageView)findViewById(R.id.more_image);
        switchFragment(HOMEFRAGMENT);
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);

        setUpListener();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

            if (networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"网络连接",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"网络断开",Toast.LENGTH_SHORT).show();
            }
        }
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
        FragmentManager manager_fragment=getSupportFragmentManager();
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
            homeimage.setColorFilter(Color.LTGRAY);

        }
        if (message_fragment != null) {
            transaction.hide(message_fragment);
            messageimage.setColorFilter(Color.LTGRAY);
        }

        if (descovery_fragment != null) {
            transaction.hide(descovery_fragment);
            descoveryimage.setColorFilter(Color.LTGRAY);
        }
        if (notice_fragment != null) {
            transaction.hide(notice_fragment);
            noticeimage.setColorFilter(Color.LTGRAY);
        }
        if (more_fragment!=null){
            transaction.hide(more_fragment);
            moreimage.setColorFilter(Color.LTGRAY);
        }

    }


    private void showHomeFragment() {
        homeimage.setColorFilter(Color.BLUE);
        if (home_fragment == null) {
            home_fragment = new HomeFragment();
            mTransaction.add(R.id.contentlayout, home_fragment, HOMEFRAGMENT);
        } else {
            mTransaction.show(home_fragment);

        }
    }
    private void showNoticeFragment() {
        noticeimage.setColorFilter(Color.BLUE);
        if (notice_fragment == null) {
            notice_fragment = new NoticeFragment();
            mTransaction.add(R.id.contentlayout, notice_fragment, NOTICEFRAGMENT);
        } else {
            mTransaction.show(notice_fragment);

        }}
    private void showMessageFragment() {
        messageimage.setColorFilter(Color.BLUE);
        if (message_fragment == null) {
            message_fragment = new MessageFragment();
            mTransaction.add(R.id.contentlayout, message_fragment, MESSAGEFRAGMENT);
        } else {
            mTransaction.show(message_fragment);

        }}
    private void showMoreFragment() {
        moreimage.setColorFilter(Color.BLUE);
        if (more_fragment == null) {
            more_fragment = new MoreFragment();
            mTransaction.add(R.id.contentlayout, more_fragment, MOREFRAGMENT);
        } else {
            mTransaction.show(more_fragment);

        }}
    private void showDescoveryFragment() {
        descoveryimage.setColorFilter(Color.BLUE);
        if (descovery_fragment == null) {
            descovery_fragment = new DescoveryFragment();
            mTransaction.add(R.id.contentlayout, descovery_fragment, DESCOVERYFRAGMENT);
        } else {
            mTransaction.show(descovery_fragment);

        }}

    /*private long exitTime;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

}















