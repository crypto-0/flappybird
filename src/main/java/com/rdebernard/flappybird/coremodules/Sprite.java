package com.rdebernard.flappybird.coremodules;
import java.awt.image.BufferedImage;

public class Sprite{
  public BufferedImage texture;
  public Sprite(){
    texture= null;
  }
  public Sprite(BufferedImage texture){
    this.texture = texture;
  }
}
