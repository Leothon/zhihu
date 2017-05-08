package demo.com.zhihu.UI.Fragment.notice.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import demo.com.zhihu.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link followFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link followFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class followFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public followFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment followFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static followFragment newInstance(String param1, String param2) {
        followFragment fragment = new followFragment();
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
        View view=inflater.inflate(R.layout.follow_page, container, false);

        ListView follow_listview=(ListView)view.findViewById(R.id.follow_listview);
        View listViewHeader=getActivity().getLayoutInflater().inflate(R.layout.follow_head,follow_listview,false);
        followAdapter adapter=new followAdapter(getActivity());
        follow_listview.setAdapter(adapter);
        follow_listview.addHeaderView(listViewHeader);
        follow_listview.setDividerHeight(0);
        return view;
    }

    private final class ViewHolderfollow{
        public  ImageView markallreaded;
        public LinearLayout follow_username;
        public ImageView follow_usericon;
        public TextView follow_users;
        public Button follow_user_button;
    }

    public class followAdapter extends BaseAdapter{

        private LayoutInflater mInflater;

        public followAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
            //init();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 100;//修改
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

            ViewHolderfollow holder;
            if(convertView==null){
                holder=new ViewHolderfollow();
                convertView = mInflater.inflate(R.layout.follow_active, null);
                holder.markallreaded=(ImageView)convertView.findViewById(R.id.markallreaded);
                holder.follow_username=(LinearLayout)convertView.findViewById(R.id.follow_username);
                holder.follow_usericon=(ImageView)convertView.findViewById(R.id.follow_usericon);
                holder.follow_users=(TextView)convertView.findViewById(R.id.follow_users);
                holder.follow_user_button=(Button)convertView.findViewById(R.id.follow_user_button);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolderfollow)convertView.getTag();
            }

            return convertView;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /*public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
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
