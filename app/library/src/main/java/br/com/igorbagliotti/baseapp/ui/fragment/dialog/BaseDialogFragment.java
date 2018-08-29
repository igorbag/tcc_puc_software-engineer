package br.com.igorbagliotti.baseapp.ui.fragment.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import br.com.igorbagliotti.baseapp.ui.BaseActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public abstract class BaseDialogFragment extends DialogFragment {

    protected Context mContext;
    protected LayoutInflater mInflater;
    private Unbinder unbinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = mInflater.inflate(getResourceLayout(), null);
        unbinder = ButterKnife.bind(this, view);
        Timber.tag(getClass().getSimpleName());
        return setupDialog(view);
    }

    protected Dialog setupDialog(View view) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x4000));
        return dialog;
    }

    protected abstract int getResourceLayout();

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
