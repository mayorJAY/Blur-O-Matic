package com.example.josycom.blur_o_matic;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;

import com.example.josycom.blur_o_matic.workers.BlurWorker;
import com.example.josycom.blur_o_matic.workers.CleanupWorker;
import com.example.josycom.blur_o_matic.workers.SaveImageToFileWorker;

import java.util.List;

public class BlurViewModel extends AndroidViewModel {

    private Uri mImageUri;
    private WorkManager mWorkManager;
    private LiveData<List<WorkInfo>> mSavedWorkInfo;
    private Uri mOutputUri;

    public BlurViewModel(@NonNull Application application) {
        super(application);
        mWorkManager = WorkManager.getInstance(application);
        mSavedWorkInfo = mWorkManager.getWorkInfosByTagLiveData(Constants.TAG_OUTPUT);
    }

    public LiveData<List<WorkInfo>> getOutputWorkInfo() {
        return mSavedWorkInfo;
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
        WorkContinuation continuation = mWorkManager.beginUniqueWork(Constants.IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker.class));
        // WorkRequest to blur the image
        for (int i = 0; i < blurLevel; i++) {
            OneTimeWorkRequest.Builder blurBuilder = new OneTimeWorkRequest.Builder(BlurWorker.class);
                    if (i == 0) {
                    blurBuilder.setInputData(createInputDataForUri());
                    }
            continuation = continuation.then(blurBuilder.build());
        }

        //Constraints constraints = new Constraints.Builder().setRequiresCharging(true).build();
        // WorkRequest to save the image to filesystem
        OneTimeWorkRequest save = new OneTimeWorkRequest.Builder(SaveImageToFileWorker.class)
                //.setConstraints(constraints)
                .addTag(Constants.TAG_OUTPUT)
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

    /**
     * Setters
     */
    void setOutputUri(String outputImageUri){
        mOutputUri = uriOrNull(outputImageUri);
    }

    /**
     * Getters
     */
    Uri getOutputUri(){
        return mOutputUri;
    }

    void cancelWork(){
        mWorkManager.cancelUniqueWork(Constants.IMAGE_MANIPULATION_WORK_NAME);
    }
}