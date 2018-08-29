package br.com.igorbagliotti.school.view.activity.aluno;

import java.util.List;

import br.com.igorbagliotti.baseapp.view.BaseView;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;

/**
 * Created by Igor Rotondo Bagliot on 24/08/2018.
 */

public interface AlunoView extends BaseView {
    void showProgress();

    void hideProgress();

    void showAllStudents(List<LoginResponse> loginResponse);
}
