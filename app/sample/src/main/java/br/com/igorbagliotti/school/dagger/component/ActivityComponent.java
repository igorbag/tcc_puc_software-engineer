package br.com.igorbagliotti.school.dagger.component;

import br.com.igorbagliotti.school.dagger.ActivityScope;
import br.com.igorbagliotti.school.view.activity.aluno.AlunoActivity;
import br.com.igorbagliotti.school.view.activity.aluno.GerenciamentoAlunoActivity;
import br.com.igorbagliotti.school.view.activity.dashboard.DashBoardActivity;
import br.com.igorbagliotti.school.view.activity.login.LoginStep1Activity;
import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ApplicationComponent {
    void inject(LoginStep1Activity loginStep1Activity);

    void inject(DashBoardActivity dashBoardActivity);

    void inject(AlunoActivity alunoActivity);

    void inject(GerenciamentoAlunoActivity gerenciamentoAlunoActivity);

}