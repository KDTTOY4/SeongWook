package com.fastcampus.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Team {
  private Integer id;
  private Integer stadiumId;
  private String name;
  private Timestamp createdAt;

  public Team(int stadiumId, String name) {
    this.stadiumId = stadiumId;
    this.name = name;
  }
}
