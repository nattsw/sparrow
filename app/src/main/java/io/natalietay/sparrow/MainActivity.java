package io.natalietay.sparrow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import io.natalietay.sparrow_service.GithubService;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    // this service is annotated to be injected by line 32
    @Inject
    GithubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SparrowApplication application = (SparrowApplication) getApplication();
        // over here you can see that we have defined a custom application
        // that builds the dagger object graph when the application is created
        application.getAppComponent().inject(this);

        Button button = (Button) findViewById(R.id.resubscribe);
        button.setOnClickListener(getOnClickListener());
    }

    @android.support.annotation.NonNull
    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeZenCall();
            }
        };
    }

    @android.support.annotation.NonNull
    private Observer<String> getZenObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Toast.makeText(MainActivity.this, "onSubscribe", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(@NonNull String response) {
                Toast.makeText(MainActivity.this, "onNext", Toast.LENGTH_SHORT).show();
                final TextView textField = (TextView) findViewById(R.id.zen);
                textField.setText(response);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(MainActivity.this, "onError" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void makeZenCall() {
        service.getZen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getZenObserver());
    }
}
