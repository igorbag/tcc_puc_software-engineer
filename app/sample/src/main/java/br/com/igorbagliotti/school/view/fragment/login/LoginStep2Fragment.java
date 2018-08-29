package br.com.igorbagliotti.school.view.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import org.parceler.Parcels;

import br.com.igorbagliotti.baseapp.ui.fragment.BaseFragment;
import br.com.igorbagliotti.school.R;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginStep2Fragment extends BaseFragment {


    @BindView(R.id.btnEntrar)
    Button btnEntrar;

    public static LoginStep2Fragment newInstance() {
        Bundle args = new Bundle();
        LoginStep2Fragment fragment = new LoginStep2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_login_step2;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.btnEntrar)
    void onClick_btnEntrar() {
        getFragmentManager().beginTransaction().replace(R.id.fl_login_step1, LoginStep3Fragment.newInstance()).addToBackStack(null).commit();
    }

}