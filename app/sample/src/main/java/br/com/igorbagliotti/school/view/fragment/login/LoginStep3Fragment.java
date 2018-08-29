package br.com.igorbagliotti.school.view.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.igorbagliotti.baseapp.ui.fragment.BaseFragment;
import br.com.igorbagliotti.school.R;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginStep3Fragment extends BaseFragment {


    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnProximo)
    Button btnProximo;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.tilLoginEmail)
    TextInputLayout tilEmail;

    public static LoginStep3Fragment newInstance() {
        Bundle args = new Bundle();
        LoginStep3Fragment fragment = new LoginStep3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_login_step3;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        //for recreation of the toolbar
    }


    @OnClick(R.id.btnBack)
    void onClick_btnBack() {
        getFragmentManager().beginTransaction().replace(R.id.fl_login_step1, LoginStep2Fragment.newInstance()).addToBackStack(null).commit();
    }

    @OnClick(R.id.btnProximo)
    void onClick_btnProximo() {
        if (emailIsValid()) {
            final String email = etEmail.getText().toString().trim();
            getFragmentManager().beginTransaction().replace(R.id.fl_login_step1, LoginStep4Fragment.newInstance(email)).addToBackStack(null).commit();
        }
    }

    private boolean emailIsValid() {
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            tilEmail.setError(getString(R.string.lbl_error_email));
            return false;
        }
        tilEmail.setError(null);
        return true;
    }
}