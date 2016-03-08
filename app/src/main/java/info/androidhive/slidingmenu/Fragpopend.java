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

import android.app.Fragment;
import android.content.Context;
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





public class Fragpopend extends Fragment {
	

	 ArrayList<Pend_Custom> countryList = new ArrayList<Pend_Custom>();
	 MyCustomAdapter dataAdapter = null;
	 String myInt;
	
	public Fragpopend(){}
	
	@Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
		countryList.clear();
       View rootView = inflater.inflate(R.layout.frag_penpoitem, container, false);
       
       
       Bundle bundle = this.getArguments();
        myInt = bundle.getString("position");
        getActivity().setTitle(myInt);	
      
			StrictMode.enableDefaults();		
			String result = null;
		   InputStream webs = null;
				try
				{				
					HttpClient httpclient = new DefaultHttpClient();
					String surl = "http://223.30.82.100/INTL/Itempending.php?var1="+myInt;
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
			 	   String[] PENDQTY = new  String[jArray.length()];			 	 
			 	
			 	 
			 	   for (int j = 0; j < jArray.length(); j++) {			 		   		  
			 		   JSONObject Json = jArray.getJSONObject(j);			 		
			 		  ITEMID[j] = Json.getString("ITEMID");
			 		 PENDQTY[j] = Json.getString("PENDQTY");
			 		 		
			 	
			 		   Pend_Custom country = new Pend_Custom(ITEMID[j],PENDQTY[j]);
			 		   countryList.add(country);
			 	   }		 
					    	   
				} catch (Exception e) {
			 	}
			//	setTitle(Ord_id);  	
				dataAdapter = new MyCustomAdapter(getActivity(),
						R.layout.po_custom, countryList);
						ListView listView = (ListView) rootView.findViewById(R.id.listView1);
						listView.setAdapter(dataAdapter);
						listView.setTextFilterEnabled(true);
						
						listView.setOnItemClickListener(new OnItemClickListener() {
						public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
						Pend_Custom country = (Pend_Custom) parent.getItemAtPosition(position);
						Toast.makeText(getActivity().getApplicationContext(),
						country.getCode(), Toast.LENGTH_SHORT).show();	
					
						}
						
						
						
						});
		 
       return rootView;
   }
	
	private class MyCustomAdapter extends ArrayAdapter<Pend_Custom> {
		 
		  private ArrayList<Pend_Custom> originalList;
		  private ArrayList<Pend_Custom> countryList;
			 
		  public MyCustomAdapter(Context context, int textViewResourceId,
		    ArrayList<Pend_Custom> countryList) {
		   super(context, textViewResourceId, countryList);
		   this.countryList = new ArrayList<Pend_Custom>();
		   this.countryList.addAll(countryList);
		   this.originalList = new ArrayList<Pend_Custom>();
		   this.originalList.addAll(countryList);
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
		   convertView = vi.inflate(R.layout.pendingcustom, null);
		 
		   holder = new ViewHolder();
		   holder.code = (TextView) convertView.findViewById(R.id.pen_party);
		   holder.name = (TextView) convertView.findViewById(R.id.Pen_Qty);
		   	   
		 
		   convertView.setTag(holder);
		 
		   } else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   Pend_Custom country = countryList.get(position);
		   holder.code.setText(country.getCode());
		   holder.name.setText(country.getName());		 
		 
		   return convertView;
		 
		  }
		 
		 
		 
		 
		 }
	

	
}
