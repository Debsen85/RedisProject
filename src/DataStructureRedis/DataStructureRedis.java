package DataStructureRedis;

public class DataStructureRedis {
    
    public String title;
    public String price;
    public Boolean enrolled;
    public Integer estimatedTime;

    public DataStructureRedis(String title, String price, Boolean enrolled, Integer estimatedTime) {
        this.title = title;
        this.price = price;
        this.enrolled = enrolled;
        this.estimatedTime = estimatedTime;
    }

}