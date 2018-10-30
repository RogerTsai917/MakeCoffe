package com.roger.makecoffee.writearticle;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.roger.makecoffee.MakeCoffee;
import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class WriteArticlePresenter implements WriteArticleContract.Presenter {
    private WriteArticleContract.View mView;
    private FirebaseFirestore mDb;

    public WriteArticlePresenter(WriteArticleContract.View view) {
        mView = view;
        mView.setPresenter(this);

        mDb = FirebaseFirestore.getInstance();
    }


    @Override
    public void postArticle(NewArticle article) {
        if (isArticleContentLegal(article)) {
            mView.showUploadingDialog();
            postArticleImageToFireStorage(article);
        }
    }

    @Override
    public void start() {

    }

    public boolean isArticleContentLegal(NewArticle article) {

        if (article.getTitle().equals("")) {
            mView.showToast(MakeCoffee.getAppContext().getResources()
                    .getString(R.string.title_cannot_be_empty));
            return false;
        }
        if (article.getContent().equals("")) {
            mView.showToast(MakeCoffee.getAppContext().getResources()
                    .getString(R.string.content_cannot_be_empty));
            return false;
        }
        if (article.getImageUrl().equals("")) {
            mView.showToast(MakeCoffee.getAppContext().getResources()
                    .getString(R.string.photo_cannot_be_empty));
            return false;
        }

        return true;
    }

    private void postArticleToFireStore(NewArticle article) {
        DocumentReference documentReference = mDb.collection(Constants.ARTICLES).document();
        String uid = documentReference.getId();
        article.setArticleUid(uid);

        mDb.collection(Constants.ARTICLES).document(uid).set(article);
        mDb.collection(Constants.USERS).document(UserManager.getInstance().getUserUid())
                .collection(Constants.POSTED_ARTICLES).document(uid).set(article);

        mView.hideUploadingDialog();
        mView.showToast(MakeCoffee.getAppContext().getResources()
                .getString(R.string.uploading_article_successful));
        mView.backPress();
    }

    private void postArticleImageToFireStorage(final NewArticle article) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;

                //upload article title photo
                final String hashKey = UUID.randomUUID().toString();
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
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    byte[] data = outputStream.toByteArray();

                    UploadTask uploadTask = reference.putBytes(data);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                mView.hideUploadingDialog();
                                throw Objects.requireNonNull(task.getException());
                            }
                            // Continue with the task to get the download URL
                            return reference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String downloadUrl = Objects.requireNonNull(task.getResult()).toString();
                            Log.d("WriteArticle", "get photo downloadUrl: " + downloadUrl);
                            article.setImageUrl(downloadUrl);
                            postArticleToFireStore(article);
                        }
                    });
                }
            }
        }).start();

    }

}
