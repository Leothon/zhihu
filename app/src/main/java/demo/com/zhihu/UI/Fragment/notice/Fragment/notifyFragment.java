package demo.com.zhihu.UI.Fragment.notice.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.IMediaControllerCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import demo.com.zhihu.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link notifyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link notifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notifyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public notifyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static notifyFragment newInstance(String param1, String param2) {
        notifyFragment fragment = new notifyFragment();
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
        View view=inflater.inflate(R.layout.notice_page, container, false);
        ListView notify_listview=(ListView)view.findViewById(R.id.notify_listview);
        View listViewHeader=getActivity().getLayoutInflater().inflate(R.layout.notify_head,notify_listview,false);
        notifyAdapter adapter=new notifyAdapter(getActivity());
        notify_listview.setAdapter(adapter);
        notify_listview.addHeaderView(listViewHeader);
        //notify_listview.setDividerHeight(1);
        return view;
    }

    public final class ViewHoldernotify{
        private ImageView markallreaded2;
        private LinearLayout notify_username;
        private TextView notify_users;
        private TextView notify_talkpage;
        private ImageView notify_usericon;
    }

    public class notifyAdapter extends BaseAdapter{
        LayoutInflater inflater;

        public notifyAdapter(Context context){
            this.inflater=LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoldernotify holder;
            if (convertView==null){
                holder=new ViewHoldernotify();
                convertView=inflater.inflate(R.layout.notify_active,null);
                holder.markallreaded2=(ImageView)convertView.findViewById(R.id.markallreaded2);
                holder.notify_username=(LinearLayout)convertView.findViewById(R.id.notify_username);
                holder.notify_users=(TextView)convertView.findViewById(R.id.notify_users);
                holder.notify_talkpage=(TextView)convertView.findViewById(R.id.notify_talkpage);
                holder.notify_usericon=(ImageView)convertView.findViewById(R.id.notify_usericon);
                convertView.setTag(holder);
            }else{
                holder=(ViewHoldernotify)convertView.getTag();
            }
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 100;//修改
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
