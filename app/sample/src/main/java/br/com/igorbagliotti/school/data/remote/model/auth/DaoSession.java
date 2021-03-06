package br.com.igorbagliotti.school.data.remote.model.auth;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;

import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponseDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig loginResponseDaoConfig;

    private final LoginResponseDao loginResponseDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        loginResponseDaoConfig = daoConfigMap.get(LoginResponseDao.class).clone();
        loginResponseDaoConfig.initIdentityScope(type);

        loginResponseDao = new LoginResponseDao(loginResponseDaoConfig, this);

        registerDao(LoginResponse.class, loginResponseDao);
    }
    
    public void clear() {
        loginResponseDaoConfig.clearIdentityScope();
    }

    public LoginResponseDao getLoginResponseDao() {
        return loginResponseDao;
    }

}
