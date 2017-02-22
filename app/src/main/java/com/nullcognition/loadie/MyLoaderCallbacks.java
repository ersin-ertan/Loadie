package com.nullcognition.loadie;

import android.util.Log;
import me.tatarka.loadie.Loader;

class MyLoaderCallbacks<T> implements Loader.Callbacks<T> {

  public static final String TAG = "MLC";
  private T result;

  public T getResult() {
    return result;
  }

  @Override public void onLoaderStart() {
    Log.i(TAG, "onLoaderStart");
  }

  @Override public void onLoaderResult(T result) {
    Log.i(TAG, "onLoaderResult: " + result.toString());
    this.result = result;
  }

  @Override public void onLoaderError(Throwable error) {
    Log.i(TAG, "onLoaderError");
  }

  @Override public void onLoaderSuccess() {
    Log.i(TAG, "onLoaderSuccess");
  }
}