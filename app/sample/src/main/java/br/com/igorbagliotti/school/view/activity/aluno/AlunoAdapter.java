package br.com.igorbagliotti.school.view.activity.aluno;

import android.content.Context;
import android.view.ViewGroup;

import br.com.igorbagliotti.baseapp.ui.adapter.BaseRecyclerAdapter;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;


/**
 * Created by deni rohimat on 17/02/15.
 */
public class AlunoAdapter extends BaseRecyclerAdapter<LoginResponse, AlunoHolder> {
    protected final DeleteAlunoClickListener mDeleteAlunoClickListener;
    protected final EditAlunoClickListener mEditAlunoClickListener;
    protected final VisualizarAlunoClickListener mVisualizarAlunoClickListener;

    public AlunoAdapter(Context context, DeleteAlunoClickListener deleteClickListener, EditAlunoClickListener editAlunoClickListener, VisualizarAlunoClickListener visualizarAlunoClickListener) {
        super(context);
        mDeleteAlunoClickListener = deleteClickListener;
        mEditAlunoClickListener = editAlunoClickListener;
        mVisualizarAlunoClickListener = visualizarAlunoClickListener;
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.student_item_row;
    }

    @Override
    public AlunoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlunoHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener, mDeleteAlunoClickListener, mEditAlunoClickListener, mVisualizarAlunoClickListener);
    }

    interface DeleteAlunoClickListener {
        void onClick(LoginResponse loginResponse);
    }

    interface EditAlunoClickListener {
        void onClick(LoginResponse loginResponse);
    }

    interface VisualizarAlunoClickListener {
        void onClick(LoginResponse loginResponse);
    }
}
