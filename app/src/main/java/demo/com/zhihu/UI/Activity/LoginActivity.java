package demo.com.zhihu.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import demo.com.zhihu.R;


public class LoginActivity extends AppCompatActivity{

    private Button login_in;
    private Button login_up;
    private ImageButton QQ_login;
    private ImageButton Weibo_login;
    private ImageButton Wechat_login;
    private EditText userName;
    private EditText passWord;
    private Boolean Login=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Login == true) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);


        login_in = (Button) findViewById(R.id.Login_in);
        login_up = (Button) findViewById(R.id.Login_up);
        QQ_login = (ImageButton) findViewById(R.id.qqlogin);
        Weibo_login = (ImageButton) findViewById(R.id.Weibologin);
        Wechat_login = (ImageButton) findViewById(R.id.Wechatlogin);
        userName = (EditText) findViewById(R.id.Userame);
        passWord = (EditText) findViewById(R.id.Passord);


        click();
    }

    public void click(){

        login_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("LoginActivity", "login执行");
                if (userName.getText().length() == 0 && passWord.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "用户名及密码为空！！", Toast.LENGTH_SHORT).show();

                } else if (userName.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "用户名为空！！", Toast.LENGTH_SHORT).show();
                } else if (passWord.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "密码为空！！", Toast.LENGTH_SHORT).show();
                } else if (1 + 1 == 2) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Login = true;
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "密码或者用户名错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("LoginActivity","login执行");
                Toast.makeText(LoginActivity.this,"此处将在以后修改为注册",Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                //startActivity(intent);//此处代码需要完善，另UI也要修改，此处应跳转至注册页面

            }
        });
        QQ_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"此功能尚未开启！",Toast.LENGTH_SHORT).show();
            }
        });
        Weibo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"此功能尚未开启！",Toast.LENGTH_SHORT).show();
            }
        });
        Wechat_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "此功能尚未开启！", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
