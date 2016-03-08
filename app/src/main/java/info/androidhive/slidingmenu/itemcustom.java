package info.androidhive.slidingmenu;
public class itemcustom {
	String code = null;
	 String name = null;
	 String continent = null;
	 String region = null;
	 String LPRATE = null;	
	 String BPRATE = null;	
	 
	 public itemcustom(String code, String name, String continent, String region,String LPRATE ) {
	  super();
	  this.code = code;
	  this.name = name;
	  this.continent = continent;
	  this.region = region;
	  this.LPRATE = LPRATE;
	  this.BPRATE = BPRATE; 
	  
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
	 public String getContinent() {
	  return continent;
	 }
	 public void setContinent(String continent) {
	  this.continent = continent;
	 }
	 
	 
	 public String getregion() {
		  return region;
		 }
		 public void setregion(String region) {
		  this.region = region;
		 }
	 
	 public String getlprate() {
	  return LPRATE;
	 }
	 public void setlprate(String LPRATE) {
	  this.LPRATE = LPRATE;
	 }
	
	 public String getbprate() {
		  return BPRATE;
		 }
		 public void setbprate(String BPRATE) {
		  this.BPRATE = BPRATE;
		 }
	
	 @Override
	 public String toString() {
	  return  code + " " + name + " "
	    + continent + " " + region;
	 }
	  
}
