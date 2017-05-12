package demo.com.zhihu.UI.Fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import demo.com.zhihu.R;
import demo.com.zhihu.UI.Activity.MainActivity;
import demo.com.zhihu.UI.Activity.searcherActivity;
import demo.com.zhihu.UI.Fragment.more.MoreFragment;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static String URL="http://www.imooc.com/api/teacher?type=4&num=30";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<newsBean> list;

    private Button research_button;
    private Button live_button;


    private int LastPosition=0;
    private boolean scrollFlag=false;






    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        ListView homelistview=(ListView)view.findViewById(R.id.home_listview);
        new homeAsyncTask().execute(URL);
        homeAdapter adapter=new homeAdapter(getActivity(),list);
        homelistview.setAdapter(adapter);
        homelistview.setDividerHeight(0);
        research_button=(Button)view.findViewById(R.id.research_button);
        live_button=(Button)view.findViewById(R.id.live_button);
        widgetOnclick();
        homelistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        scrollFlag=false;
                        LastPosition=absListView.getLastVisiblePosition();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        scrollFlag=true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int i1, int i2) {

                if (scrollFlag){
                    if (firstVisibleItem<LastPosition){
                        Toast.makeText(getActivity(),"上滑",Toast.LENGTH_SHORT).show();
                    }else if (firstVisibleItem>LastPosition){
                        Toast.makeText(getActivity(),"下滑",Toast.LENGTH_SHORT).show();
                    }else
                        return;
                }
            }
        });
        return view;
    }



    public void widgetOnclick(){
        research_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),searcherActivity.class);
                startActivity(intent);
            }
        });

        live_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到liveActivity
            }
        });
    }

    private List<newsBean> getJsonData(String url)  {



        new Thread(){
            @Override
            public void run() {
                super.run();
                String url="http://www.imooc.com/api/teacher?type=4&num=30";
                List<newsBean> homenewsBeanList=new ArrayList<>();
                try {
                    String homejsonString = readStream(new URL(url).openStream());
                    JSONObject homejsonObject;
                    newsBean homenewsBean;
                    try {
                        homejsonObject = new JSONObject(homejsonString);
                        JSONArray homejsonarray = homejsonObject.getJSONArray("data");
                        for (int i = 0; i < homejsonarray.length(); i++) {
                            homejsonObject = homejsonarray.getJSONObject(i);
                            homenewsBean = new newsBean();
                            homenewsBean.homeusericon = homejsonObject.getString("picSmall");
                            homenewsBean.hometitle = homejsonObject.getString("name");
                            homenewsBean.homecontext = homejsonObject.getString("description");
                            homenewsBeanList.add(homenewsBean);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message=Message.obtain();
                message.obj=homenewsBeanList;
                handler.sendMessage(message);

            }
        }.start();

        return null;


    }

    public List<newsBean> getdata(List<newsBean> list){
        List<newsBean> home2newsBeanList=new ArrayList<>();
        home2newsBeanList=list;
        return home2newsBeanList;
    }

    //把homenewsBeanList传入listview中，可以绑定数据获取没有参数的方法


    private Handler handler=new Handler(){
        //
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<newsBean> home1newsBeanList=new ArrayList<>();
            home1newsBeanList=(List<newsBean>) msg.obj;
            getdata(home1newsBeanList);
        }
    };


    private String readStream(InputStream is){
        InputStreamReader isr;
        String result="";
        String line="";
        try {
            isr=new InputStreamReader(is,"UTF-8");
            BufferedReader br=new BufferedReader(isr);
            while ((br.readLine()!=null)){
                result+=line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;

    }
    class homeAsyncTask extends AsyncTask<String,Void,List<newsBean>>{


        @Override
        protected List<newsBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }
    }


/*1、需要修改adapter，否则显示不正常
 2017.4.1update:这不是愚人节的玩笑，这个问题仍然没有感觉
* 2、需要找到添加各种数据的方式，否则没办法显示众多的数据
 * 2017.4.1update：找到了添加数据的方式，简单的描述一下吧：首先得需要该网站的API，然后找到api的链接
 * 解析url地址，得到JSON数据，建立一个对象，储存各项数据，然后得到所有的数据，此时要建立一个list来储存
 * 得到的各种数据，比如图像，题目和内容，返回的数据传入AsyncTask异步加载的方法中，注意自定义的Adapter
 * 中要将数据和context一块传入，在设置adapter时，第二个参数为数据。TIP：但目前，仍然没有找到方法添加进去，
 * 因为安卓某个版本之后，与网络连接的部分代码，必须放在子线程中，即不能堵塞主线程的运行，但是子线程不能返回数据，
 * 只能通过handle方法来传递，然而handle方法返回的数据不明白怎么传递到listview中
*
* 3、设置各种控件的点击结果，在widget方法中统统点击this，然后写单独的监听点击的内部类去实现
* 4、根据控件点击进入的页面写各种activity或者说是fragment*/
    private class homeAdapter extends BaseAdapter{
        private LayoutInflater mInflater;

        private List<newsBean> homelist;
        public homeAdapter(Context context,List<newsBean> data){
            mInflater = LayoutInflater.from(context);
            homelist=data;

        }
        /*public void bindData(List<newsBean> data){
            homelist=data;

        }*/


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            //return homelist.size();
            return 50;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            //return null;
            return homelist.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderhome holder = null;
            //Log.d("HomeFragment","如果看到这行字，说明getView被执行");
            if (convertView == null) {
                holder=new ViewHolderhome();
                convertView = mInflater.inflate(R.layout.mainpagepart, null);
                holder.userlittleicon=(ImageView)convertView.findViewById(R.id.userlittleicon);
                holder.topicoruser=(TextView)convertView.findViewById(R.id.topicoruser);
                holder.topicname=(TextView)convertView.findViewById(R.id.topic_name);
                holder.deletepart=(Button)convertView.findViewById(R.id.delete_part);
                holder.parttitlename=(TextView)convertView.findViewById(R.id.part_titlename);
                holder.partmain=(TextView)convertView.findViewById(R.id.part_main);
                holder.agreequestions=(LinearLayout) convertView.findViewById(R.id.agreequestions);
                holder.agreenum=(TextView)convertView.findViewById(R.id.agreenum);
                holder.talkquestions=(LinearLayout)convertView.findViewById(R.id.talkquestions);
                holder.talknum=(TextView)convertView.findViewById(R.id.talknum);
                holder.followquestions=(TextView)convertView.findViewById(R.id.followqustions);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolderhome)convertView.getTag();
            }
            Log.d("HomeFragment","如果看到这行字，说明getView被执行");
            holder.userlittleicon.setImageResource(R.drawable.usericon);
            //new homeImageLoader().showImageByThread(holder.userlittleicon,homelist.get(position).homeusericon);
            //holder.parttitlename.setText(homelist.get(position).hometitle);
            //holder.partmain.setText(homelist.get(position).homecontext);

            return convertView;
    }

    public final class ViewHolderhome{
        public ImageView userlittleicon;
        public TextView topicoruser;
        public TextView topicname;
        public Button deletepart;
        public TextView parttitlename;
        public TextView partmain;
        public LinearLayout agreequestions;
        public TextView agreenum;
        public LinearLayout talkquestions;
        public TextView talknum;
        public TextView followquestions;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    }
}

