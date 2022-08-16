package com.example.youtubecleaner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList;

    public ListViewAdapter(ArrayList<ListViewItem> itemList){
        if(itemList == null){
            listViewItemList = new ArrayList<ListViewItem>();
        } else {
            listViewItemList = itemList;
        }
    }

    // Adapter에 사용되는 데이터의 개수 리턴
   public int getCount() {
        return listViewItemList.size();
    }

    // 지정한 위치(i)에 있는 데이터 리턴
    public Object getItem(int i) {
        return listViewItemList.get(i);
    }

    // 지정한 위치(i)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    public long getItemId(int i) {
        return i;
    }

    // 아이템 데이터 추가를 위한 함수
    public void addItem(String userID, String comment, float score){
        ListViewItem item = new ListViewItem();

        //Float fScore = Float.valueOf(score);

        item.setItemUserID(userID);
        item.setItemComment(comment);
        item.setItemScore(score);

        listViewItemList.add(item);
    }

    // i에 위치한 데이터를 화면에 출력하는데 사용될 view를 리턴
    public View getView(int i, View view, ViewGroup viewGroup) {
        int pos = i;
        final Context context = viewGroup.getContext();

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.content,viewGroup, false);
        }

        TextView tvItemUserID = (TextView) view.findViewById(R.id.tvUserID);
        TextView tvItemComment = (TextView) view.findViewById(R.id.tvComment);
        TextView tvItemScore = (TextView) view.findViewById(R.id.tvScore);

        // LIstViewItemList에서 i에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(pos);

        tvItemUserID.setText(listViewItem.getItemUserID());
        tvItemComment.setText(listViewItem.getItemComment());
        tvItemScore.setText(Float.toString(listViewItem.getItemScore())+"%");

        return view;
    }

    public ArrayList<ListViewItem> getItemList(){
        return listViewItemList;
    }
}
