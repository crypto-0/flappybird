package com.rdebernard.flappybird.components;

import com.rdebernard.phanes.entities.Component;
import com.rdebernard.phanes.entities.Entity;

public class Parent implements Component{
  public Entity parent;
  public Parent(){
    parent = null;
  }
  public Parent(Entity parent){
    this.parent = parent;
  }
  public Parent(Parent parent){
    this.parent = parent.parent;
  }
}
