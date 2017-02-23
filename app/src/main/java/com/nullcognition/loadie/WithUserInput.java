package com.nullcognition.loadie;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.bluelinelabs.conductor.Controller;
import java.util.concurrent.Callable;
import me.tatarka.loadie.Loader;
import me.tatarka.loadie.LoaderManager;
import me.tatarka.loadie.RxLoader;
import me.tatarka.loadie.controller.LoaderManagerProvider;
import rx.Observable;

public class WithUserInput extends Controller {

  public static final int WITH_USER_INPUT_LOADER_ID = 1;

  LoaderManager loaderManager;
  MyLoaderCallbacks<String> stringMyLoaderCallbacks;
  Loader<String> loader;

  public static final String TAG = "WithUserInput";
  Unbinder unbinder;

  @BindView(R.id.title) TextView title;
  @BindView(R.id.editText) EditText input;

  @OnClick(R.id.button) void startLoader() {

    if (!loader.hasResult() && !loader.isRunning()) {
      //if (loader.getCallbacks() == null) loader.setCallbacks(stringMyLoaderCallbacks);
      loader.start();
    }
  }

  @OnClick(R.id.button2) void restartLoader() {
    if (loader != null) loader.restart();
  }

  @OnClick(R.id.button3) void Loader() {
    if (loader != null && loader.hasResult()) {
      Toast.makeText(getActivity(), "Result:" + stringMyLoaderCallbacks.getResult(),
          Toast.LENGTH_SHORT).show();
    }
  }

  public WithUserInput() {
    loaderManager = LoaderManagerProvider.forController(this);

    loader = loaderManager.init(WITH_USER_INPUT_LOADER_ID,
        RxLoader.create(Observable.fromCallable(new Callable<String>() {
          @Override public String call() throws Exception {
            return input.getText().toString();
          }
        })), stringMyLoaderCallbacks = new MyLoaderCallbacks<String>() {
        });
  }

  @NonNull @Override
  protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    View rootView = inflater.inflate(R.layout.my_controller, container, false);
    rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.holo_purple));
    unbinder = ButterKnife.bind(this, rootView);

    input.setText(TAG);
    title.setText(TAG);

    return rootView;
  }

  @Override protected void onDestroyView(@NonNull View view) {
    unbinder.unbind();
    super.onDestroyView(view);
  }
}

