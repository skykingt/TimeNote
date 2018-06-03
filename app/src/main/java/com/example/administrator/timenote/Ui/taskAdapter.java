package com.example.administrator.timenote.Ui;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.timenote.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class taskAdapter extends ArrayAdapter<task> {
    private int resourceId;
    public taskAdapter(Context context, int resource, List<task> objects) {
        super(context, resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        task t=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null,false);
        View view_line=convertView.findViewById(R.id.view_color_1);
        TextView taskname=convertView.findViewById(R.id.taskname);
        TextView date=convertView.findViewById(R.id.data_1);
        TextView taskdes=convertView.findViewById(R.id.taskdes);
        TextView taskp=convertView.findViewById(R.id.taskp);
        ImageView ImageId=convertView.findViewById(R.id.image_level);

        taskname.setText(t.getTaskname());
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:SS");
        date.setText(format.format( t.getDate_1()));
        taskdes.setText(t.getTaskdes());
        taskp.setText(t.getTaskp());
        ImageId.setImageResource(t.getImageId());
        return view;
    }
}