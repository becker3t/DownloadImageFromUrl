package com.example.android.downloadfile;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.InputStream;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    public static final String TAG = "MyIntentService";

    // TODO: Rename parameters
    public static final String FILE_KEY = "file";
    public static final String EXTRA_FILE_PATH = "file_path";
    public static final String RECEIVER_TAG = "receiver";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String path = intent.getExtras().getString(EXTRA_FILE_PATH);
            Log.d("hi", path);
            ResultReceiver mReceiver = intent.getExtras().getParcelable(RECEIVER_TAG);
            Bundle b = new Bundle();
            b.putParcelable(FILE_KEY, DownloadFile(path));
            mReceiver.send(0, b);
        }
    }

    private Bitmap DownloadFile(String fileUrl) {
        Bitmap bitmap = null;

        try {
            // Download Image from URL
            InputStream input = new java.net.URL(fileUrl).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
