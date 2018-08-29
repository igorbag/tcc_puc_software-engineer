package br.com.igorbagliotti.baseapp.presenter;

import br.com.igorbagliotti.baseapp.view.BaseView;


public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}