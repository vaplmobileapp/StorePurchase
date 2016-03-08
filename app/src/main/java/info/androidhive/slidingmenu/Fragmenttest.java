package info.androidhive.slidingmenu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.R.raw;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Fragmenttest extends Fragment {

	// String[] capex = new  String[1000];
	ArrayList<itemcustom> countryList = new ArrayList<itemcustom>();
	MyCustomAdapter dataAdapter = null;
	String myInt;
	String brname;
	String username;
	InputStream webs = null;
	public Fragmenttest(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		countryList.clear();
		View rootView = inflater.inflate(R.layout.fragment_ex, container, false);

		Bundle bundle = this.getArguments();
		myInt = bundle.getString("position");
		brname = bundle.getString("branch");
		username = bundle.getString("uname");

		getActivity().setTitle(myInt);
		// Toast.makeText(getActivity().getApplicationContext(), myInt, Toast.LENGTH_SHORT).show();

		StrictMode.enableDefaults();
		String result = null;

		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			String surl = "http://223.30.82.100/StorePurchase/itemdet.php?var1="+myInt;
			HttpPost httppost = new HttpPost(surl);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			webs = entity.getContent();
		}
		catch (Exception e)
		{
			Toast.makeText(getActivity().getApplicationContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
		}
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(webs,"iso.8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line=br.readLine())!=null)
			{
				sb.append(line+"/n");
			}

			webs.close();

			result=sb.toString() ;
		}
		catch(Exception e)
		{

		}

		try {

			JSONArray jArray = new JSONArray(result);
			String[] ITEMID = new  String[jArray.length()];
			String[] POQTY = new  String[jArray.length()];
			String[] PORATE = new  String[jArray.length()];
			String[] POAMOUNT = new  String[jArray.length()];
			String[] LPRATE = new  String[jArray.length()];
			String[] BRRATE = new  String[jArray.length()];


			for (int j = 0; j < jArray.length(); j++) {
				JSONObject Json = jArray.getJSONObject(j);
				ITEMID[j] = Json.getString("ITEMID");
				POQTY[j] = Json.getString("POQTY");
				PORATE[j] = Json.getString("PORATE");
				POAMOUNT[j]=Json.getString("POAMOUNT");
				LPRATE[j]=Json.getString("LPRATE");
				BRRATE[j]=Json.getString("BRRATE");
				// capex[j]=Json.getString("Capex");

				itemcustom country = new itemcustom(ITEMID[j],POQTY[j],PORATE[j],POAMOUNT[j],LPRATE[j] );
				countryList.add(country);
			}

		} catch (Exception e) {
		}
		dataAdapter = new MyCustomAdapter(getActivity(),
				R.layout.custom_item, countryList);
		ListView listView = (ListView) rootView.findViewById(R.id.listView1);
		listView.setAdapter(dataAdapter);
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				itemcustom country = (itemcustom) parent.getItemAtPosition(position);
				//Toast.makeText(getActivity().getApplicationContext(),
				//country.getCode(), Toast.LENGTH_SHORT).show();

				String item = country.getCode();

				item=item.replace(" ", "");

				Fragment duedateFrag = new Lastpurchase();
				android.app.FragmentTransaction ft  = getFragmentManager().beginTransaction();
				ft.replace(R.id.frame_container, duedateFrag);
				ft.addToBackStack(null);
				ft.commit();
				Bundle bundle = new Bundle();
				bundle.putString("position", item);
				bundle.putString("branch", brname);
				duedateFrag.setArguments(bundle);
			}

		});

		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.approval, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item1 :

				try
				{
					HttpClient httpclient = new DefaultHttpClient();
					brname=brname.replace(" ", "" );
					String surl = "http://223.30.82.100/StorePurchase/App.php?var1="+myInt+"&var2="+brname+"&var3="+username;
					HttpPost httppost = new HttpPost(surl);
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					Toast.makeText(getActivity().getApplicationContext(), "Order Approved", Toast.LENGTH_SHORT).show();

					//getActivity().getFragmentManager().beginTransaction().remove(this).commit();

					//Fragment duedateFrag = new HomeFragment();
					//android.app.FragmentTransaction ft  = getFragmentManager().beginTransaction();
					//ft.replace(R.id.frame_container, duedateFrag);
					//ft.addToBackStack(null);
					//ft.commit();
					//Bundle bundle = new Bundle();
					//bundle.putString("position", PO_DOCID);
					//bundle.putString("branch", brnam);
					//bundle.putString("uname", username);
					//duedateFrag.setArguments(bundle);

					//getActivity().findViewById(android.R.id.list ).invalidate();


				}
				catch (Exception e) {

				}
				break;


			default:
				break;
		}

		return true;
	}


	private class MyCustomAdapter extends ArrayAdapter<itemcustom> {

		private ArrayList<itemcustom> originalList;
		private ArrayList<itemcustom> countryList;
		private CountryFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
							   ArrayList<itemcustom> countryList) {
			super(context, textViewResourceId, countryList);
			this.countryList = new ArrayList<itemcustom>();
			this.countryList.addAll(countryList);
			this.originalList = new ArrayList<itemcustom>();
			this.originalList.addAll(countryList);
		}

		@Override
		public Filter getFilter() {
			if (filter == null){
				filter  = new CountryFilter();
			}
			return filter;
		}


		private class ViewHolder {
			TextView code;
			TextView name;
			TextView continent;
			TextView region;
			TextView nkl;
			TextView brrate;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));
			if (convertView == null) {

				LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.custom_item, null);

				holder = new ViewHolder();
				holder.code = (TextView) convertView.findViewById(R.id.code);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.continent = (TextView) convertView.findViewById(R.id.continent);
				holder.region = (TextView) convertView.findViewById(R.id.region);
				holder.nkl = (TextView) convertView.findViewById(R.id.nkl);
				holder.brrate = (TextView) convertView.findViewById(R.id.textView1);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			itemcustom country = countryList.get(position);
			holder.code.setText(country.getCode());
			holder.name.setText(country.getName());
			holder.continent.setText(country.getContinent());
			holder.region.setText(country.getregion());
			holder.nkl.setText(country.getlprate());
			//  holder.brrate.setText(country.getbrate());

			return convertView;

		}

		private class CountryFilter extends Filter
		{

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				constraint = constraint.toString().toLowerCase();
				FilterResults result = new FilterResults();
				if(constraint != null && constraint.toString().length() > 0)
				{
					ArrayList<itemcustom> filteredItems = new ArrayList<itemcustom>();

					for(int i = 0, l = originalList.size(); i < l; i++)
					{
						itemcustom country = originalList.get(i);
						if(country.toString().toLowerCase().contains(constraint))
							filteredItems.add(country);
					}
					result.count = filteredItems.size();
					result.values = filteredItems;
				}
				else
				{
					synchronized(this)
					{
						result.values = originalList;
						result.count = originalList.size();
					}
				}
				return result;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
										  FilterResults results) {

				countryList = (ArrayList<itemcustom>)results.values;
				notifyDataSetChanged();
				clear();
				for(int i = 0, l = countryList.size(); i < l; i++)
					add(countryList.get(i));
				notifyDataSetInvalidated();
			}
		}


	}



}
