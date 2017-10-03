package co.in.dreamguys.littlekids.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.in.dreamguys.littlekids.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 11-09-2017.
 */

public class AdapterCategoryList extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<String> CategoryList = new ArrayList<>();


    public AdapterCategoryList(Context mContext, ArrayList<String> CategoryList) {
        this.mContext = mContext;
        layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.CategoryList = CategoryList;
    }

    @Override
    public int getCount() {
        return CategoryList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (view == null) {
            mHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.adapter_category_list, null);
            mHolder.inputCategory = (TextView) view.findViewById(R.id.AC_TV_category);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }

        mHolder.inputCategory.setText(CategoryList.get(i).toString());

        return view;
    }

    class ViewHolder {
        TextView inputCategory;
    }

}
