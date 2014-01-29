package com.logan.scorer.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logan.scorer.R;

public class PlayerAdapter extends BaseAdapter{
	private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public PlayerAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
	public int getCount() {
		return data.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.player, null);
 
        TextView name = (TextView)view.findViewById(R.id.name); // Player Name
 
        HashMap<String, String> player = new HashMap<String, String>();
        player = data.get(position);
        name.setText(player.get("name"));
        return view;
	}
}
