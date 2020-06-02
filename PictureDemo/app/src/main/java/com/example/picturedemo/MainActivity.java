package com.example.picturedemo;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSingleSelected(View view) {
        PictureSelectedUtils.newInstance().onShowSingleSelect(this, new OnResultCallbackListener() {
            @Override
            public void onResult(List<LocalMedia> result) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void onMoreSelected(View view) {
        PictureSelectedUtils.newInstance().onShowMoreSelect(this, 10, false, null, new OnResultCallbackListener() {
            @Override
            public void onResult(List<LocalMedia> result) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void onSinglePreviewSelected(View view) {
        PictureSelectedUtils.newInstance().onShowSingleSelect(this, true, false, new OnResultCallbackListener() {
            @Override
            public void onResult(List<LocalMedia> result) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void onMorePreviewSelected(View view) {
        PictureSelectedUtils.newInstance().onShowMoreSelect(this, 10, true, null, new OnResultCallbackListener() {
            @Override
            public void onResult(List<LocalMedia> result) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void onSinglePreviewCropSelected(View view) {
        PictureSelectedUtils.newInstance().onShowSingleSelect(this, false, true, new OnResultCallbackListener() {
            @Override
            public void onResult(List<LocalMedia> result) {

            }

            @Override
            public void onCancel() {

            }
        });
    }
}
