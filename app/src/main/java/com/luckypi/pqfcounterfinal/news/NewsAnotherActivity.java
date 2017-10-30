package com.luckypi.pqfcounterfinal.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.luckypi.pqfcounterfinal.R;

public class NewsAnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_text);
        TextView textView =(TextView)findViewById(com.luckypi.pqfcounterfinal.R.id.textView);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
      // String message ="21";
        textView.setText(message);
    }
}
