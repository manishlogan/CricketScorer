package com.logan.scorer;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.logan.scorer.R.id;
import com.logan.scorer.dao.DbHelper;

public class AddPlayerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_player, menu);
		return true;
	}
	
	public void add(View view){
		DbHelper helper = new DbHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		TextView nameView = (TextView)findViewById(id.playerName);
		values.put("name", nameView.getText().toString());
		long id = db.insert(Constants.PLAYER_TABLE, null, values);
		db.close();
		setResult(200);
		finish();
	}

}
