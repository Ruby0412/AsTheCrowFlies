
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import javax.imageio.IIOException;

///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  Experiment
//File:             Organism
//Semester:         CS302 Spring 2015
//
//Author:           , Mingqi Tan.
//CS Login:         lliu
//Lecturer's Name:  Laura Hobbes LeGault
//Lab Section:      331
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//Pair Partner:     Mingqi Tan.
//Email:            mtan25@wisc.edu
//CS Login:         mingqi
//Lecturer's Name:  Laura Hobbes LeGault
//Lab Section:      (your partner's lab section number)
//
////////////////////STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//fully acknowledge and credit all sources of help,
//other than Instructors and TAs.
//
//Persons:          Identify persons by name, relationship to you, and email.
//Describe in detail the the ideas and help they provided.
//
//Online sources:   avoid web searches to solve your problems, but if you do
//search, be sure to include Web URLs and description of 
//of any information you find.
////////////////////////////80 columns wide //////////////////////////////////


public class AsTheCrowFlies {
	private static ArrayList <City> cities = new ArrayList<City>();	
	private static Trip trip = null;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args)throws FileNotFoundException,
	IllegalArgumentException {

		Scanner input = new Scanner(System.in);
		int number=-1;
        boolean fromDefault = false;
		String fn;
		
		
		System.out.println("As The Crow Flies");
		menu:while(true) {
            if(!fromDefault){
            	
           //display menu choice            	
			System.out.println();
			System.out.println("1. Load available cities from a file");
			System.out.println("2. Display available cities");
			System.out.println("3. Create a trip");
			System.out.println("4. Add a city to available cities");
			System.out.println("5. Exit Program");
			System.out.print("Enter choice as integer [1-5]: ");

			

			
			try {

				number = input.nextInt();
			}
			catch (RuntimeException ex){
				number = 6 ;
			}
			input.nextLine();
            }
            
			switch(number) {

			 case 1 :	
				int cityNum =0;
				try	
				{
					//get valid filename from the user
					System.out.print("Enter the filename: ");

					fn = input.next();		   
					File file = new File(fn);
					Scanner in = new Scanner(file);

					
					while (in.hasNextLine()){	
						try {
							String cityEle=in.nextLine();
							String[] info =cityEle.split(",");

							double lati= Double.parseDouble(info[2]);  
							double longi= Double.parseDouble(info[3]);

							if(checkLatiInput(lati)&&checkLongiInput(longi)){											
								City a = new City (info[1],info[0],lati,longi);
								cities.add(a);
								cityNum++;	
							}
							else throw new IllegalArgumentException();
						}
						catch(IllegalArgumentException ex){

						}		
						catch(RuntimeException ex){
							
						}
					}
				}
				catch (FileNotFoundException ex){
					System.out.println("Unable to read file");
					//System.out.println("0 cities added");
				}
				finally {
					System.out.println(cityNum+" cities added");
				}
				fromDefault = false;
				break;
			case 2 :
				
				//display available cities from chosen file
				if(cities.size()!=0){
					for(int i=0;i<cities.size();i++){
						System.out.println(cities.get(i));
					}			
				}
				fromDefault = false;
				break;

			case 3 :
				
				ArrayList <City> tripCities= new ArrayList<City>();

				System.out.println("There are " +  cities.size() 
						+ " cities to choose from.");
				// if there are less two cities
				if (trip == null) {
					if(cities.size()<2){
			System.out.println("Must have at least 2 cities to choose from.");
	                    break;
					}
					else		
				{
			System.out.println("New trip created, needs at least two cities.");
			
			//create a trip
					while (true) 
					{	
				System.out.print("Enter next city name (or enter to end): ");

						String command  = scan.nextLine();


						if (command.equals("") &&  tripCities.size() >= 2)
						{
							break;
						}

						if(command.equals("") && tripCities.size() < 2)
						{	
			System.out.println("Must have at least 2 cities to choose from.");
							continue menu;

						}

						City toAdd = searchCity(command);	

						//System.out.println(toAdd);

						if (toAdd == null)
						{
							continue;
						}
						else 
						{
							tripCities.add(toAdd);
						}


					}//end of while : ask for cities to add


					trip = new Trip (tripCities); 
					System.out.println(trip);


			
				}
			}//end of if : if need to add new city
			
			else 
			{
				System.out.print("Add to current trip (y/n)? ");

		if (scan.next().startsWith("y")||scan.next().startsWith("Y")) 
				{ 
					while (true) 
					{	
				System.out.print("Enter next city name (or enter to end): ");

						String command  = scan.nextLine();

		if (command.equals("") && trip.getCityNum() + tripCities.size() >= 2)
					{
							break;
						}

			if(command.equals("") && trip.getCityNum() + tripCities.size() < 2)
					{	
			System.out.println("Must have at least 2 cities to choose from.");
					
			continue menu;
						}

				City toAdd = searchCity(command);						

					if (toAdd == null)
					{
						continue;
					}
					else 
					{
						tripCities.add(toAdd);
					}


						}//end of while : ask for cities to add

						trip.addCity(tripCities);
						System.out.println(trip);


					}//end of if : add to current city
					else 
					{	
                       
			System.out.println("New trip created, needs at least two cities.");

				while (true) 
					{	
				System.out.print("Enter next city name (or enter to end): ");

					String command  = scan.nextLine();


			if (command.equals("") &&  tripCities.size() >= 2)
					{
				
				break;
				
					}
			
					if(command.equals("") &&  tripCities.size() < 2)
					{	
			System.out.println("Must have at least 2 cities to choose from.");
					
					continue menu;			
						
					}

					City toAdd = searchCity(command);	

					

					if (toAdd == null)
						{
						continue;
						}
						else 
						{
							tripCities.add(toAdd);
						}


				}//end of while : ask for cities to add

				trip = new Trip (tripCities);
				System.out.println(trip);


					}



				}

				//save trip to file
				System.out.print("Write trip details to file (y/n)? ");

				if (scan.nextLine().equals("y"))
				{
					System.out.print("Enter filename: ");

					File toWrite = new File (scan.nextLine());
					PrintWriter writer = new PrintWriter (toWrite);

	          writer.println(trip);

               writer.close();
 
				}
				fromDefault = false;
				break;//end of case 3
			case 4 :
				//add new city
				
				Double lati;
				String[]info=new String[4];
				System.out.print("Enter state name: ");		        
				info[0]=input.nextLine();
				System.out.print("Enter city name: ");
				info[1]=input.nextLine();
				while(true){
					 
					try{
			// get valid latitude
			System.out.print("Enter latitude as double (-90.0 to 90.0): ");
				info[2]=input.nextLine();
				 lati=Double.parseDouble(info[2]);
				if(checkLatiInput(lati)){
				   break;
				}
				else throw new RuntimeException();
		      	}
					catch (RuntimeException ex){
					System.out.println("Invalid input. Try again.");
				}
			}		
					while (true){
				  try{
			//get valid longitude
			System.out.print("Enter longitude as double (-180.0 to 180.0): ");
						info[3]=input.nextLine();
						Double longi=Double.parseDouble(info[3]);
						if(checkLongiInput(longi)){
							City city = new City(info[1],info[0],lati,longi);
							cities.add(city);
								System.out.println("Added: "+city);
								break;
						}
						
						else throw new RuntimeException();

					}

					catch (RuntimeException ex){
						System.out.println("Invalid input. Try again.");
					}

				}
					fromDefault = false; 
					break;
			case 5 : 
				//exit program
				
				System.out.println("Thank you for your business.");
				if(cities.size()>0){
					File file = new File("available_cities.txt");			
					try(PrintWriter output=new PrintWriter(file)){
						for(int i=0;i<cities.size();i++){
							output.println(cities.get(i));
						}	
					}
		System.out.println("Saved available cities to available_cities.txt");
				}
				System.exit(0);
				break;
			default:
				
				// deal with invalid menu choice
				while(true){
				System.out.println("Invalid input. Try again.");
				System.out.print("Enter choice as integer [1-5]: ");
                String s = input.nextLine();
                try{
                	number=Integer.parseInt(s);
                	if(number>5||number<1){
                		throw new RuntimeException();
                	}
                	else fromDefault = true; break;
                }
                catch (RuntimeException ex){
                	
                 }
				}
			}

		}





	}//end of main method

	public static boolean checkLatiInput(Double a){
		if (a<=90.00 && a>=-90.00){
			return true;
		}
		return false;
	}
	public static boolean checkLongiInput(Double b){
		if (b<=180.00 && b>=-180.00){
			return true;
		}
		return false;
	}
	

	public static City searchCity(String cityName){
		for (int i =0; i< cities.size();i++){
			if  (cities.get(i).getCityName().equalsIgnoreCase(cityName)){
				return cities.get(i); 
			}


		}

		return null ; 
	}
}//end of class
