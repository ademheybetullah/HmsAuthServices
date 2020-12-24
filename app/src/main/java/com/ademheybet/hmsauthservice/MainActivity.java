package com.ademheybet.hmsauthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.AGConnectAuthCredential;
import com.huawei.agconnect.auth.AGConnectUser;
import com.huawei.agconnect.auth.EmailAuthProvider;
import com.huawei.agconnect.auth.SignInResult;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    Button sign_in,sign_up;
    EditText emailEdt,passwordEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_in=findViewById(R.id.codeBtn);
        sign_up=findViewById(R.id.signupBtn);
        emailEdt=findViewById(R.id.emailEditText);
        passwordEdt=findViewById(R.id.passwordEditText);
        AGConnectUser user = AGConnectAuth.getInstance().getCurrentUser();
        if (user!=null){
            Intent intent=new Intent(MainActivity.this,MainActivity3.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
    }

    public void signUp(View view){

        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);

    }
    public void signIn(View view){
        AGConnectAuthCredential credential = EmailAuthProvider.credentialWithPassword(emailEdt.getText().toString(), passwordEdt.getText().toString());
        AGConnectAuth.getInstance().signIn(credential)
                .addOnSuccessListener(new OnSuccessListener<SignInResult>() {
                    @Override
                    public void onSuccess(SignInResult signInResult) {
                        // Obtain sign-in information.
                        Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(MainActivity.this,MainActivity3.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void forgotPw(View view){

        Intent intent =new Intent(MainActivity.this,MainActivity4.class);
        startActivity(intent);
    }


}