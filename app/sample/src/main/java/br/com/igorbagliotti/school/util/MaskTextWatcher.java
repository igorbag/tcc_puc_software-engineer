package br.com.igorbagliotti.school.util;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by igorb on 26/06/2018.
 */

public class MaskTextWatcher implements TextWatcher {


    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_MOBILE_PHONE = "(##)#####-####";
    public static final String FORMAT_DDD_PHONE = "##";
    public static final String FORMAT_SIMPLE_PHONE = "####-####";
    public static final String FORMAT_SIMPLE_MOBILE_PHONE = "#####-####";
    public static final String FORMAT_PHONE = "(##)####-####";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";
    public static final String FORMAT_DIGIT = "#";

    private final EditText mEditText;
    private final String mMask;
    private boolean mIsUpdating;
    private String mOld = "";

    public MaskTextWatcher(final EditText editText, final String mask) {
        this.mEditText = editText;
        this.mMask = mask;
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]", "").replaceAll("[)]", "").replaceAll("[:]", "");
    }

    @Override
    public void afterTextChanged(final Editable s) {
    }

    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        final String str = unmask(s.toString());
        String mask = "";
        if (mIsUpdating) {
            mOld = str;
            mIsUpdating = false;
            return;
        }
        int i = 0;
        for (final char m : mMask.toCharArray()) {
            if (m != '#' && str.length() > mOld.length()) {
                mask += m;
                continue;
            }
            try {
                mask += str.charAt(i);
            } catch (final Exception e) {
                break;
            }
            i++;
        }
        mIsUpdating = true;
        mEditText.setText(mask);
        mEditText.setSelection(mask.length());
    }
}
