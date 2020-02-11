package com.example.josycom.blur_o_matic;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;

import com.example.josycom.blur_o_matic.workers.BlurWorker;
import com.example.josycom.blur_o_matic.workers.CleanupWorker;
import com.example.josycom.blur_o_matic.workers.SaveImageToFileWorker;

public class BlurViewModel extends AndroidViewModel {

    private Uri mImageUri;
    private WorkManager mWorkManager;

    public BlurViewModel(@NonNull Application application) {
        super(application);
        mWorkManager = WorkManager.getInstance(application);
    }

    /**
     * Creates the input data bundle which includes the Uri to operate on
     * @return Data which contains the Image Uri as a String
     */
    private Data createInputDataForUri(){
        Data.Builder builder = new Data.Builder();
        if (mImageUri != null){
            builder.putString(Constants.KEY_IMAGE_URI, mImageUri.toString());
        }
        return builder.build();
    }

    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    void applyBlur(int blurLevel) {
        // WorkRequest to cleanup temporary files
        WorkContinuation continuation = mWorkManager.beginWith(OneTimeWorkRequest.from(CleanupWorker.class));
        // WorkRequest to blur the image
        for (int i = 0; i < blurLevel; i++) {
            OneTimeWorkRequest.Builder blurBuilder = new OneTimeWorkRequest.Builder(BlurWorker.class);
                    if (i == 0) {
                    blurBuilder.setInputData(createInputDataForUri());
                    }
            continuation = continuation.then(blurBuilder.build());
        }
        // WorkRequest to save the image to filesystem
        OneTimeWorkRequest save = new OneTimeWorkRequest.Builder(SaveImageToFileWorker.class)
                .build();
        continuation = continuation.then(save);

        //Enqueue the work
        continuation.enqueue();
    }

    private Uri uriOrNull(String uriString) {
        if (!TextUtils.isEmpty(uriString)) {
            return Uri.parse(uriString);
        }
        return null;
    }

    /**
     * Setters
     */
    void setImageUri(String uri) {
        mImageUri = uriOrNull(uri);
    }

    /**
     * Getters
     */
    Uri getImageUri() {
        return mImageUri;
    }

}