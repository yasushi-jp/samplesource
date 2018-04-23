package knowledgebank.batch.model;

public class Ranking {
    private long id;
    private String name;
    private long count;

    public Ranking(long id, String name, long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public long getCount() {
        return count;
    }
}
