package br.com.igorbagliotti.school.dagger.module;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.data.local.PreferencesHelper;
import br.com.igorbagliotti.school.data.remote.APIService;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final BaseApplication mBaseApplication;

    public ApplicationModule(BaseApplication baseApplication) {
        this.mBaseApplication = baseApplication;
    }

    @Provides
    @Singleton
    public BaseApplication provideApplication() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    public APIService provideApiService() {
        return APIService.Factory.create(mBaseApplication);
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    public PreferencesHelper prefsHelper() {
        return new PreferencesHelper(mBaseApplication);
    }

}