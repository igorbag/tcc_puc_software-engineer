package br.com.igorbagliotti.school;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.VisibleForTesting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

import br.com.igorbagliotti.school.dagger.component.ApplicationComponent;
import br.com.igorbagliotti.school.dagger.component.DaggerApplicationComponent;
import br.com.igorbagliotti.school.dagger.module.ApplicationModule;
import br.com.igorbagliotti.school.data.remote.model.auth.DaoMaster;
import br.com.igorbagliotti.school.data.remote.model.auth.DaoSession;
import br.com.igorbagliotti.school.events.AuthenticationErrorEvent;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class BaseApplication extends Application {
    public static final String DB_NAME = "SchoolDatabase";
    @Inject
    EventBus mEventBus;
    private Scheduler mScheduler;
    private ApplicationComponent mApplicationComponent;
    private DaoSession mDaoSession;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        boolean isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

        if (isDebuggable) {
            Timber.plant(new Timber.DebugTree());
        }

        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
        mEventBus.register(this);

        initDatabase();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    private void initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @VisibleForTesting
    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.mApplicationComponent = applicationComponent;
    }

    public Scheduler getSubscribeScheduler() {
        if (mScheduler == null) {
            mScheduler = Schedulers.io();
        }
        return mScheduler;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Timber.e("########## onLowMemory ##########");
    }

    @Override
    public void onTerminate() {
        mEventBus.unregister(this);
        super.onTerminate();
    }

    @Subscribe
    public void onEvent(AuthenticationErrorEvent event) {
        Timber.e("Unauthorized! Redirect to Signin Activity..!.");
    }

}
