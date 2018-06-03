package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.administrator.timenote.R;

import java.util.ArrayList;

public class task_select_Adapter extends ArrayAdapter<String> implements View.OnClickListener {
    private int resourceId;
    private ListAdapter.InnerItemOnclickListener mListener;

    public task_select_Adapter (Context context, int resource, ArrayList<String> objects) {
        super(context, resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ListAdapter.ViewHolder holder=null;
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null,false);
        holder=new ListAdapter.ViewHolder();
        holder.mButton= (Button) view.findViewById(R.id.task_select_button_1);
        holder.mButton.setText(getItem(position));
        holder.mButton.setOnClickListener(this);
        holder.mButton.setTag(position);
        return view;

    }

    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(ListAdapter.InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
    static class ViewHolder{
        Button mButton;
    }
}