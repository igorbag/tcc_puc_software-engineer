package br.com.igorbagliotti.school.view.activity.aluno;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.igorbagliotti.baseapp.ui.adapter.BaseRecyclerAdapter;
import br.com.igorbagliotti.baseapp.ui.adapter.viewholder.BaseItemViewHolder;
import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;
import butterknife.BindView;

/**
 * Created by Igor Rotondo Bagliot on 24/08/2018.
 */

public class AlunoHolder extends BaseItemViewHolder<LoginResponse> {
    @BindView(R.id.tvNome)
    TextView tvNome;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.ivEdit)
    ImageView ivEdit;
    private AlunoAdapter.DeleteAlunoClickListener mDelete;
    private AlunoAdapter.EditAlunoClickListener mEdit;
    private AlunoAdapter.VisualizarAlunoClickListener mVisualizar;

    public AlunoHolder(Context context, View itemView, BaseRecyclerAdapter.OnItemClickListener itemClickListener, BaseRecyclerAdapter.OnLongItemClickListener longItemClickListener,
                       AlunoAdapter.DeleteAlunoClickListener deleteClickListener,
                       AlunoAdapter.EditAlunoClickListener editAlunoClickListener,
                       AlunoAdapter.VisualizarAlunoClickListener visualizarAlunoClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
        this.mContext = context;
        mDelete = deleteClickListener;
        mEdit = editAlunoClickListener;
        mVisualizar = visualizarAlunoClickListener;

    }

    @Override
    public void bind(LoginResponse loginResponse) {
        tvNome.setText(loginResponse.nome);
        ivDelete.setOnClickListener(v -> mDelete.onClick(loginResponse));
        ivEdit.setOnClickListener(v -> mEdit.onClick(loginResponse));
        itemView.setOnClickListener(v -> mVisualizar.onClick(loginResponse));
    }
}
