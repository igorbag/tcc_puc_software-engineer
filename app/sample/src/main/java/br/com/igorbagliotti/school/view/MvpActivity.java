package br.com.igorbagliotti.school.view;

import android.os.Bundle;

import br.com.igorbagliotti.baseapp.ui.BaseActivity;
import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.dagger.component.ActivityComponent;
import br.com.igorbagliotti.school.dagger.component.DaggerActivityComponent;

public abstract class MvpActivity extends BaseActivity {

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder().applicationComponent(getApp().getApplicationComponent()).build();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    protected BaseApplication getApp() {
        return (BaseApplication) getApplicationContext();
    }

}
