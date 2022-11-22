package com.rdebernard.flappybird.components;
import com.rdebernard.flappybird.coremodules.SpriteSheet;
import com.rdebernard.phanes.entities.Component;

public class Animation implements Component{
  public float timeElapse;
  public float interval ;
  public int currentFrame;
  public int frames;
  public boolean finished ;
  public boolean loop;
  public SpriteSheet spriteSheet;
  public Animation(){
  }
  public Animation(SpriteSheet spriteSheet,long interval){
    this.spriteSheet = spriteSheet;
    this.currentFrame = 0;
    this.interval = interval;
    this.frames = spriteSheet.sprites.size();
    this.loop = true;
  }
  public Animation(Animation animation){
    this.spriteSheet = animation.spriteSheet;
    this.currentFrame = animation.currentFrame;
    this.interval = animation.interval;
    this.frames = animation.frames;
    this.loop = animation.finished;
  }
}
