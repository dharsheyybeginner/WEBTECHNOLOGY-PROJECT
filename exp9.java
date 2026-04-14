package com.example.emailapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email, subject, message;
    Button btnAttach, btnSend;
    Uri fileUri = null;

    private static final int PICK_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        btnAttach = findViewById(R.id.btnAttach);
        btnSend = findViewById(R.id.btnSend);

        // Attach file
        btnAttach.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE);
        });

        // Send email
        btnSend.setOnClickListener(v -> sendEmail());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
        }
    }

    private void sendEmail() {
        String to = email.getText().toString();
        String sub = subject.getText().toString();
        String msg = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        intent.putExtra(Intent.EXTRA_SUBJECT, sub);
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        if (fileUri != null) {
            intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        }

        startActivity(Intent.createChooser(intent, "Send Email via"));
    }
}