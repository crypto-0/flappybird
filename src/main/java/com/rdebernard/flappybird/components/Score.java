package com.rdebernard.flappybird.components;

import com.rdebernard.phanes.entities.Component;

public class Score implements Component{
  public int score;
  public Score(){
    score = 0;
  }
  public Score(int score){
    this.score = score;
  }
  public Score(Score score){
    this.score = score.score;
  }
}
