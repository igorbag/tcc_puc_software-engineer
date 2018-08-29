package br.com.igorbagliotti.school.view.fragment.login;

import br.com.igorbagliotti.baseapp.view.BaseView;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginRequest;

/**
 * Created by Igor Rotondo Bagliot on 22/08/2018.
 */

public interface LoginView extends BaseView {
    void showProgress();

    void hideProgress();

}
