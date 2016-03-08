package com.sw.domain.entity.game;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GAME_RECORD")
public class GameRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String player;
    private Integer score;
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public GameRecord() {
    }

    public GameRecord(String player, Integer score, String type, Date time) {
        this.player = player;
        this.score = score;
        this.type = type;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
