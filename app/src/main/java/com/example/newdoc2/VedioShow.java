package com.example.newdoc2;

import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.newdoc2.VidioPage.path;

public class VedioShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_show);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        final VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        //Set MediaController  to enable play, pause, forward, etc options.
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        //mediaController.setMediaPlayer(videoView);
       // String uriPath="android.resource://com.example.newdoc2/" + R.raw.bac2;
        //Location of Media File
       // Uri uri = Uri.parse(uriPath);
        //Starting VideView By Setting MediaController and URI
        videoView.setMediaController(mediaController);
        //videoView.setVideoURI(uri);
        videoView.setVideoPath(path);

        videoView.requestFocus();
       videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                mp.setLooping(true);
            }
        });


    //  videoView.requestFocus();
     // videoView.start();



    }
}
