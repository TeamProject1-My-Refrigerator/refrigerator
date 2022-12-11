package com.cookandroid.refrigerator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MoreListAdapter extends BaseAdapter{
    ArrayList<MoreListItem> items = new ArrayList<MoreListItem>();
    Context context;



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        MoreListItem morelistItem = items.get(position);

        if(ConvertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ConvertView = inflater.inflate(R.layout.morelist_recipy, viewGroup, false);

        }

        ImageView imageView = ConvertView.findViewById(R.id.morelist_item_image);
        TextView textView = ConvertView.findViewById(R.id.morelist_item_name);
        ImageButton imageButton = ConvertView.findViewById(R.id.morelist_item_button);

        imageView.setImageResource(morelistItem.getImage());
        textView.setText(morelistItem.getName()  +  "\n유통기한 : "  +morelistItem.getExdate()+  "\n입고날짜 : "+morelistItem.getIndate() );
        imageButton.setImageResource(R.drawable.deletebutton);

        imageButton.setOnClickListener(items.get(position).onClickListener);

        ConvertView.setTag(""+position);
        return ConvertView;
    }
    public void addItem(MoreListItem item){
        items.add(item);
    }
    //초기화
    public void clear(){ items.clear();}


}