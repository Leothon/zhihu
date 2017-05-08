package demo.com.zhihu.UI.Fragment.notice.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import demo.com.zhihu.R;
import demo.com.zhihu.UI.Fragment.message.MessageFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link thanksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link thanksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class thanksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public thanksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment thanksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static thanksFragment newInstance(String param1, String param2) {
        thanksFragment fragment = new thanksFragment();
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
        View view = inflater.inflate(R.layout.thanks_page, container, false);
        ListView thank_listview = (ListView) view.findViewById(R.id.thank_listview);
        View listViewHeader=getActivity().getLayoutInflater().inflate(R.layout.thank_head,thank_listview, false);
        thankAdapter adapter = new thankAdapter(getActivity());
        thank_listview.setAdapter(adapter);
        thank_listview.addHeaderView(listViewHeader);
        //thank_listview.setDividerHeight(1);
        return view;
    }

    public final class ViewHolderthanks {
        private ImageView markallreaded1;
        private ImageView thank_usericon;
        private LinearLayout thank_username;
        private TextView thank_users;
        private TextView thank_talkpage;
    }

    public class thankAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public thankAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 100;//修改
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolderthanks holder;
            if (convertView == null) {
                holder = new ViewHolderthanks();
                convertView = inflater.inflate(R.layout.thank_active, null);
                holder.markallreaded1 = (ImageView) convertView.findViewById(R.id.markallreaded1);
                holder.thank_usericon = (ImageView) convertView.findViewById(R.id.thank_usericon);
                holder.thank_username = (LinearLayout) convertView.findViewById(R.id.thank_username);
                holder.thank_users = (TextView) convertView.findViewById(R.id.thank_users);
                holder.thank_talkpage = (TextView) convertView.findViewById(R.id.thank_talkpage);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolderthanks) convertView.getTag();
            }
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
