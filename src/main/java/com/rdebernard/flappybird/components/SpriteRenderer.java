package com.rdebernard.flappybird.components;
import com.rdebernard.flappybird.coremodules.Renderer;
import com.rdebernard.phanes.entities.Component;

public class SpriteRenderer extends Renderer implements Component{
  public boolean flipx;
  public boolean flipy;
  public SpriteRenderer(){
    this.sprite = null;
    this.color = null;
    this.rendererPriority = 0;
    this.enabled = false;
    this.flipx = false;
    this.flipy = false;
  }
  public SpriteRenderer(SpriteRenderer other){
    this.sprite = other.sprite;
    this.color  = other.color;
    this.flipx = other.flipx;
    this.flipy = other.flipy;
    this.enabled = other.enabled;
  }
}
