package nwhacks.wayve;

public class Description {
    private String Title;
    private String content;
    private int Agree;
    private int Disagree;
    private int total;

    public Description(String title, String content){
        this.Title = title;
        this.content = content;
        Agree = 0;
        Disagree = 0;
        total = 0;
    }

    //Accessors
    public String getTitle(){
        return Title;
    }
    public String getContent(){
        return content;
    }

    public int getAgree(){
        return Agree;
    }

    public int getDisagree(){
        return Disagree;
    }

    public int getTotal(){
        this.total = Agree - Disagree;
        return total;
    }

    //Modifiers
    public void addupVotes(){
        Agree++;
    }

    public void adddownVotes(){
        Disagree++;
    }

}
