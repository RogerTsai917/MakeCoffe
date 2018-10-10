package com.roger.makecoffee.writearticle;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.roger.makecoffee.MakeCoffee;
import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.Article;
import com.roger.makecoffee.objects.define.ArticleStep;
import com.roger.makecoffee.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static android.support.constraint.Constraints.TAG;

public class WriteArticlePresenter implements WriteArticleContract.Presenter {
    private WriteArticleContract.View mView;
    private FirebaseFirestore mDb;

    public WriteArticlePresenter(WriteArticleContract.View view) {
        mView = view;
        mView.setPresenter(this);

        mDb = FirebaseFirestore.getInstance();
    }


    @Override
    public void postArticle(Article article) {
        if (isArticleContentLegal(article)) {
            mView.showUploadingDialog();
            postArticleImageToFireStorage(article);
        }
    }

    @Override
    public void start() {

    }

    private void postArticleToFireStore(Article article) {
        mDb.collection("articles").add(article);
        mView.hideUploadingDialog();
        mView.backPress();
    }

    private boolean isArticleContentLegal(Article article) {

        if (article.getTitle().equals("")) {
            mView.showToast(MakeCoffee.getAppContext().getResources()
                    .getString(R.string.title_cannot_be_empty));
            return false;
        }
        if (article.getDescription().equals("")) {
            mView.showToast(MakeCoffee.getAppContext().getResources()
                    .getString(R.string.description_cannot_be_empty));
            return false;
        }
        for (int i = 0; i < article.getArticleStepArrayListSize(); i++) {
            if (article.getArticleStepArrayList().get(i).getContent().equals("")) {
                mView.showToast(MakeCoffee.getAppContext().getResources()
                        .getString(R.string.step_content_cannot_be_empty));
                return false;
            }
        }
        return true;
    }

    private void postArticleImageToFireStorage(final Article article) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;

                //upload article steps photo
                for (int i = 0; i < article.getArticleStepArrayListSize(); i++) {
                    String hashKey = UUID.randomUUID().toString();
                    final StorageReference reference = FirebaseStorage.getInstance().getReference()
                            .child(Constants.ARTICLES_PHOTOS + hashKey);
                    final ArticleStep articleStep = article.getArticleStepArrayList().get(i);
                    if (!articleStep.getPhotoUrl().equals("")) {
                        bitmap = null;
                        try {
                            bitmap = Glide.with(mView.returnActivity())
                                    .asBitmap()
                                    .load(articleStep.getPhotoUrl())
                                    .submit()
                                    .get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        if (bitmap != null) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] data = baos.toByteArray();

                            UploadTask uploadTask = reference.putBytes(data);
                            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    // Continue with the task to get the download URL
                                    return reference.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String downloadUrl = task.getResult().toString();
                                    Log.d(TAG, "sendPhotoToFireBase downloadUrl: " + downloadUrl);
                                    articleStep.setPhotoUrl(downloadUrl);
                                }
                            });
                        }
                    }
                }

                //upload article title photo
                String hashKey = UUID.randomUUID().toString();
                final StorageReference reference = FirebaseStorage.getInstance().getReference()
                        .child(Constants.ARTICLES_PHOTOS + hashKey);
                try {
                    bitmap = Glide.with(mView.returnActivity())
                            .asBitmap()
                            .load(article.getImageUrl())
                            .submit()
                            .get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    UploadTask uploadTask = reference.putBytes(data);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            // Continue with the task to get the download URL
                            return reference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String downloadUrl = task.getResult().toString();
                            Log.d(TAG, "sendPhotoToFireBase downloadUrl: " + downloadUrl);
                            article.setImageUrl(downloadUrl);
                            postArticleToFireStore(article);
                        }
                    });
                }
            }
        }).start();

    }

}
