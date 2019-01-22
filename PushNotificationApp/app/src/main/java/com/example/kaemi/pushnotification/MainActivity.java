package com.example.kaemi.pushnotification;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView nameTextView;
    TextView contentTextView;

    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.title_text_view);
        nameTextView = findViewById(R.id.name_text_view);
        contentTextView = findViewById(R.id.content_text_view);

        sendButton = findViewById(R.id.send_button);

    }

    public void sendButton(View view) {
        Toast.makeText(this, titleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
    }


    public void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            //To do//
                            return;
                        }

                        // Get the Instance ID token//
                        String token = task.getResult().getToken();
                        Log.d("Token: ", token);

                        // Save the token on device
                        SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.remove("Token");
                        editor.putString("Token", token);
                        editor.apply();

                    }
                });
    }
}
