package br.com.igorbagliotti.school.view.activity.dashboard;

import android.content.Context;

import javax.inject.Inject;

import br.com.igorbagliotti.baseapp.presenter.BasePresenter;
import br.com.igorbagliotti.school.BaseApplication;

public class DashboardPresenter implements BasePresenter<DashboardView> {

    private DashboardView mDashboardView;

    @Inject
    public DashboardPresenter(Context context) {
        ((BaseApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Override
    public void attachView(DashboardView view) {
        mDashboardView = view;
    }

    @Override
    public void detachView() {
        mDashboardView = null;
    }


}