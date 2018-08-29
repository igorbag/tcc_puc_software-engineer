package br.com.igorbagliotti.school.view.activity.login;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.DaoSession;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;
import br.com.igorbagliotti.school.view.MvpActivity;
import br.com.igorbagliotti.school.view.activity.dashboard.DashBoardActivity;
import br.com.igorbagliotti.school.view.fragment.login.LoginStep2Fragment;

public class LoginStep1Activity extends MvpActivity {

    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDaoSession = ((BaseApplication) getApplication()).getDaoSession();
        callView();
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_login_step1;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {

    }

    private void callView() {
        if (userHasLoggged()) {
            startActivity(new Intent(this, DashBoardActivity.class));
        } else {
            getBaseFragmentManager().beginTransaction().replace(R.id.fl_login_step1, LoginStep2Fragment.newInstance()).addToBackStack(null).commit();
        }
    }


    /**
     * Metodo responsavel por verificar se existe usuario salvo local
     *
     * @return
     */
    private boolean userHasLoggged() {
        final List<LoginResponse> loginResponseList = mDaoSession.getLoginResponseDao().loadAll();

        if (loginResponseList != null && loginResponseList.size() > 0) {
            return true;
        }
        return false;
    }

}
