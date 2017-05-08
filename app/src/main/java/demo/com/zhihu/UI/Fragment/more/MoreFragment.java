package demo.com.zhihu.UI.Fragment.more;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import demo.com.zhihu.R;


public class MoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Map<String, Object>> myData;




    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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
        View view=inflater.inflate(R.layout.fragment_more, container, false);

        ListView morelistView=(ListView)view.findViewById(R.id.more_listview);

        MoreAdapter adapter = new MoreAdapter(getActivity());
        morelistView.setAdapter(adapter);
        return view;
        /*
        * 还有众多的组件没有添加，需要在写每一个Activity时加上*/
    }


    public final class ViewHoldermore{
        public LinearLayout user_info;
        public LinearLayout main_info;
        public LinearLayout other_info;
        //public Button editButton;
        //public Button deleteButton;
    }

    private class MoreAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MoreAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
            //init();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoldermore holder;
            if (convertView == null) {
                holder=new ViewHoldermore();
                convertView = mInflater.inflate(R.layout.more_active, null);
                holder.user_info = (LinearLayout) convertView.findViewById(R.id.user_info);
                holder.main_info = (LinearLayout) convertView.findViewById(R.id.main_info);
                holder.other_info = (LinearLayout) convertView.findViewById(R.id.other_info);
                convertView.setTag(holder);
            }else {
                holder = (ViewHoldermore)convertView.getTag();
            }
            return convertView;
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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

}
