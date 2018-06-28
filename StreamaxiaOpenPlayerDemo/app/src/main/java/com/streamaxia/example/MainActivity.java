package com.streamaxia.example;


import com.google.android.exoplayer.AspectRatioFrameLayout;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.streamaxia.player.StreamaxiaPlayer;
import com.streamaxia.player.listener.StreamaxiaPlayerState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import streamaxia.com.streamaxiaOpenPlayerDemo.R;

public class MainActivity extends Activity implements StreamaxiaPlayerState {

    @BindView(R.id.surface_view)
    SurfaceView surfaceView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.debug_text_view)
    TextView debugText;
    @BindView(R.id.player_state_view)
    TextView stateText;
    @BindView(R.id.play)
    ImageView playBtn;
    @BindView(R.id.small)
    ImageView smallBtn;
    @BindView(R.id.mute)
    ImageView muteBtn;
    @BindView(R.id.url_edit_text)
    EditText mEditText;
    @BindView(R.id.video_frame)
    AspectRatioFrameLayout aspectRatioFrameLayout;

    private Uri uri;

    private StreamaxiaPlayer mStreamaxiaPlayer = new StreamaxiaPlayer();

    private int STREAM_TYPE = 0;

    Runnable hide = new Runnable() {
        @Override
        public void run() {
            playBtn.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getExtras();
        //setting the initial tags of the player
        playBtn.setTag("play");
        muteBtn.setTag("mute");
        smallBtn.setTag("small");
        progressBar.setVisibility(View.GONE);
        initRTMPExoPlayer();
        mEditText.setText(uri.toString());
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        uri = Uri.parse(extras.getString(ChooserActivity.URI));
        STREAM_TYPE = extras.getInt(ChooserActivity.TYPE);
    }

    @OnClick(R.id.play)
    public void setPlayBtn() {
        playBtn.postDelayed(hide, 1000);
        if (playBtn.getTag().equals("play")) {
            mStreamaxiaPlayer.play(uri, STREAM_TYPE);
            surfaceView.setBackgroundColor(Color.TRANSPARENT);
            progressBar.setVisibility(View.GONE);
            playBtn.setTag("pause");
            playBtn.setImageResource(R.drawable.pause);
            setAspectRatioFrameLayoutOnClick();
        } else {

            mStreamaxiaPlayer.pause();

            progressBar.setVisibility(View.VISIBLE);
            playBtn.setTag("play");
            playBtn.setImageResource(R.drawable.play);
        }
    }

    @OnClick(R.id.mute)
    public void setMute() {
        if (muteBtn.getTag().equals("mute")) {
            mStreamaxiaPlayer.setMute();
            muteBtn.setTag("muted");
            muteBtn.setImageResource(R.drawable.muted);
        } else {
            mStreamaxiaPlayer.setMute();
            muteBtn.setTag("mute");
            muteBtn.setImageResource(R.drawable.mute);
        }
    }

    @OnClick(R.id.small)
    public void setScreenSize() {
        if (smallBtn.getTag().equals("small")) {
            mStreamaxiaPlayer.setVideoSize(300, 300);
            smallBtn.setTag("big");
            smallBtn.setImageResource(R.drawable.big);
        } else {
            mStreamaxiaPlayer.setVideoSize(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            smallBtn.setTag("small");
            smallBtn.setImageResource(R.drawable.small);
        }
    }

    private void setAspectRatioFrameLayoutOnClick() {
        aspectRatioFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playBtn.setVisibility(View.VISIBLE);
                playBtn.postDelayed(hide, 1000);
            }
        });
    }

    private void initRTMPExoPlayer() {

        mStreamaxiaPlayer.initStreamaxiaPlayer(surfaceView, aspectRatioFrameLayout,
                stateText, this, this, uri);
    }

    @Override
    public void stateENDED() {
        progressBar.setVisibility(View.GONE);
        playBtn.setImageResource(R.drawable.play);

    }

    @Override
    public void stateBUFFERING() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateIDLE() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void statePREPARING() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateREADY() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void stateUNKNOWN() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStreamaxiaPlayer.stop();
    }
}