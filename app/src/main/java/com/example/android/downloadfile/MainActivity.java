package com.example.android.downloadfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private EditText urlEt;
    private ImageView downloadedImgVw;

    public MyResultReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new MyResultReceiver(new Handler(), this);

        urlEt = (EditText) findViewById(R.id.photoUrlEt);
        downloadedImgVw = (ImageView) findViewById(R.id.downloadImgView);
        Button downloadBtn = (Button) findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(this);
    }

    public void OnImageDownloaded(Bitmap bmap) {
        downloadedImgVw.setImageBitmap(bmap);
        urlEt.setHint(getString(R.string.enter_url));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.downloadBtn:
                Intent i = new Intent(MainActivity.this, MyIntentService.class);

                Bundle b = new Bundle();
                b.putString(MyIntentService.EXTRA_FILE_PATH, urlEt.getText().toString());
                b.putParcelable(MyIntentService.RECEIVER_TAG, mReceiver);

                i.putExtras(b);
                startService(i);
                break;
        }
    }

    public void ErrorDownloading() {
        Log.d(TAG, "Error Downloading File");
        urlEt.setText("");
        urlEt.setHint("BAD URL");
    }
}
