package com.rdebernard.flappybird.components;
import com.rdebernard.phanes.entities.Component;

public class Collider implements Component{
  public int width;
  public int height;
  public Collider(int width,int height){
    this.height = height;
    this.width = width;
  }
  public Collider(Collider collider){
    this.width = collider.width;
    this.height = collider.height;
  }

}
