package br.com.igorbagliotti.school.view.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

import br.com.igorbagliotti.baseapp.ui.fragment.BaseFragment;
import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.DaoSession;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginRequest;
import br.com.igorbagliotti.school.util.DialogFactory;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginStep4Fragment extends BaseFragment implements LoginView {

    public static final String EXTRA_EMAIL_LOGIN = "LoginStep4Fragment.EXTRA_EMAIL_LOGIN";
    private static ProgressBar mProgressBar = null;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.etSenha)
    EditText etSenha;
    @BindView(R.id.tilLoginSenha)
    TextInputLayout tilLoginSenha;

    @BindView(R.id.btnConfirmar)
    Button btnConfirmar;


    private String mEmail;
    private LoginPresenter mLoginPresenter;
    private DaoSession mDaoSession;

    public static LoginStep4Fragment newInstance(String email) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_EMAIL_LOGIN, Parcels.wrap(email));
        LoginStep4Fragment fragment = new LoginStep4Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEmail = Parcels.unwrap(getArguments().getParcelable(EXTRA_EMAIL_LOGIN));
            mDaoSession = ((BaseApplication) getContext().getApplicationContext()).getDaoSession();

        }
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_login_step4;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        setUpPresenter();
    }

    private void setUpPresenter() {
        mLoginPresenter = new LoginPresenter(getActivity(), mDaoSession);
        mLoginPresenter.attachView(this);
    }

    @OnClick(R.id.btnBack)
    void onClick_btnBack() {
        getFragmentManager().beginTransaction().replace(R.id.fl_login_step1, LoginStep3Fragment.newInstance()).addToBackStack(null).commit();
    }


    @OnClick(R.id.btnConfirmar)
    void onClick_btnConfirmar() {
        if (passwordIsValid()) {
            final LoginRequest loginRequest = new LoginRequest().makeAuth(mEmail, etSenha.getText().toString());
            mLoginPresenter.auth(loginRequest);
        }
    }

    private boolean passwordIsValid() {
        if (StringUtils.isEmpty(etSenha.getText().toString())) {
            tilLoginSenha.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilLoginSenha.setError(null);
        return true;
    }

    @Override
    public void showProgress() {
        if (mProgressBar == null) {
            mProgressBar = DialogFactory.DProgressBar(mContext);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

}