package com.example.josycom.blur_o_matic;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;

import com.example.josycom.blur_o_matic.workers.BlurWorker;

public class BlurViewModel extends AndroidViewModel {

    private Uri mImageUri;
    private WorkManager mWorkManager;

    public BlurViewModel(@NonNull Application application) {
        super(application);
        mWorkManager = WorkManager.getInstance(application);
    }

    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    void applyBlur(int blurLevel) {
        mWorkManager.enqueue(OneTimeWorkRequest.from(BlurWorker.class));
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