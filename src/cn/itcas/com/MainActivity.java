package cn.itcas.com;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private EditText et_number;
	private EditText et_body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_number = (EditText) findViewById(R.id.et_number);
		et_body = (EditText) findViewById(R.id.et_body);

	}

	public void add(View v) {
		Intent intent = new Intent(this, ContactsActivity.class);
		// startActivity(intent);
		// startActivity(intent)的返回值为空，如果需要从新开的activity得到数据(有结果)需要用下面的方法
		startActivityForResult(intent, 1);
	}

	// 当我们开启的Activity页面关闭的时候这个方法就调用
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
		// TODO Auto-generated method stub
		if (resultCode == 10) {
			String phone = Data.getStringExtra("phone");
			et_number.setText(phone);
		} else if (resultCode == 20) {
			String body = Data.getStringExtra("template");
			et_body.setText(body);

		}

		super.onActivityResult(requestCode, resultCode, Data);
	}

	public void insert(View v) {
		Intent intent = new Intent(this, Templatesms.class);
		//
		startActivityForResult(intent, 2);

	}

	public void send(View v) {
		// 短信发送
		String number = et_number.getText().toString().trim();
		String body = et_body.getText().toString().trim();
		// 可以用SmsManager直接发送消息，用getDefault方法实例化SmsManager
		SmsManager smsManager = SmsManager.getDefault();
		//SmsManager一次最多发送140个英文字母或者70个汉字
		ArrayList<String> list = smsManager.divideMessage(body);
		for(String div :list){
			smsManager.sendTextMessage(number, null, div, null, null);
		}
		// scAddress代表运营商服务中心的号码
		//smsManager.sendTextMessage(number, null, body, null, null);
	}
}
