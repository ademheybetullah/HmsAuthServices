package com.ademheybet.hmsauthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.AGConnectUser;

public class MainActivity3 extends AppCompatActivity {

    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        username=findViewById(R.id.textView2);
        AGConnectUser user = AGConnectAuth.getInstance().getCurrentUser();
        System.out.println(user.getDisplayName());
        System.out.println(user.getEmail());
        System.out.println(user.getEmailVerified());
        String getUn=user.getEmail();
        username.setText(getUn.substring(0,getUn.indexOf("@")));




    }
    public void logout(View view){
        AGConnectUser user = AGConnectAuth.getInstance().getCurrentUser();
        AGConnectAuth.getInstance().signOut();
        Intent intent =new Intent(MainActivity3.this,MainActivity.class);
        startActivity(intent);
        username.setText(user.getDisplayName());
    }


}