package com.xiaoxin.fashionthing.utils.http;

import android.content.Context;
import android.widget.Toast;
import io.reactivex.subscribers.ResourceSubscriber;

public class ProgressSubscriber<T> extends ResourceSubscriber<T> implements ProgressCancelListener {

  private ProgressDialogHandler mProgressDialogHandler;
  private SubscriberOnNextListener mSubscriberOnNextListener;
  private Context context;

  public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
    this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    this.context = context;
    mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
  }

  private void showProgressDialog() {
    if (mProgressDialogHandler != null) {
      mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)
          .sendToTarget();
    }
  }

  private void dismissProgressDialog() {
    if (mProgressDialogHandler != null) {
      mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
          .sendToTarget();
      mProgressDialogHandler = null;
    }
  }

  @Override public void onError(Throwable e) {
    dismissProgressDialog();
    Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
  }

  @Override public void onComplete() {
    dismissProgressDialog();
    Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
  }

  @Override protected void onStart() {
    super.onStart();
    showProgressDialog();
  }

  @Override public void onNext(T t) {
    mSubscriberOnNextListener.onNext(t);
  }

  @Override public void onCancelProgress() {
    if (isDisposed()) dispose();
  }
}