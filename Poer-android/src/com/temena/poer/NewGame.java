package com.temena.poer;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.drawable.*;

public class NewGame extends Activity {
	EditText name;
	Button go_button;
	SharedPreferences prefs;
	SharedPreferences.Editor edit;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(0xFF179200));
		name = (EditText) findViewById(R.id.name);
		go_button = (Button) findViewById(R.id.go_button);
		go_button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					String temp = name.getText().toString();
					if(temp == null || temp == "" || temp.isEmpty()) {
						Toast.makeText(getApplicationContext(), "Please enter a name...", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "Nice to meet you "+temp+"!", Toast.LENGTH_SHORT).show();
						prefs = getSharedPreferences("com.temena.poer.Settings", MODE_PRIVATE);
						edit = prefs.edit();
						edit.putString("NAME", temp);
						edit.commit();
						try {
						startActivity(new Intent(NewGame.this, StartGameActivity.class));
						} catch(Exception e) {
							Toast.makeText(getApplicationContext(), Log.getStackTraceString(e), Toast.LENGTH_LONG).show();
						}
					}
				}
				
			
		});
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		edit = null;
		prefs = null;
	}
}
