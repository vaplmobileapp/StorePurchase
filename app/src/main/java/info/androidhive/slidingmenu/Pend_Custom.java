package info.androidhive.slidingmenu;

import android.app.Activity;

public class Pend_Custom extends Activity {
	

	String code = null;
	 String name = null;
	
		 
	 
	 public Pend_Custom(String code, String name) {
	  super();
	  this.code = code;
	  this.name = name;
	  
	  
	 }
	  
	 public String getCode() {
	  return code;
	 }
	 public void setCode(String code) {
	  this.code = code;
	 }
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	
	
		
	 
	
	 @Override
	 public String toString() {
	  return  code + " " + name;
	  
	 }
	  


}
