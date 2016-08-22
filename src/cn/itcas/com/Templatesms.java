package cn.itcas.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Templatesms extends Activity {
	String objects[]={"我很忙","我去吃饭了","我在开会","我睡觉了","我在工作"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_template);
		ListView lv_sms = (ListView) findViewById(R.id.lv_sms);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.templateitem, objects);
		lv_sms.setAdapter(adapter);
		lv_sms.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//取数据
				String content = objects[position];
				Intent intent =new Intent();
				intent.putExtra("template", content);
				//返回数据
				setResult(20, intent);
				
				finish();
			}
		});
	}

}
