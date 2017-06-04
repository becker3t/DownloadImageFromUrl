package com.example.android.downloadfile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Android on 6/3/2017.
 */

public class MyResultReceiver extends ResultReceiver {

    private final MainActivity mainActivity;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */


    public MyResultReceiver(Handler handler, MainActivity activity) {
        super(handler);
        mainActivity = activity;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultData.getParcelable(MyIntentService.FILE_KEY) != null) {
//            mReceiver.onReceiveResult(resultCode, resultData);
            mainActivity.OnImageDownloaded((Bitmap) resultData.getParcelable(MyIntentService.FILE_KEY));
        }
        else {
            mainActivity.ErrorDownloading();
        }
    }
}
