package com.rdebernard.flappybird.coremodules;
import java.awt.image.BufferedImage;

public class Sprite{
  public BufferedImage img;
  public Sprite(){
    img= null;
  }
  public Sprite(BufferedImage img){
    this.img = img;
  }
}
