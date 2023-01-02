package com.ctl.customcamerademo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;

public class PreviewImageVideoActivity extends AppCompatActivity {
    private String filePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_image_video);
        filePath = getIntent().getStringExtra("filepath");
        Log.e("TAG>>", "onCreate: "+filePath);
        if (filePath.contains("mp4")) {
            File file = new File(filePath);
            previewVideo(file);
        } else {
            ImageView ivImagePreview = findViewById(R.id.ivImagePreview);
            ivImagePreview.setVisibility(View.VISIBLE);
            File file = new File(filePath);
            Picasso.get()
                    .load(Uri.fromFile(file))
                    .placeholder(R.drawable.ic_baseline_info_24)
                    .error(R.drawable.ic_baseline_info_24)
                    .centerCrop()
                    .fit()
                    .into(ivImagePreview);

        }
    }

    private void previewVideo(File file) {
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(file.getAbsolutePath());
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.setVisibility(View.VISIBLE);
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
