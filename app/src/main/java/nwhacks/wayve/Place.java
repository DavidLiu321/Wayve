package nwhacks.wayve;

import java.lang.reflect.Array;

import java.util.*;


public class Place {
    private String name;
    private float longitude, latitude;
    private ArrayList<Description> description;      //make it a class
    private ArrayList<Float> listofrating = new ArrayList<>();
    private float rating;

    //Constructor
    public Place(String name){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rating = 0;
    }

    //Accessor
    public String getname(){
        return name;
    }

    public float getLongitude(){
        return longitude;
    }

    public float getLatitude(){
        return latitude;
    }

    public ArrayList<Description> getDescription(){
        return description;
    }

    public float getRating(){
        calculateRating();
        return rating;
    }

    //Modifier
    public void addDescription(String title, String newDescription){
        Description desc = new Description(title, newDescription);
        description.add(desc);
    }
    //adds the newRating to the new
    public void addRating(float newRating){
        listofrating.add(newRating);
        getRating();
    }

    //finds the average of the ratings in the listofrating
    public void calculateRating(){

        int size = listofrating.size();
        float sum = 0;

        for(int i = 0; i < size; i++){
            sum += listofrating.get(i);
        }
        sum = sum/size;

        this.rating = sum;
    }

    //sets the longitude and the latitude for the place
    public void setLongandLad(float longitude, float latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /*
    returns ArrayList of top three Description based off of total(= agreed - disagreed)
    if the list of description has less than 3 descriptions, then it returns descriptions that
    has the title "nothing" and description "nothing"
     */
    public ArrayList<Description> gettopthree(){
        Description first = description.get(0);
        Description second = new Description("nothing", "nothing");
        Description third = new Description("nothing", "nothing");
        ArrayList<Description> topthree = new ArrayList<>();

        for(int k = 1; k < description.size(); k++){
            if(description.get(k).getTotal() > first.getTotal()){
                third = second;
                second = first;
                first = description.get(k);
            }
            else if(description.get(k).getTotal() > second.getTotal()){
                third = second;
                second = description.get(k);
            }else if(description.get(k).getTotal() > third.getTotal()){
                third = description.get(k);
            }
        }
        topthree.add(first);
        topthree.add(second);
        topthree.add(third);

        return topthree;


    }
}
