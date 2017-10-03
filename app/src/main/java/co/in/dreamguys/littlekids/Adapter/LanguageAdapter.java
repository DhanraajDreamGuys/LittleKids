package co.in.dreamguys.littlekids.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import co.in.dreamguys.littlekids.Category_Activity;
import co.in.dreamguys.littlekids.Helper.Config;
import co.in.dreamguys.littlekids.R;
import co.in.dreamguys.littlekids.RealmModel.LanguageInfo;
import io.realm.RealmResults;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 11-09-2017.
 */

public class LanguageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private RealmResults<LanguageInfo> mLanguageList;

    public LanguageAdapter(Context mContext, RealmResults<LanguageInfo> mLanguageList) {
        this.mContext = mContext;
        this.mLanguageList = mLanguageList;
        layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mLanguageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_category_list, null);
            mHolder.inputLanguage = (TextView) convertView.findViewById(R.id.AC_TV_category);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.inputLanguage.setText(mLanguageList.get(position).getLang());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.language = mLanguageList.get(position).getLang();
                Intent Category_activity = new Intent(mContext, Category_Activity.class);
                mContext.startActivity(Category_activity);
                ((Activity) mContext).finish();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView inputLanguage;

    }
}
