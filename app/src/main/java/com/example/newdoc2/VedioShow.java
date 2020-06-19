package com.example.newdoc2;

import android.content.Intent;
import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.newdoc2.VidioPage.path;
import static com.example.newdoc2.FirstAct.highFirst;
import static com.example.newdoc2.FirstAct.widthFirst;
public class VedioShow extends AppCompatActivity {
  public ImageButton full_secreen,closeVidio;
  VideoView videoView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vedio_show);
    getWindow().setFormat(PixelFormat.UNKNOWN);
     videoView =(VideoView)findViewById(R.id.vdVw);
    full_secreen=(ImageButton)findViewById(R.id.full_screen) ;
    closeVidio=(ImageButton)findViewById(R.id.closVedio_screen) ;
    full_secreen.getLayoutParams().height=highFirst/14;
    full_secreen.getLayoutParams().width=widthFirst/14;
    closeVidio.getLayoutParams().height=highFirst/14;
    closeVidio.getLayoutParams().width=widthFirst/14;
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
public int clicablFull=-1;

  public void fullscreen(View view) {
    clicablFull++;
    if(clicablFull%2==0)
    {
      full_secreen.setImageResource(R.drawable.ic_fullscreen_exit);
      RelativeLayout.LayoutParams layoutParams =
              new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
     layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

      videoView.setLayoutParams(layoutParams);

    }
    else
    {
      full_secreen.setImageResource(R.drawable.ic_fullscreen);
      RelativeLayout.LayoutParams layoutParams =
              new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
     layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
      videoView.setLayoutParams(layoutParams);
    }
  }

  public void closVedio_screen(View view) {
    videoView.stopPlayback();
    Intent intent = new Intent(getApplicationContext(), VidioPage.class);
    startActivity(intent);
  }
}
