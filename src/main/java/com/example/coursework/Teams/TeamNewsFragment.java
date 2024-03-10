// This fragment displays the latest news for a given team
// its parent activity is SingleTeamActivity

package com.example.coursework.Teams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.example.coursework.R;


public class TeamNewsFragment extends Fragment {

    private String url;


    // allows the containing activity to set the url
    public void setURL(String url) {
        this.url = url;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.team_news_fragment, container, false);

        WebView webView = rootView.findViewById(R.id.webview); // retrieves the browser viewer
//        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if required, it is not currently
        webView.loadUrl(url); // Loads the webpage URL

        return rootView;
    }
}