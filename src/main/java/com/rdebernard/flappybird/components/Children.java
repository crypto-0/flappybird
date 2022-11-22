package com.rdebernard.flappybird.components;

import java.util.ArrayList;

import com.rdebernard.phanes.entities.Component;
import com.rdebernard.phanes.entities.Entity;

public class Children implements Component{
  public ArrayList<Entity> children;
  public Children(){
    children = new ArrayList<>();
  }
  public Children(Children children){
    this.children = new ArrayList<>(children.children);
  }
}
