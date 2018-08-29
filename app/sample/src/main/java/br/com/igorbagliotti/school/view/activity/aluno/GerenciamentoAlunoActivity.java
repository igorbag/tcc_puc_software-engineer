package br.com.igorbagliotti.school.view.activity.aluno;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

import br.com.igorbagliotti.school.R;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;
import br.com.igorbagliotti.school.data.remote.model.student.Student;
import br.com.igorbagliotti.school.util.DialogFactory;
import br.com.igorbagliotti.school.util.MaskTextWatcher;
import br.com.igorbagliotti.school.view.MvpActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class GerenciamentoAlunoActivity extends MvpActivity implements AlunoView {

    public static final String EXTRA_LOGIN_RESPONSE = "GerenciamentoAlunoActivity.EXTRA_LOGIN_RESPONSE";
    public static final String EXTRA_TIPO_EXIBICAO = "GerenciamentoAlunoActivity.EXTRA_TIPO_EXIBICAO";

    private static ProgressBar mProgressBar = null;

    @BindView(R.id.tilNome)
    TextInputLayout tilNome;
    @BindView(R.id.tilCpf)
    TextInputLayout tilCpf;
    @BindView(R.id.tilEndereco)
    TextInputLayout tilEndereco;
    @BindView(R.id.tilEstado)
    TextInputLayout tilEstado;
    @BindView(R.id.tilMunicipio)
    TextInputLayout tilMunicipio;
    @BindView(R.id.tilTelefone)
    TextInputLayout tilTelefone;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.tilSenha)
    TextInputLayout tilSenha;

    @BindView(R.id.etNome)
    EditText etNome;
    @BindView(R.id.etCpf)
    EditText etCpf;
    @BindView(R.id.etEndereco)
    EditText etEndereco;
    @BindView(R.id.etEstado)
    EditText etEstado;
    @BindView(R.id.etMunicipio)
    EditText etMunicipio;
    @BindView(R.id.etTelefone)
    EditText etTelefone;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etSenha)
    EditText etSenha;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.btnConfirmar)
    Button btnConfirmar;
    private AlunoPresenter mPresenter;
    private LoginResponse mLoginResponse;
    private TipoExibicao mTipoExibicao;


    public static Intent getCallingIntent(Context context, LoginResponse loginResponse, TipoExibicao tipoExibicao) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_LOGIN_RESPONSE, Parcels.wrap(loginResponse));
        bundle.putParcelable(EXTRA_TIPO_EXIBICAO, Parcels.wrap(tipoExibicao));
        return new Intent(context, GerenciamentoAlunoActivity.class).putExtras(bundle);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.ger_aluno_activity;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        addMaskEditText();
        setUpParcelable();
        selectMode();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        changeTitle();
        setUpPresenter();
    }

    private void changeTitle() {
        if (isEditMode()) {
            getBaseActionBar().setTitle(getString(R.string.lbl_activity_editar_aluno));
        } else if (isDetailMode()) {
            getBaseActionBar().setTitle(getString(R.string.lbl_activity_visualizar_aluno));
        } else {
            getBaseActionBar().setTitle(getString(R.string.lbl_activity_cadastrar_aluno));
        }
    }


    @Override
    public void showProgress() {
        if (mProgressBar == null) {
            mProgressBar = DialogFactory.DProgressBar(mContext);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAllStudents(List<LoginResponse> loginResponse) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.btnConfirmar)
    void onClick_btnConfirmar() {
        if (isValidForm()) {
            onEditOrSave();
        }
    }

    private void onEditOrSave() {
        if (mLoginResponse != null && StringUtils.isNoneEmpty(mLoginResponse.id)) {
            edit();
        } else {
            save();
        }
    }

    private void save() {
        final Student student = new Student(
                etCpf.getText().toString(),
                etNome.getText().toString(),
                etEndereco.getText().toString(),
                etEstado.getText().toString(),
                etMunicipio.getText().toString(),
                etTelefone.getText().toString(),
                etEmail.getText().toString(),
                etSenha.getText().toString(),
                true,
                new Date().toString());
        mPresenter.saveStudents(student);
    }

    private void edit() {
        final Student student = new Student(
                mLoginResponse.id,
                etCpf.getText().toString(),
                etNome.getText().toString(),
                etEndereco.getText().toString(),
                etEstado.getText().toString(),
                etMunicipio.getText().toString(),
                etTelefone.getText().toString(),
                etEmail.getText().toString(),
                etSenha.getText().toString(),
                true,
                new Date().toString());
        mPresenter.editStudent(student);
    }

    private void addMaskEditText() {
        final TextWatcher textWatcherCpf = new MaskTextWatcher(etCpf, MaskTextWatcher.FORMAT_CPF);
        etCpf.addTextChangedListener(textWatcherCpf);
        final TextWatcher textWatcherTelefone = new MaskTextWatcher(etTelefone, MaskTextWatcher.FORMAT_PHONE);
        etTelefone.addTextChangedListener(textWatcherTelefone);
    }

    private boolean isValidForm() {
        boolean isValid = nomeIsValid();
        isValid = cpfIsValid() && isValid;
        isValid = enderecoIsValid() && isValid;
        isValid = estadoIsValid() && isValid;
        isValid = municipioIsValid() && isValid;
        isValid = telefoneIsValid() && isValid;
        isValid = emailIsValid() && isValid;
        isValid = senhaIsValid() && isValid;
        return isValid;
    }

    private boolean cpfIsValid() {
        if (StringUtils.isEmpty(etCpf.getText().toString())) {
            tilCpf.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilCpf.setError(null);
        return true;
    }

    private boolean nomeIsValid() {
        if (StringUtils.isEmpty(etNome.getText().toString())) {
            tilNome.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilNome.setError(null);
        return true;
    }


    private boolean enderecoIsValid() {
        if (StringUtils.isEmpty(etEndereco.getText().toString())) {
            tilEndereco.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilEndereco.setError(null);
        return true;
    }

    private boolean estadoIsValid() {
        if (StringUtils.isEmpty(etEstado.getText().toString())) {
            tilEstado.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilEstado.setError(null);
        return true;
    }

    private boolean municipioIsValid() {
        if (StringUtils.isEmpty(etMunicipio.getText().toString())) {
            tilMunicipio.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilMunicipio.setError(null);
        return true;
    }

    private boolean telefoneIsValid() {
        if (StringUtils.isEmpty(etTelefone.getText().toString())) {
            tilTelefone.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilTelefone.setError(null);
        return true;
    }

    private boolean emailIsValid() {
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            tilEmail.setError(getString(R.string.lbl_error_email));
            return false;
        }
        tilEmail.setError(null);
        return true;
    }

    private boolean senhaIsValid() {
        if (StringUtils.isEmpty(etSenha.getText().toString())) {
            tilSenha.setError(getString(R.string.lbl_error_required));
            return false;
        }
        tilSenha.setError(null);
        return true;
    }

    private void setUpParcelable() {
        mLoginResponse = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_LOGIN_RESPONSE));
        mTipoExibicao = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_TIPO_EXIBICAO));
    }

    private boolean isEditMode() {
        if (mTipoExibicao != null && mTipoExibicao.equals(TipoExibicao.EDITAR)) {
            return true;
        }
        return false;
    }

    private boolean isDetailMode() {
        if (mTipoExibicao != null && mTipoExibicao.equals(TipoExibicao.VISUALIZAR)) {
            return true;
        }
        return false;
    }

    private void selectMode() {
        detailMode();
        editMode();
    }

    private void editMode() {
        if (isEditMode()) {
            bindInformations(mLoginResponse);
        }
    }

    private void detailMode() {
        if (isDetailMode()) {
            tvInfo.setVisibility(View.GONE);
            etSenha.setVisibility(View.GONE);
            btnConfirmar.setVisibility(View.GONE);
            disableAllEditTexts();
            bindInformations(mLoginResponse);
        }
    }

    private void bindInformations(LoginResponse loginResponse) {
        etNome.setText(loginResponse.nome);
        etCpf.setText(loginResponse.cpf);
        etEndereco.setText(loginResponse.endereco);
        etEstado.setText(loginResponse.estado);
        etMunicipio.setText(loginResponse.municipio);
        etTelefone.setText(loginResponse.telefone);
        etEmail.setText(loginResponse.email);
        etSenha.setText(loginResponse.senha);
    }

    private void disableAllEditTexts() {
        etNome.setEnabled(false);
        etCpf.setEnabled(false);
        etEndereco.setEnabled(false);
        etEstado.setEnabled(false);
        etMunicipio.setEnabled(false);
        etTelefone.setEnabled(false);
        etEmail.setEnabled(false);
        etSenha.setEnabled(false);
    }

    private void setUpPresenter() {
        mPresenter = new AlunoPresenter(this);
        mPresenter.attachView(this);
    }
}