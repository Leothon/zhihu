package demo.com.zhihu.UI.Fragment.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Created by 10483 on 2017/3/28.
 */
public class homeImageLoader {
    private ImageView mimageview;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mimageview.setImageBitmap((Bitmap)msg.obj);
        }
    };
    public void showImageByThread(ImageView imageview, final String url){

        mimageview=imageview;

        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap bitmap=getBitmapFromURL(url);
                Message message=Message.obtain();
                message.obj=bitmap;
                handler.sendMessage(message);
            }
        }.start();

    }
    public Bitmap getBitmapFromURL(final String urlString) {


        try{
            Bitmap homebitmap;
            InputStream is;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            homebitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            is.close();
            return homebitmap;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
         //finally {
            //try {
                //is.close();
            //} catch (IOException e) {
                //e.printStackTrace();
            //}
        //}

        return null;
    }

}
