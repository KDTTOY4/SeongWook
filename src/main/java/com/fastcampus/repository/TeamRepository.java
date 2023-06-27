package com.fastcampus.repository;

import com.fastcampus.dao.TeamDao;
import com.fastcampus.model.Team;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository {
  public void save(Team team) {
    TeamDao.insertTeam(team.getStadiumId(), team.getName());
  }
}
