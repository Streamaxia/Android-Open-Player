package com.streamaxia.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.streamaxia.player.StreamaxiaPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import streamaxia.com.streamaxiaOpenPlayerDemo.R;

/**
 * @author James
 */

public class ChooserActivity extends Activity {

    public static final String URI = "uri";
    public static final String TYPE = "type";

    // Modify this to your desired streams
    public static final String RTMP_URL = "rtmp://vd2.wmspanel.com/video_demo/stream";
    public static final String HLS_URL = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8";
    public static final String DASH_URL = "https://dash.akamaized.net/akamai/bbb_30fps/bbb_30fps.mpd";

    @BindView(R.id.rtmp_demo)
    RelativeLayout rtmpDemoBtn;
    @BindView(R.id.hls_demo)
    RelativeLayout hlsDemoBtn;
    @BindView(R.id.dash_demo)
    RelativeLayout dashDemoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rtmp_demo)
    public void setRtmpDemoBtn() {
        sendIntet(StreamaxiaPlayer.TYPE_RTMP);
    }

    @OnClick(R.id.hls_demo)
    public void setHlsDemoBtn() {
        sendIntet(StreamaxiaPlayer.TYPE_HLS);
    }

    @OnClick(R.id.dash_demo)
    public void setDashDemoBtn() {
        sendIntet(StreamaxiaPlayer.TYPE_DASH);
    }

    private void sendIntet(int type) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (type) {
            case StreamaxiaPlayer.TYPE_RTMP:
                intent.putExtra(URI, ChooserActivity.RTMP_URL);
                intent.putExtra(TYPE, StreamaxiaPlayer.TYPE_RTMP);
                break;
            case StreamaxiaPlayer.TYPE_HLS:
                intent.putExtra(URI, ChooserActivity.HLS_URL);
                intent.putExtra(TYPE, StreamaxiaPlayer.TYPE_HLS);
                break;
            case StreamaxiaPlayer.TYPE_DASH:
                intent.putExtra(URI, ChooserActivity.DASH_URL);
                intent.putExtra(TYPE, StreamaxiaPlayer.TYPE_DASH);
                break;
        }
        startActivity(intent);
    }
}
