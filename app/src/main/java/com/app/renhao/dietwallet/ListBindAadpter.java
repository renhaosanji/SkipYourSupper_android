package com.app.renhao.dietwallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2016-04-29.
 */
public class ListBindAadpter extends RealmBaseAdapter<DataPackage> {

    LayoutInflater inflater;
    int layout;
    Context context;
    RealmResults<DataPackage> realmResults;

    public ListBindAadpter(Context context, RealmResults realmResults, boolean automaticUpdate, int layout,LayoutInflater inflater) {
        super(context, realmResults, automaticUpdate);
        this.realmResults = realmResults;
        this.layout = layout;
        this.inflater =inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(layout,parent,false);
        }
        TextView money = (TextView) convertView.findViewById(R.id.money);
        TextView calorie = (TextView) convertView.findViewById(R.id.calorie);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView foodname = (TextView) convertView.findViewById(R.id.foodname);
        DBManager db= DBManager.getInstance(context);
        money.setText(realmResults.get(position).getMoney()+"");
        calorie.setText(realmResults.get(position).getCalorie()+"");
        date.setText("3");
        foodname.setText("4");

        return convertView;
    }
}
