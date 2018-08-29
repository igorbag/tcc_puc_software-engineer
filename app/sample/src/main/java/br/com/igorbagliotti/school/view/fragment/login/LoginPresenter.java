package br.com.igorbagliotti.school.view.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import br.com.igorbagliotti.baseapp.presenter.BasePresenter;
import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.APIService;
import br.com.igorbagliotti.school.data.remote.model.auth.DaoSession;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginRequest;
import br.com.igorbagliotti.school.util.DialogFactory;
import br.com.igorbagliotti.school.view.activity.dashboard.DashBoardActivity;
import retrofit2.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class LoginPresenter implements BasePresenter<LoginView> {

    private static final String HTTP_ERROR_500 = "500";

    @Inject
    APIService mAPIService;
    @Inject
    EventBus mEventBus;
    private LoginView mLoginView;
    private Subscription mSubscription;
    private DaoSession mDaoSession;

    @Inject
    public LoginPresenter(Context context, DaoSession daoSession) {
        mDaoSession = daoSession;
        ((BaseApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }


    @Override
    public void attachView(LoginView view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {
        mLoginView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    /**
     * Metodo responsavel por prover a chamada da autenticacao de servico
     *
     * @param loginRequest
     */
    public void auth(LoginRequest loginRequest) {
        mLoginView.showProgress();
        if (mSubscription != null) mSubscription.unsubscribe();
        BaseApplication baseApplication = BaseApplication.get(mLoginView.getContext());
        mSubscription = mAPIService.autenticar(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(loginResponse -> {
                    mLoginView.hideProgress();
                    //1- Salva os dados do usuario local
                    mDaoSession.getLoginResponseDao().insert(loginResponse);
                    //2- Redireciona para a tela de dashboard se caso a API conseguir logar
                    mLoginView.getContext().startActivity(new Intent(mLoginView.getContext(), DashBoardActivity.class));
                    mLoginView.hideProgress();
                }, throwable -> {
                    mLoginView.hideProgress();
                    Log.d("Http error", String.valueOf(((HttpException) throwable).code()));
                    if (String.valueOf(((HttpException) throwable).code()).equals(HTTP_ERROR_500)) {
                        DialogFactory.createSimpleOkDialog(mLoginView.getContext(), mLoginView.getContext().getString(R.string.app_name), mLoginView.getContext().getString(R.string.lbl_login_incorreto)).show();
                    }
                });

    }
}