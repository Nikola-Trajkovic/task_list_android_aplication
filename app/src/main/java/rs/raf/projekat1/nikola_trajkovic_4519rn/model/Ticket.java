package rs.raf.projekat1.nikola_trajkovic_4519rn.model;

public class Ticket {

    private int id;
    private String picture;
    private String naslov;
    private String opis;
    private String type;
    private String priority;
    private int estimation;
    private int loggedTime;

    public Ticket(int id, String picture, String naslov, String opis, String type, String priority, int estimation, int loggedTime) {
        this.id = id;
        this.picture = picture;
        this.naslov = naslov;
        this.opis = opis;
        this.type = type;
        this.priority = priority;
        this.estimation = estimation;
        this.loggedTime = loggedTime;
    }

    public String getPriority() {
        return priority;
    }

    public int getEstimation() {
        return estimation;
    }

    public String getPicture() {
        return picture;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getOpis() {
        return opis;
    }

    public String getType() {
        return type;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public int getId() {
        return id;
    }
}
