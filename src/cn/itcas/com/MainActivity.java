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
		// startActivity(intent)�ķ���ֵΪ�գ������Ҫ���¿���activity�õ�����(�н��)��Ҫ������ķ���
		startActivityForResult(intent, 1);
	}

	// �����ǿ�����Activityҳ��رյ�ʱ����������͵���
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
		// ���ŷ���
		String number = et_number.getText().toString().trim();
		String body = et_body.getText().toString().trim();
		// ������SmsManagerֱ�ӷ�����Ϣ����getDefault����ʵ����SmsManager
		SmsManager smsManager = SmsManager.getDefault();
		//SmsManagerһ����෢��140��Ӣ����ĸ����70������
		ArrayList<String> list = smsManager.divideMessage(body);
		for(String div :list){
			smsManager.sendTextMessage(number, null, div, null, null);
		}
		// scAddress������Ӫ�̷������ĵĺ���
		//smsManager.sendTextMessage(number, null, body, null, null);
	}
}
