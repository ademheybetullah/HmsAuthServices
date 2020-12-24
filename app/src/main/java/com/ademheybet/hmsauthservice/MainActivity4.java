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
import com.huawei.agconnect.auth.EmailAuthProvider;
import com.huawei.agconnect.auth.VerifyCodeResult;
import com.huawei.agconnect.auth.VerifyCodeSettings;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;

import java.util.Locale;

public class MainActivity4 extends AppCompatActivity {

    Button sign_btn,code_btn;
    EditText emailEdt,passwordEdt,verifityEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        code_btn=findViewById(R.id.codeBtn);
        sign_btn=findViewById(R.id.signupBtn);
        emailEdt=findViewById(R.id.emailEditText);
        passwordEdt=findViewById(R.id.passwordEditText);
        verifityEdt=findViewById(R.id.verifityEditText);
        Toast.makeText(MainActivity4.this, "Enter your email and get a password reset code", Toast.LENGTH_LONG).show();
    }
    public void getcode(View view){
        VerifyCodeSettings settings = new VerifyCodeSettings.Builder()
                .action(VerifyCodeSettings.ACTION_RESET_PASSWORD)
                .sendInterval(30)
                .locale(Locale.getDefault())
                .build();
        Task<VerifyCodeResult> task = EmailAuthProvider.requestVerifyCode(emailEdt.getText().toString(), settings);
        task.addOnSuccessListener(TaskExecutors.uiThread(), new OnSuccessListener<VerifyCodeResult>() {
            @Override
            public void onSuccess(VerifyCodeResult verifyCodeResult) {
                // The verification code application is successful.
                Toast.makeText(MainActivity4.this, "We've sent a verification code to this email address. You can sign up with that code", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(TaskExecutors.uiThread(), new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(MainActivity4.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
        });
    }
    public void singin(View view){
        AGConnectAuth.getInstance().resetPassword(emailEdt.getText().toString(), passwordEdt.getText().toString(), verifityEdt.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // onSuccess
                        Toast.makeText(MainActivity4.this, "login with your new password", Toast.LENGTH_LONG).show();
                        Intent sign =new Intent(MainActivity4.this,MainActivity.class);
                        startActivity(sign);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // onFail
                        Toast.makeText(MainActivity4.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }
}