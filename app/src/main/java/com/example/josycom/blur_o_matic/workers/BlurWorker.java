package com.example.josycom.blur_o_matic.workers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.josycom.blur_o_matic.R;


public class BlurWorker extends Worker {
    private static final String TAG = BlurWorker.class.getSimpleName();

    public BlurWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        try {
            Bitmap picture = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.test);

            //Blur the bitmap
            Bitmap output = WorkerUtils.blurBitmap(picture, applicationContext);

            //Write Bitmap to a temp file
            Uri outputUri = WorkerUtils.writeBitmapToFile(applicationContext, output);

            WorkerUtils.makeStatusNotification("Output is " + outputUri.toString(), applicationContext);
            return Result.success();
        } catch (Throwable throwable) {
            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}
