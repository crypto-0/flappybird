package com.rdebernard.flappybird.components;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.Component;

public class Jumped implements Component{
  public Vec3d position;
  public Jumped(){
    position = new Vec3d();
  }
  public Jumped(Vec3d pos){
    position = pos;
  }
}
