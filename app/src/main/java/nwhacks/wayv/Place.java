package nwhacks.wayv;

import java.lang.reflect.Array;
import java.util.*;


public class Place {
    private String name;
    private float longitude, latitude;
    private ArrayList<Description> description;      //make it a class
    private ArrayList<Integer> listofrating;
    private double rating;

    //Constructor
    public Place(String name, float longitude, float latitude, int rating){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rating = rating;
    }

    //Accessor
    public String getname(){
        return name;
    }

    public ArrayList<Description> getDescription(){
        return description;
    }

    public double getRating(){
        return rating;
    }

    //Modifier
    public void addDescription(String title, String newDescription){
        Description desc = new Description(title, newDescription);
    }

    public void calculateRating(int newRating){
        listofrating.add(newRating);
        int size = listofrating.size();
        double sum = 0;

        for(int i = 0; i < size; i++){
            sum += listofrating.get(i);
        }
        sum = sum/size;

        this.rating = sum;
    }

    public void gettopthree(){
        Description first = description.get(0);
        Description second, third;

        for(int k = 1; k < description.size(); k++){
            if(description.get(k).getTotal() > first.getTotal()){
                first = description.get(k);
            }
        }

    }
}
