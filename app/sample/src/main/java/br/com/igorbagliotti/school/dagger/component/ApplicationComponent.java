package br.com.igorbagliotti.school.dagger.component;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.dagger.module.ApplicationModule;
import br.com.igorbagliotti.school.data.local.PreferencesHelper;
import br.com.igorbagliotti.school.data.remote.APIService;
import br.com.igorbagliotti.school.data.remote.UnauthorisedInterceptor;
import br.com.igorbagliotti.school.view.activity.aluno.AlunoPresenter;
import br.com.igorbagliotti.school.view.activity.dashboard.DashboardPresenter;
import br.com.igorbagliotti.school.view.fragment.login.LoginPresenter;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication mainPresenter);

    void inject(LoginPresenter baseApplication);

    void inject(DashboardPresenter dashboardPresenter);

    void inject(AlunoPresenter alunoPresenter);

    void inject(UnauthorisedInterceptor unauthorisedInterceptor);


    APIService apiService();

    EventBus eventBus();

    PreferencesHelper prefsHelper();


}