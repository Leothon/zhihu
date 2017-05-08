package demo.com.zhihu.UI.Fragment.notice;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import demo.com.zhihu.Adapter.MyFragmentPagerAdapter;
import demo.com.zhihu.R;
import demo.com.zhihu.UI.Fragment.notice.Fragment.followFragment;
import demo.com.zhihu.UI.Fragment.notice.Fragment.notifyFragment;
import demo.com.zhihu.UI.Fragment.notice.Fragment.thanksFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoticeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private static final int white=R.color.white;
    //private static final int bluewhite=R.color.bluewhite;

    //Resources resources;
    private ViewPager viewPager;
    private ArrayList<Fragment> list;
    private int currIndex = 0;
    //private int bottomLineWidth;
    //private int offset = 0;
    //private int position_one;
    //public final static int num = 2 ;

    private Button notify_button;
    private Button thanks_button;
    private Button follow_button;





    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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
        View view=inflater.inflate(R.layout.fragment_notice, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        notifyFragment notice_page = new notifyFragment();
        thanksFragment thanks_page = new thanksFragment();
        followFragment follow_page = new followFragment();


        list = new ArrayList<Fragment>();// 将要分页显示的View装入数组中
        list.add(notice_page);
        list.add(thanks_page);
        list.add(follow_page);




        //tabline = (ImageView) view.findViewById(R.id.tabline);
        notify_button = (Button) view.findViewById(R.id.notify_button);
        thanks_button = (Button) view.findViewById(R.id.thank_button);
        follow_button = (Button) view.findViewById(R.id.follow_button);


        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),list));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(0);
        /*FragmentPagerAdapter adapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return list.get(arg0);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(list.get(position));


                return list.get(position);
            }
        };*/

        notify_button.setOnClickListener(new MyOnClickListener(0));
        thanks_button.setOnClickListener(new MyOnClickListener(1));
        follow_button.setOnClickListener(new MyOnClickListener(2));
        /*notify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify_button.setTextColor(getResources().getColor(white));
                thanks_button.setTextColor(getResources().getColor(bluewhite));
                follow_button.setTextColor(getResources().getColor(bluewhite));
            }
        });

        thanks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanks_button.setTextColor(getResources().getColor(white));
                notify_button.setTextColor(getResources().getColor(bluewhite));
                follow_button.setTextColor(getResources().getColor(bluewhite));
            }
        });

        follow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow_button.setTextColor(getResources().getColor(white));
                notify_button.setTextColor(getResources().getColor(bluewhite));
                thanks_button.setTextColor(getResources().getColor(bluewhite));

            }
        });*/



        //viewPager.setAdapter(adapter);
        //TranslateAnimation animation = new TranslateAnimation(position_one, offset, 0, 0);
        notify_button.setTextColor(getResources().getColor(R.color.white));
        //animation.setFillAfter(true);
        //animation.setDuration(300);
        //ivBottomLine.startAnimation(animation);

        return view;
    }

    public class MyOnClickListener implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v){
                viewPager.setCurrentItem(index);
        }
    };


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            //Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex ==1||currIndex==2) {
                        //animation = new TranslateAnimation(position_one, offset, 0, 0);
                        notify_button.setTextColor(getResources().getColor(R.color.white));
                    }
                    thanks_button.setTextColor(getResources().getColor(R.color.bluewhite));
                    follow_button.setTextColor(getResources().getColor(R.color.bluewhite));
                    break;
                case 1:
                    if (currIndex ==0||currIndex==2 ) {
                        //animation = new TranslateAnimation(offset, position_one, 0, 0);
                        thanks_button.setTextColor(getResources().getColor(R.color.white));
                    }
                    notify_button.setTextColor(getResources().getColor(R.color.bluewhite));
                    follow_button.setTextColor(getResources().getColor(R.color.bluewhite));
                    break;
                case 2:
                    if (currIndex==0||currIndex==1){
                        //animation = new TranslateAnimation(offset, position_one, 0, 0);
                        follow_button.setTextColor(getResources().getColor(R.color.white));
                    }
                    notify_button.setTextColor(getResources().getColor(R.color.bluewhite));
                    thanks_button.setTextColor(getResources().getColor(R.color.bluewhite));

            }
            currIndex = arg0;
            //animation.setFillAfter(true);
           // animation.setDuration(300);
            //ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //@Override
    /*public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
