package Model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Team {
    private int id;
    private int stadiumId;
    private String name;
    private Timestamp createdAt;
    private Timestamp deletedAt;

    public Team(int id, int stadiumId, String name, Timestamp createdAt, Timestamp deletedAt) {
        this.id = id;
        this.stadiumId = stadiumId;
        this.name = name;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
}
