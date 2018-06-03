package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.administrator.timenote.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<String> implements OnClickListener {
    private int resourceId;
    private Callback mCallback;
    public ListAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder=null;
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null,false);
        holder=new ViewHolder();
        Button mButton= (Button) view.findViewById(R.id.list_select_button);
        mButton.setText(getItem(position));
        mButton.setOnClickListener(this);
        mButton.setTag(position);
        return view;

        }

    public interface Callback {
        public void click(View v);
    }
    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }
    static class ViewHolder{
        Button mButton;
    }
}