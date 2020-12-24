
package com.ademheybet.hmsauthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.EmailAuthProvider;
import com.huawei.agconnect.auth.EmailUser;
import com.huawei.agconnect.auth.SignInResult;
import com.huawei.agconnect.auth.VerifyCodeResult;
import com.huawei.agconnect.auth.VerifyCodeSettings;
import com.huawei.agconnect.core.service.auth.OnTokenListener;
import com.huawei.agconnect.core.service.auth.TokenSnapshot;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    Button sign_btn,code_btn;
    EditText emailEdt,passwordEdt,verifityEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        code_btn=findViewById(R.id.codeBtn);
        sign_btn=findViewById(R.id.signupBtn);
        emailEdt=findViewById(R.id.emailEditText);
        passwordEdt=findViewById(R.id.passwordEditText);
        verifityEdt=findViewById(R.id.verifityEditText);
       /* AGConnectAuth.getInstance().addTokenListener(new OnTokenListener() {
            public void onChanged(TokenSnapshot tokenSnapshot) {
                TokenSnapshot.State state = tokenSnapshot.getState();
                if (state == TokenSnapshot.State.TOKEN_UPDATED) {
                    String token = tokenSnapshot.getToken();
                }
            }
        });*/
        Toast.makeText(MainActivity2.this, "First you need to enter your email address and get a verification code", Toast.LENGTH_LONG).show();

    }
    public void getcode(View view){

        Intent intent=new Intent(MainActivity2.this,MainActivity2.class);
        startActivity(intent);
        VerifyCodeSettings settings = new VerifyCodeSettings.Builder()
                .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
                .sendInterval(30)
                .locale(Locale.getDefault())
                .build();
        Task<VerifyCodeResult> task = EmailAuthProvider.requestVerifyCode(emailEdt.getText().toString(), settings);
        task.addOnSuccessListener(TaskExecutors.uiThread(), new OnSuccessListener<VerifyCodeResult>() {
            @Override
            public void onSuccess(VerifyCodeResult verifyCodeResult) {
                // The verification code application is successful.
                Toast.makeText(MainActivity2.this, "We've sent a verification code to this email address. You can sign up with that code", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(TaskExecutors.uiThread(), new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity2.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
        });
    }
    public void signUp(View view){

        EmailUser emailUser = new EmailUser.Builder()
                .setEmail(emailEdt.getText().toString())
                .setVerifyCode(verifityEdt.getText().toString())
                .setPassword(passwordEdt.getText().toString())
                .build();
        AGConnectAuth.getInstance().createUser(emailUser)
                .addOnSuccessListener(new OnSuccessListener<SignInResult>() {
                    @Override
                    public void onSuccess(SignInResult signInResult) {
                        // After an account is created, the user has signed in by default.
                        Toast.makeText(MainActivity2.this, "Successfully", Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(MainActivity2.this,MainActivity3.class);
                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MainActivity2.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                    }

                });


    }
}