package com.example.ideal.play_video;

import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

        VideoView videoView;
        String title = "my first video";
        String url = "https://firebasestorage.googleapis.com/v0/b/kuch-hee.appspot.com/o/220%20Android%20Navigation%20Drawer%20Tutorial%20Part%203%20-%20coursetro.com.mp4?alt=media&token=a4703569-e291-4da9-bede-931d5c5c9039";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//one

            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+"</font>"));
            // getSupportActionBar().setTitle("title");
            //set title according to video.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        videoView = (VideoView) findViewById(R.id.myVideo);
        Uri vidUri = Uri.parse(url);
        videoView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(videoView);
        videoView.setMediaController(vidControl);
        videoView.start();

    }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
