package com.pronix.questionsapp.render;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pronix.questionsapp.R;

import java.util.ArrayList;

public class CommonSpinnerAdapter extends BaseAdapter {

    Context context;
    ArrayList list;

    public CommonSpinnerAdapter(Context context, ArrayList list)
    {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.spinner_item, viewGroup, false);
        TextView tv_Item = v.findViewById(R.id.tv_Item);

        Object object = list.get(i);
        if(object instanceof String)
        {
            tv_Item.setText(object.toString());
        }

        return v;
    }
}
