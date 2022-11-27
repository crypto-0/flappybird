package com.rdebernard.flappybird.components;

import com.rdebernard.phanes.entities.Component;

public class ScoreHitBox implements Component{
  public boolean scored;
  public ScoreHitBox(){
    scored = false;
  }
  public ScoreHitBox(boolean scored){
    this.scored = scored;
  }
  public ScoreHitBox(ScoreHitBox scoreHitBox){
    this.scored = scoreHitBox.scored;
  }
}
