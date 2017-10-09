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
import android.widget.LinearLayout;
import android.widget.TextView;

import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.LearningAct;
import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.RealmModel.CategoryItems;
import co.in.dreamguys.littlekids.ShowCategoryItems;
import co.in.dreamguys.littlekids.Util.Utility;
import io.realm.RealmResults;

/**
 * Created by user5 on 11-09-2017.
 */

public class CategoryItemsAdapter extends BaseAdapter {

    private Context mContext;
    private RealmResults<CategoryItems> mCategoryItemList;
    Animation mBlink;

    public CategoryItemsAdapter() {
    }

    public void setData(RealmResults<CategoryItems> mCategoryItemList) {
        this.mCategoryItemList = mCategoryItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCategoryItemList.size();
    }

    @Override
    public CategoryItems getItem(int position) {
        if (position < 0 || position >= mCategoryItemList.size()) {
            return null;
        } else {
            return mCategoryItemList.get(position);
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
        viewHolder.setLanguageDatas(position);
        mBlink = AnimationUtils.loadAnimation(mContext, R.anim.blink);
        viewHolder.ll_category.startAnimation(mBlink);
        return view;
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.adapter_show_category_items, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    public void setClassInstance(Context mContext) {
        this.mContext = mContext;
    }

    private class ViewHolder {
        TextView inputLanguage;
        LinearLayout ll_category;

        ViewHolder(View view) {
            inputLanguage = (TextView) view.findViewById(R.id.ASC_TV_category_items);
            ll_category = (LinearLayout)view.findViewById(R.id.ASC_CV_show_category_items);
        }

        void setLanguageDatas(final int positions) {
            inputLanguage.setText(getItem(positions).getSection_title());
            final MediaPlayer mp = Utility.createMediaplayerAudio(mContext);
            inputLanguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    ((ShowCategoryItems) mContext).getMediaPlayInstance(mp);
                    showLearningAct(getItem(positions));
                }
            });
        }
    }

    private void showLearningAct(CategoryItems sections) {
        final Activity activity = (Activity) mContext;
        Intent callCatAct = new Intent(mContext, LearningAct.class);
        callCatAct.putExtra(Config.SECTION_ID, sections.getSection_title());
        mContext.startActivity(callCatAct);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);


    }
}
