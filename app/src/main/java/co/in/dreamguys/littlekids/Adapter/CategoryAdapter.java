package co.in.dreamguys.littlekids.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import co.in.dreamguys.littlekids.ChooseCategory;
import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.RealmModel.Category;
import co.in.dreamguys.littlekids.ShowCategoryItems;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.RealmList;

/**
 * Created by user5 on 11-09-2017.
 */

public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private RealmList<Category> mCategoryList;
    Animation mBlink;

    public CategoryAdapter() {
    }

    public void setData(RealmList<Category> mCategoryList) {
        this.mCategoryList = mCategoryList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Category getItem(int position) {
        if (position < 0 || position >= mCategoryList.size()) {
            return null;
        } else {
            return mCategoryList.get(position);
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
        mBlink = AnimationUtils.loadAnimation(mContext, R.anim.blink);
        viewHolder.inputLanguage.startAnimation(mBlink);
        return view;
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.adapter_category_list, parent, false);
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

        void setLanguageDatas(final Category category) {
            inputLanguage.setText(category.getCategory_name());
            final MediaPlayer mp = Utility.createMediaplayerAudio(mContext);
            inputLanguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    ((ChooseCategory) mContext).getMediaPlayInstance(mp);
                    showCategoryItemsAct(category.getCategory_id(), category.getLang_id());
                }
            });
        }
    }

    private void showCategoryItemsAct(String category_id, String lang_id) {
        final Activity activity = (Activity) mContext;
        Intent callCatAct = new Intent(mContext, ShowCategoryItems.class);
        callCatAct.putExtra(Config.CAT_ID, category_id);
        callCatAct.putExtra(Config.LANG_ID, lang_id);
        mContext.startActivity(callCatAct);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
