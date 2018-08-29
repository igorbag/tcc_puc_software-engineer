package br.com.igorbagliotti.school.view.activity.aluno;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ProgressBar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import br.com.igorbagliotti.baseapp.ui.view.BaseRecyclerView;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;
import br.com.igorbagliotti.school.util.DialogFactory;
import br.com.igorbagliotti.school.view.MvpActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class AlunoActivity extends MvpActivity implements AlunoView {

    private static final String LOGIN_RESPONSE = "AlunoActivity.LOGIN_RESPONSE";
    private static ProgressBar mProgressBar = null;
    @BindView(R.id.rvStudents)
    BaseRecyclerView mRecyclerView;
    @BindView(R.id.fabAddUser)
    FloatingActionButton fabAddUser;
    private AlunoAdapter mAdapter;
    private AlunoPresenter mPresenter;

    @Override
    protected int getResourceLayout() {
        return R.layout.aluno_activity;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        getBaseActionBar().setTitle(getString(R.string.lbl_activity_aluno));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setUpAdapter();
        setUpRecyclerView();
        setUpPresenter();
    }

    private void setUpAdapter() {
        mAdapter = new AlunoAdapter(mContext, (deleteItemClick) -> {
            DialogFactory.createSimpleOkDialog(mContext, getString(R.string.app_name), getString(R.string.lbl_msg_confirm_delete), (dialog, which) -> {
                mPresenter.deleteStudent(deleteItemClick.id);
                mAdapter.remove(deleteItemClick);
                mAdapter.notifyDataSetChanged();
            }).show();
        }, (editItemClick) ->
        {   startActivity(GerenciamentoAlunoActivity.getCallingIntent(mContext, editItemClick, TipoExibicao.EDITAR));
        }, (visualizarItemClick) -> {
            startActivity(GerenciamentoAlunoActivity.getCallingIntent(mContext, visualizarItemClick, TipoExibicao.VISUALIZAR));
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadAllStudents();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    private void setUpPresenter() {
        mPresenter = new AlunoPresenter(this);
        mPresenter.attachView(this);
        mPresenter.loadAllStudents();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAllStudents(List<LoginResponse> loginResponse) {
        mAdapter.clear();
        mAdapter.addAll(loginResponse);
        mRecyclerView.refreshComplete();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.fabAddUser)
    void onClick_fabAddUser() {
        startActivity(new Intent(this, GerenciamentoAlunoActivity.class));
    }


}