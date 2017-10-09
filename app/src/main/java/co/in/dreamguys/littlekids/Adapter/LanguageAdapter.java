package co.in.dreamguys.littlekids.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import co.in.dreamguys.littlekids.ChooseCategory;
import co.in.dreamguys.littlekids.ChooseLanguage;
import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.RealmResults;

/**
 * Created by user5 on 11-09-2017.
 */

public class LanguageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private RealmResults<LanguageInfo> mLanguageList;
    private int lastPosition = -1;

    public LanguageAdapter() {
    }

    public void setData(RealmResults<LanguageInfo> mLanguageList) {
        this.mLanguageList = mLanguageList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLanguageList.size();
    }

    @Override
    public LanguageInfo getItem(int position) {
        if (position < 0 || position >= mLanguageList.size()) {
            return null;
        } else {
            return mLanguageList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null ? convertView : createView(parent));
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.setLanguageDatas(getItem(position));
        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        view.startAnimation(animation);
        lastPosition = position;*/
        return view;
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.adapter_language_list, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    public void setClassInstance(Context mContext) {
        this.mContext = mContext;
    }

    private class ViewHolder {
        TextView inputLanguage;

        ViewHolder(View view) {
            inputLanguage = (TextView) view.findViewById(R.id.AC_TV_category);
        }

        void setLanguageDatas(final LanguageInfo language) {
            inputLanguage.setText(language.getLang());
            final MediaPlayer mp = Utility.createMediaplayerAudio(mContext);
            inputLanguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    ((ChooseLanguage) mContext).getMediaPlayInstance(mp);
                    showCategoryAct(language.getLang_id());
                }
            });
        }
    }

    private void showCategoryAct(String lang_id) {
        final Activity activity = (Activity) mContext;
        Intent callCatAct = new Intent(mContext, ChooseCategory.class);
        callCatAct.putExtra(Config.LANG_ID, lang_id);
        mContext.startActivity(callCatAct);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


}
