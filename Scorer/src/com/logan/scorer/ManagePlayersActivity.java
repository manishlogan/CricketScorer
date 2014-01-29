package com.logan.scorer;

import java.util.ArrayList;
import java.util.HashMap;

import com.logan.scorer.R.id;
import com.logan.scorer.adapter.PlayerAdapter;
import com.logan.scorer.dao.DbHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ManagePlayersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_players);
		loadPlayers();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_players, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addPlayer:
			Intent nextActivity = new Intent(this, AddPlayerActivity.class);
			startActivityForResult(nextActivity, 200);
			break;
		default:
			break;
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == 200){
			loadPlayers();
			Toast.makeText(this, "Player added successfully", Toast.LENGTH_LONG).show();
		}
	}
	
	private void loadPlayers(){
		ManagePlayersActivity activity = this;
		DbHelper helper = new DbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		String cols[] = {"_id","name"};
		Cursor c = db.query(Constants.PLAYER_TABLE, cols, null, null, null, null, "name");
		ArrayList<HashMap<String, String>> players = new ArrayList<HashMap<String,String>>();
		if(c.moveToFirst()){
			do{
				HashMap<String, String> player = new HashMap<String, String>();
				player.put("id",c.getString(0));
				player.put("name", c.getString(1));
				players.add(player);
			}while(c.moveToNext());
		}
		
		PlayerAdapter adapter = new PlayerAdapter(this, players);
		ListView playersList = (ListView)findViewById(id.playersList);
		playersList.setAdapter(adapter);	
		
		playersList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long arg3) {
				adapter.getItemAtPosition(position);
			}
			
		});
	}
}
