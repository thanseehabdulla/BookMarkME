    package com.app.ats.com.bookmarkme;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by real on 4/30/2016.
 */
public class Webv extends Activity {
    private class webclient extends WebViewClient {

        private ProgressBar progressBar;

        public webclient(ProgressBar progressBar) {
            this.progressBar=progressBar;
            progressBar.setVisibility(View.VISIBLE);
        }



        public boolean ShouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }


    }
    private SwipeRefreshLayout swipeLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.web_main);
        //Button b = (Button) findViewById(R.id.sear);
        //final EditText e = (EditText) findViewById(R.id.url);


        Bundle b = getIntent().getExtras();
        final String urlss=b.getString("url");


        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {
            final WebView w = (WebView) findViewById(R.id.webView);
            // w.loadUrl("http://www.google.com");

            w.setWebViewClient(new WebViewClient());
            w.loadUrl(urlss);
        //w.getSettings().getAllowContentAccess();
            w.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            w.getSettings().setAllowContentAccess(true);
        //w.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            w.getSettings().setAppCacheEnabled(true);
            w.getSettings().setJavaScriptEnabled(true);
            w.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            w.getSettings().setBuiltInZoomControls(true);
w.getSettings().setDatabaseEnabled(true);
            w.getSettings().setDisplayZoomControls(true);
            w.getSettings().setDomStorageEnabled(true);
            w.getSettings().setLoadsImagesAutomatically(true);
            w.getSettings().setSaveFormData(true);




        }


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //your method to refresh content
                if (!DetectConnection.checkInternetConnection(Webv.this)) {
                    Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                    if (swipeLayout.isRefreshing()) {
                        swipeLayout.setRefreshing(false);
                    }
                }
                else {
                        final WebView w = (WebView) findViewById(R.id.webView);
                        // w.loadUrl("http://www.google.com");

                        w.setWebViewClient(new WebViewClient());
                    w.loadUrl(urlss);
                    //w.getSettings().getAllowContentAccess();
                    w.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                    w.getSettings().setAllowContentAccess(true);
                   // w.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
                    w.getSettings().setAppCacheEnabled(true);
                    w.getSettings().setJavaScriptEnabled(true);
                    w.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                    w.getSettings().setBuiltInZoomControls(true);
                    w.getSettings().setDatabaseEnabled(true);
                    w.getSettings().setDisplayZoomControls(true);
                    w.getSettings().setDomStorageEnabled(true);
                    w.getSettings().setLoadsImagesAutomatically(true);
                    w.getSettings().setSaveFormData(true);
                        if (swipeLayout.isRefreshing()) {
                            swipeLayout.setRefreshing(false);
                        }
                    }
                }
        });

//don't forget to cancel refresh when work is done



    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            {
                //your Action code
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
