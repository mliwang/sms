package cn.itcas.com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsActivity extends Activity {
	private List<Person> persons;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		ListView lv = (ListView) findViewById(R.id.lv);
		persons = new ArrayList<Person>();
		for (int i = 0; i < 20; i++) {
			Person person = new Person();
			person.setName("����"+i);
			person.setPhone("327467"+i);
			persons.add(person);
			
		}
		//��������������
		lv.setAdapter(new adapter());
		//����listview�ĵ���¼�
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//��ȡ������Ŀ�е�����
				String phone = persons.get(position).getPhone();
				//��phone����main
				Intent intent = new Intent();
				intent.putExtra("phone", phone);
				//�ѽ�����ظ�������
				setResult(10, intent);
				
				finish();
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	private class adapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return persons.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if(convertView==null){
				view=View.inflate(getApplicationContext(), R.layout.contactitem, null);
			}
			else {
				view=convertView;
			}
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
			tv_name.setText(persons.get(position).getName());
			tv_phone.setText(persons.get(position).getPhone());
			
			return view;
		}
	}
}
