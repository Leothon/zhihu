package demo.com.zhihu.UI.Fragment.descovery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.zip.Inflater;

import demo.com.zhihu.R;


public class DescoveryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageSwitcher imageSwitcher;
    private int[] imgRes=new int[]{
            R.drawable.pic_1,
            R.drawable.pic_2,
            R.drawable.pic_3,
            R.drawable.pic_4
    };
    private int foot=0;//表示当前图片的标记

    private String[] itemdata={"adba","dadhba","fndkabfkjasb","fbaibfia","basjdfvbakfba","fbsbafhka"};

    public DescoveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescoveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescoveryFragment newInstance(String param1, String param2) {
        DescoveryFragment fragment = new DescoveryFragment();
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
        View view=inflater.inflate(R.layout.fragment_descovery, container, false);

        /*imageSwitcher=(ImageSwitcher)view.findViewById(R.id.image_switcher);
        imageSwitcher.setFactory(new ViewFactoryImpl());
        imageSwitcher.setImageResource(imgRes[this.foot++]);
        //imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        //设置图片的切换效果，此处并不需要*/


        ListView discoverylistview=(ListView)view.findViewById(R.id.discovery_listview);
        View listViewHeader=getActivity().getLayoutInflater().inflate(R.layout.discovery_item1,discoverylistview,false);
        DiscoveryAdapter adapter=new DiscoveryAdapter(getActivity(),itemdata);
        discoverylistview.setAdapter(adapter);
        discoverylistview.addHeaderView(listViewHeader);
        return view;
    }

    public class DiscoveryAdapter extends BaseAdapter{

        private LayoutInflater inflater;
        private static final int ITEM2=1;
        private static final int ITEM3=2;
        private String[] itemdata;
        public DiscoveryAdapter(Context context,String[] item2data){
            this.inflater=LayoutInflater.from(context);
            this.itemdata=item2data;
        }
        @Override
        public int getCount() {
            return 10;
        }

        /*public int getItemViewType(int position) {
           return itemdata.length(position)?ITEM2:ITEM3;
        }*/

        @Override
        public int getViewTypeCount() {
            return itemdata.length;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            Item2Holder viewholder2;
            Item3Holder viewholder3;
            switch (getItemViewType(position)){
                case ITEM2:
                    if(convertview==null){
                        viewholder2=new Item2Holder();
                        convertview=inflater.inflate(R.layout.discovery_item2,null);
                        viewholder2.watch_all=(TextView)convertview.findViewById(R.id.watch_all);
                        convertview.setTag(viewholder2);
                    }else{
                        viewholder2=(Item2Holder)convertview.getTag();
                    }
                    break;
                case ITEM3:
                    if (convertview==null){
                        viewholder3=new Item3Holder();
                        convertview=inflater.inflate(R.layout.discovery_item3,null);
                        viewholder3.main_part=(TextView)convertview.findViewById(R.id.dis_part_main);
                        convertview.setTag(viewholder3);
                    }else{
                        viewholder3=(Item3Holder)convertview.getTag();
                    }
                    break;
            }

            return convertview;



        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }



    public class Item2Holder{
        TextView watch_all;
    }
    public class Item3Holder{
        TextView main_part;
    }
    private class ViewFactoryImpl implements ViewSwitcher.ViewFactory{
        @Override
        public View makeView() {
            ImageView img=new ImageView(getActivity());
            img.setBackgroundColor(getResources().getColor(R.color.white));
            img.setScaleType(ImageView.ScaleType.CENTER);
            img.setLayoutParams(new ImageSwitcher.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));//定义组件的显示
            return img;
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
