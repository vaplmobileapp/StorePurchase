package info.androidhive.slidingmenu;
public class Pocustom {
	String code = null;
	 String name = null;
	 String continent = null;
	 String region = null;
	 String net = null;
	 String capex = null;
	 String branch = null;
		 
	 
	 public Pocustom(String code, String name, String continent, String region,String net,String capex,String branch) {
	  super();
	  this.code = code;
	  this.name = name;
	  this.continent = continent;
	  this.region = region;	 
	  this.net = net;
	  this.capex = capex;
	  this.branch = branch;
	  
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
	 public String getRegion() {
	  return region;
	 }
	 public void setRegion(String region) {
	  this.region = region;
	 }
	 public void setcapex(String capex) {
		  this.capex = capex;
		 }
	 
	 public void setbranch(String branch) {
		  this.branch = branch;
		 }
	 
	 public String getbranch() {
		  return branch;
		 }	 
	
	 public String getcapex() {
		  return capex;
		 }
	 
	 public String getnet() {
		  return net;
		 }
		 public void setnet(String net) {
		  this.net = net;
		 }
	
	 @Override
	 public String toString() {
	  return  code + " " + name + " "
	    + continent + " " + region;
	 }
	  
}
