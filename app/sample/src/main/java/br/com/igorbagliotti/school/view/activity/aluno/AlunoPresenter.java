package br.com.igorbagliotti.school.view.activity.aluno;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import br.com.igorbagliotti.baseapp.presenter.BasePresenter;
import br.com.igorbagliotti.school.BaseApplication;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.APIService;
import br.com.igorbagliotti.school.data.remote.model.student.Student;
import br.com.igorbagliotti.school.util.DialogFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class AlunoPresenter implements BasePresenter<AlunoView> {

    @Inject
    APIService mAPIService;
    private AlunoView mAlunoView;
    private Subscription mSubscription;

    @Inject
    public AlunoPresenter(Context context) {
        ((BaseApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Override
    public void attachView(AlunoView view) {
        mAlunoView = view;
    }

    @Override
    public void detachView() {
        mAlunoView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    /**
     * Metodo responsavel por chamar o servico de carreggar estudantes para a listagem na tela
     */
    public void loadAllStudents() {
        if (mSubscription != null) mSubscription.unsubscribe();
        BaseApplication baseApplication = BaseApplication.get(mAlunoView.getContext());
        mSubscription = mAPIService.getAllStudents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(loginResponse -> {
                    mAlunoView.showAllStudents(loginResponse);
                }, throwable -> {
                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_error_generic_api)).show();
                });

    }

    /**
     * Metodo responsavel por salvar estudantes
     */
    public void saveStudents(Student student) {
        BaseApplication baseApplication = BaseApplication.get(mAlunoView.getContext());
        mSubscription = mAPIService.saveStudent(student)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(loginResponse -> {
                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_sucess_alert), (dialog, which) -> {
                        mAlunoView.getContext().startActivity(new Intent(mAlunoView.getContext(), AlunoActivity.class));
                    }).show();
                }, throwable -> {
                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_error_generic_api)).show();
                });
    }

    /**
     * Metodo responsavel por salvar estudantes
     */
    public void editStudent(Student student) {
        BaseApplication baseApplication = BaseApplication.get(mAlunoView.getContext());
        mSubscription = mAPIService.editStudent(student.id, student)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(loginResponse -> {

                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_sucess_edit_alert), (dialog, which) -> {
                        mAlunoView.getContext().startActivity(new Intent(mAlunoView.getContext(), AlunoActivity.class));
                    }).show();

                }, throwable -> {
                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_error_generic_api)).show();
                });
    }


    /**
     * Metodo responsavel por salvar estudantes
     */
    public void deleteStudent(String id) {
        BaseApplication baseApplication = BaseApplication.get(mAlunoView.getContext());
        mSubscription = mAPIService.deleteStudent(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(loginResponse -> {
                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_sucess_remove_alert)).show();
                }, throwable -> {
                    DialogFactory.createSimpleOkDialog(mAlunoView.getContext(), mAlunoView.getContext().getString(R.string.app_name), mAlunoView.getContext().getString(R.string.lbl_error_generic_api)).show();
                });
    }
}