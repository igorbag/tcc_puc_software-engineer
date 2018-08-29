package  br.com.igorbagliotti.baseapp.ui.adapter.viewholder;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseListViewHolder<Data> {
    protected Context mContext;

    public BaseListViewHolder(View itemView) {
        ButterKnife.bind(this, itemView);
        Timber.tag(getClass().getSimpleName());
    }

    public abstract void bind(Data data);
}
