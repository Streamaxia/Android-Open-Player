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
    public static final String RTMP_URL = "rtmp://184.72.239.149/vod/mp4:bigbuckbunny_1500.mp4";
    public static final String HLS_URL = "https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8";
    public static final String DASH_URL = "http://www.youtube.com/api/manifest/dash/id/3aa39fa2cc27967f/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams="
            + "ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=A2716F75795F5D2AF0E88962FFCD10DB79384F29.84308FF04844498" +
            "CE6FBCE4731507882B8307798&key=ik0";

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
