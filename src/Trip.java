import java.util.ArrayList;




public class Trip extends AsTheCrowFlies {

	private static ArrayList <City> visited = new ArrayList<City>();
    int totaldis;
	//datafield
	
	
	public  Trip () 
	{
		findShortestPath();
		this.totaldis=this.totaldis();
	}
	public void findShortestPath(){
		// find the next city to go to
		
		int mindis= tripCities.get(0).calculate(tripCities.get(1));
		visited.add(tripCities.get(0));
		 tripCities.remove(0);
		while (tripCities.size()>0){
			
			int next=0;
			for(int i=0;i<tripCities.size();i++){
				int tempdis = visited.get(visited.size()-1).calculate(tripCities.get(i));
			// see if tempdis less than current mindis
				if (tempdis<mindis){
					next=i;
					mindis=tempdis;
				}
			}
			//remove the next city from the tripCities to Visited arraylist
			visited.add(tripCities.get(next));
			tripCities.remove(next);	
			//Reset the next trip temp mindist as the distance between last index 
			//element in visited and first element in current tripCities.  
			mindis=visited.get(visited.size()-1).calculate(tripCities.get(0));
		}
		
		
	}
	//accessor
	public int gettotaldis(){
		return totaldis;
	}
	
	public int totaldis(){
		int total=0;
		for (int i=0;i<visited.size()-1;i++){
		 total+=visited.get(i).calculate(visited.get(i+1));
		}
		//calculate the distance for going back to origin city.
		total+=visited.get(visited.size()-1).calculate(visited.get(0));
		return total;
	}
	
	
	@Override 
	public String toString(){
		final double coefficientNum= 0.000621371;
		int convertedDis=(int)(totaldis*coefficientNum);
		String present ="There are " + visited.size()+" cities in this trip.\n";
	for(int i=0;i<visited.size()-1;i++){
		present +=visited.get(i).getCityName().toUpperCase()+" to " 
	+visited.get(i+1).getCityName().toUpperCase()+" as the crow flies is about "
	+ visited.get(i).calculate(visited.get(i+1)) + " meters " +"(~" +
	 (int)(visited.get(i).calculate(visited.get(i+1))*coefficientNum)
	 + " miles)\n";
			}
	   present+=visited.get(visited.size()-1).getCityName().toUpperCase()+" to " 
				+visited.get(0).getCityName().toUpperCase()+" as the crow flies is about "
				+ visited.get(visited.size()-1).calculate(visited.get(0)) + ""
						+ " meters " +"(~" +
 (int)(visited.get(visited.size()-1).calculate(visited.get(0))*coefficientNum)
				+ " miles)\n";
   present="Total Distance: "+totaldis+" meters (~"+ convertedDis +" miles)";
	
	
		return present;
	}
}
