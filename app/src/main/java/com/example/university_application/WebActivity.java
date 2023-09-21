package com.example.university_application;

import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        webView = findViewById(R.id.webViewforNewPage);

        // Retrieve the URL from the intent's extras
        String url = getIntent().getStringExtra("URL");


        // Load the URL in the WebView
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

}
