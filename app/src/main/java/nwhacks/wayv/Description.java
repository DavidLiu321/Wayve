package nwhacks.wayv;

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

    public String getContent(){
        return content;
    }

    public int getTotal(){
        this.total = Agree - Disagree;

        return total;
    }
    //modifiers
    public void addupVotes(){
        Agree++;
    }

    public void adddownVotes(){
        Disagree++;
    }

}
