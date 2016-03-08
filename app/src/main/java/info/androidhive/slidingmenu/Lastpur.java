package info.androidhive.slidingmenu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Lastpur extends Activity {
	String item_id;
	String Branch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lastpur);		
		item_id=(getIntent().getExtras().getString("pur_item"));
		Branch=(getIntent().getExtras().getString("Branch"));
		setTitle(item_id);	
		Toast.makeText(getApplicationContext(), item_id, Toast.LENGTH_SHORT).show();
		StrictMode.enableDefaults();
		int id_click=0;
		String result = null;
	   InputStream webs = null;
			try
			{				
				HttpClient httpclient = new DefaultHttpClient();
				String surl = "http://223.30.82.100/Purchase/lastpur.php?var1="+item_id+"&var2="+Branch;
				HttpPost httppost = new HttpPost(surl);			
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				webs = entity.getContent();	
				
			}
			catch (Exception e)
			{
	
		   Toast.makeText(getApplicationContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
		   
		   
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
		    	   
		    	  
		    	    TableLayout tableLayout = new TableLayout(getApplicationContext());
		    	    android.widget.TableRow.LayoutParams lp = new TableRow.LayoutParams(0,android.widget.TableRow.LayoutParams.MATCH_PARENT,1f);
		    	    TableRow tableRow;
		    	    
		    	    TextView textView;   	    
		    
		    	    JSONArray jArray = new JSONArray(result);
		    	   for (int j = 0; j < jArray.length(); j++) {
		    		   tableRow = new TableRow(getApplicationContext());    		  
		    		   JSONObject Json = jArray.getJSONObject(j);    		 
		    				   for (int k = 0; k < 5; k++) {    					   
		    	    	         textView = new TextView(getApplicationContext());
		    	    	        textView.setId(id_click);		    	    	      
		    	    	        textView.setLayoutParams(lp);
		    	    	         if (k==0)											
		    	    	         {
		    	    	        	 if(j==0)
		    	    	        	 {
		    	    	        		 textView.setText(Json.getString("DOCID"));		    	    	        		 
				    	    	          textView.setTextColor(Color.BLACK );
				    	    	          textView.setTextSize(16);
				    	    	          textView.setTypeface(Typeface.DEFAULT_BOLD);
				    	    	          textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	        	 else {									
		    	    	          textView.setText(Json.getString("DOCID"));
		    	    	          
		    	    	          textView.setTextColor(Color.BLACK );
		    	    	          textView.setTextSize(16);
		    	    	          textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 } 
		    	    	         }
		    	    	        else if(k==1) {
		    	    	        	if(j==0)
		    	    	        	 {
		    	    	        		 textView.setText(Json.getString("DOCDATE"));
				    	    	          textView.setTextColor(Color.BLACK );
				    	    	          textView.setTextSize(16);
				    	    	          textView.setTypeface(Typeface.DEFAULT_BOLD);
				    	    	          textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	        	 else {	
		    	    	        	 textView.setText(Json.getString("DOCDATE"));		    	    	       
		    	    	        	 textView.setTextColor(Color.BLACK  );		    	    	        	// 
		    	    	        	 textView.setTextSize(16);
		    	    	        	 textView.setTypeface(Typeface.DEFAULT_BOLD);
		    	    	        	 textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	        	 if(j==0 || j==jArray.length()-1)
		       	    	          {    
		    	    	        		 textView.setTextColor(Color.BLACK );;
		       	    	          
		       	    	          }
								}
		    	    	         else if(k==2) {
		    	    	        	 if(j==0)
		    	    	        	 {
		    	    	        		 textView.setText(Json.getString("POQTY"));
				    	    	          textView.setTextColor(Color.BLACK );
				    	    	          textView.setTextSize(16);
				    	    	          textView.setTypeface(Typeface.DEFAULT_BOLD);
				    	    	          textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	        	 else {	
		    	    	        	 textView.setText(Json.getString("POQTY"));
		    	    	        	 textView.setTextColor(Color.BLACK );
		    	    	        	 textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	         }
		    	    	         else if(k==3) {
		    	    	        	 if(j==0)
		    	    	        	 {
		    	    	        		 textView.setText(Json.getString("RATE"));
				    	    	          textView.setTextColor(Color.BLACK );
				    	    	          textView.setTextSize(16);
				    	    	          textView.setTypeface(Typeface.DEFAULT_BOLD);
				    	    	          textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	        	 else {	
		    	    	        	 textView.setText(Json.getString("RATE"));
		    	    	        	 textView.setTextColor(Color.BLACK );
		    	    	        	 textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	         }
		    	    	         else  if(k==4) {
		    	    	        	 if(j==0)
		    	    	        	 {
		    	    	        		 textView.setText(Json.getString("AMOUNT"));
				    	    	          textView.setTextColor(Color.BLACK );
				    	    	          textView.setTextSize(16);
				    	    	          textView.setTypeface(Typeface.DEFAULT_BOLD);
				    	    	          textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	        	 else {										
									 textView.setText(Json.getString("AMOUNT"));
		    	    	        	 textView.setTextColor(Color.BLACK );
		    	    	        	 textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		    	    	        	 }
		    	    	         }
		    	    	        
		    	    	         
		    	    	         //   textView.setPadding(8, 8, 8, 8);
		    	    	            textView.setTextColor(Color.parseColor("#FFFFFF"));		    	    	            
		    	    	            
		    	    	             tableRow.addView(textView);  	                	             
		    	    	                     	    	             }			    				   
		    				   
		    				   tableLayout.addView(tableRow, new TableLayout.LayoutParams(
		    		                    LayoutParams.MATCH_PARENT,
		    		                    LayoutParams.MATCH_PARENT)); 
		    				   tableLayout.setBackgroundColor(Color.parseColor("#696969"));
		    				   id_click++;
		    		    	    	    }		    	
		    	   ScrollView scroll = new ScrollView(getApplicationContext());
		    	   scroll.setBackgroundColor(Color.parseColor("#696969"));
		    	    scroll.addView(tableLayout);		    	   
		    	   setContentView(scroll);		    	   
		    	    	   
			} catch (Exception e) {
		 
				
				
			}
}

public boolean onCreateOptionsMenu(Menu menu) {  
		
        getMenuInflater().inflate(R.menu.lprate, menu);        
        return true;	
	}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case R.id.mont:
		Intent inte = new Intent(this,Month.class);
		inte.putExtra("pur_item", item_id);
		inte.putExtra("Branch", Branch);
		startActivity(inte);
		break;

	default:
		break;
	}

	return super.onOptionsItemSelected(item);
}
}
