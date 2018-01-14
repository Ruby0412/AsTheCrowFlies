import java.math.*;
public class City {

	
	//data field
	private String city_name;
	private String city_state;
	private Double latitude;
	private Double longitude;
	
	//Constructor
	public City(Double Latitude,Double Longitude) {
		this.latitude=Latitude;
		this.longitude=Longitude;
	}
	
	//constructor
	public City 
	(String C_name, String C_state, Double latitue, Double longitude)throws NullPointerException
	{
		if (C_name==null||C_state==null){
			throw new NullPointerException();
		}
		this.city_name = C_name;
		this.city_state = C_state;
		this.latitude = latitue;
		this.longitude = longitude;
		
	}
	
	// getters
	public String getName () 
	{
		return city_name;
	}
	
	public String getState () 
	{
		return city_state;
	}
	
	public Double getLatitude() 
	{
		return latitude;
	}
	
	public Double getLongitude() 
	{
		return longitude;
	}
	
	//calculate the distance based on Haversine formula
	public int calculate (City destinCity) 
	{
	final int R=6371;
	final int CONVERTCONSTANT = 1000;
	double a = Math.pow((Math.sin((destinCity.latitude-latitude)/2)),2) + 
			  Math.cos(latitude) *Math.cos(destinCity.latitude)*Math.pow(Math.sin((destinCity.latitude-latitude)/2), 2);
	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	int distance= (int) (R*c)* CONVERTCONSTANT;
		return distance;
	}
	
   //accessor 
	public String getCityName(){
		return city_name;
	}
	
	//toString method 
	@Override 
   public String toString() {
	   
	   return city_state.toUpperCase()+","+city_name.toUpperCase()+","
	   +latitude+","+longitude;
   }
	
	//this method used to determine whether the city names are the same
   public boolean equals (String city){
	if( this.city_name.equals(city)){
		return true;
	}
	   return false;
  }
	
}//end of class