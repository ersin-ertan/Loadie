package com.nullcognition.loadie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

public class MainActivity extends AppCompatActivity {

  private Router router;
  private Router router2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    ViewGroup container2 = (ViewGroup) findViewById(R.id.controller_container2);
    router2 = Conductor.attachRouter(this, container2, savedInstanceState);
    if (!router2.hasRootController()) {
      router2.setRoot(RouterTransaction.with(new NoUserInput()));
    }


    ViewGroup container = (ViewGroup) findViewById(R.id.controller_container);
    router = Conductor.attachRouter(this, container, savedInstanceState);
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(new WithUserInput()));
    }







  }
}

