package com.example.sabari_ex11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewClientCompat;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

class LocalContentWebViewClient extends WebViewClientCompat {
    private final WebViewAssetLoader mAssetLoader;

    LocalContentWebViewClient(WebViewAssetLoader assetLoader) {
        mAssetLoader = assetLoader;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return mAssetLoader.shouldInterceptRequest(request.getUrl());
    }
}

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    WebViewAssetLoader assetLoader;
    private EditText url;
    private Button getButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        url = findViewById(R.id.url);
        getButton = findViewById(R.id.load);

        webView.getSettings().setJavaScriptEnabled(true);

        // Load static HTML content
//        String staticHtml = "<html>\n" +
//                "<body>\n" +
//                "\n" +
//                "<h1 style=\"color:red; font-family:sans-serif\">This is a HTML Site</h1 >\n " +
//                "\n" +
//                "<p style=\"color:blue;\">A blue paragraph.</p>\n" +
//                "\n" +
//                "<h2>An Unordered HTML List</h2>\n" +
//                "\n" +
//                "<ul>\n" +
//                " <li>Coffee</li>\n" +
//                " <li>Tea</li>\n" +
//                " <li>Milk</li>\n" +
//                "</ul>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>";
//        webView.loadData(staticHtml, "text/html", "UTF-8");

        assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                .addPathHandler("/res/", new WebViewAssetLoader.AssetsPathHandler(this))
                .build();
        webView.setWebViewClient(new LocalContentWebViewClient(assetLoader));

//        webView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
        webView.loadUrl("file:///android_asset/index.html");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://" + url.getText().toString());
            }
        });
    }
}