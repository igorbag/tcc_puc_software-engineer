package br.com.igorbagliotti.school.view.activity.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.DaoSession;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;
import br.com.igorbagliotti.school.view.MvpActivity;
import br.com.igorbagliotti.school.view.activity.aluno.AlunoActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class DashBoardActivity extends MvpActivity implements DashboardView {

    @BindView(R.id.tvLblLoggedUserName)
    TextView tvLblLoggedUserName;

    private DaoSession mDaoSession;

    @Override
    protected int getResourceLayout() {
        return R.layout.dashboard_activity;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        mDaoSession = ((BaseApplication) getApplication()).getDaoSession();
        setLoggedNameDashboard();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public Context getContext() {
        return this;
    }


    private void setLoggedNameDashboard() {
        final LoginResponse loginInformation = mDaoSession.getLoginResponseDao().loadByRowId(1);
        tvLblLoggedUserName.setText(getString(R.string.lbl_user_logged, loginInformation != null ? loginInformation.nome : getString(R.string.lbl_welcome)));
    }

    private LoginResponse getLoginResponse() {
        return mDaoSession.getLoginResponseDao().loadByRowId(1);
    }

    @OnClick(R.id.cvMenuPrincipal)
    void onClick_cvMenuPrincipal() {
        startActivity(new Intent(this, AlunoActivity.class));
    }
}
