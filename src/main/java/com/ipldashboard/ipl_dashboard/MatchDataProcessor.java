package com.ipldashboard.ipl_dashboard;

import com.ipldashboard.ipl_dashboard.model.Match;
import com.ipldashboard.ipl_dashboard.rest.MatchInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) {

    final Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    match.setMatchType(matchInput.getMatch_type());
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());

    String firstInningTeam, secondInningTeam;
    if("bat".equals(matchInput.getToss_decision())){
      firstInningTeam = matchInput.getToss_winner();
      secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    } else {
      secondInningTeam = matchInput.getToss_winner();
      firstInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam2()) ? matchInput.getTeam1() : matchInput.getTeam2();
    }
    match.setTeam1(firstInningTeam);
    match.setTeam2(secondInningTeam);

    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setResult(matchInput.getResult());
    match.setResultMargin(match.getResultMargin());

    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());

    return match;
  }

}