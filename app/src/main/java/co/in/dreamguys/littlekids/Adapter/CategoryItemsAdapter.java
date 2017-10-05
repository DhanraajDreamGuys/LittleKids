package co.in.dreamguys.littlekids.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.LearningAct;
import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.RealmModel.CategoryItems;
import io.realm.RealmResults;

/**
 * Created by user5 on 11-09-2017.
 */

public class CategoryItemsAdapter extends BaseAdapter {

    private Context mContext;
    private RealmResults<CategoryItems> mCategoryItemList;

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
        return view;
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.adapter_category_items_list, parent, false);
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
            inputLanguage = (TextView) view.findViewById(R.id.AC_TV_category_items);
        }

        void setLanguageDatas(final int positions) {
            inputLanguage.setText(getItem(positions).getSection_title());
            inputLanguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLearningAct(getItem(positions));
                }
            });
        }
    }

    private void showLearningAct(CategoryItems sections) {
        Intent callCatAct = new Intent(mContext, LearningAct.class);
        callCatAct.putExtra(Config.SECTION_ID, sections.getSection_title());
        mContext.startActivity(callCatAct);

    }
}
