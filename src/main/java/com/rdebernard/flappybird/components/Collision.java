package com.rdebernard.flappybird.components;

import com.rdebernard.phanes.entities.Component;
import com.rdebernard.phanes.entities.Entity;

public class Collision implements Component{
  public  Entity entity1;
  public  Entity entity2;
  public double collisionTime;
  public byte xnormal,ynormal;
  public Collision(){
    this.entity1 = null;
    this.entity2 = null;
    this.collisionTime = 0;
    this.xnormal = 0;
    this.ynormal = 0;
  }
  public Collision(Entity entity1,Entity entity2){
    this.entity1 = entity1;
    this.entity2 = entity2;
    this.collisionTime = 0;
    this.xnormal = 0;
    this.ynormal = 0;
  }
  public Collision(Entity entity1,Entity entity2,double collisionTime,byte xnormal,byte ynormal){
    this.entity1 = entity1;
    this.entity2 = entity2;
    this.collisionTime = collisionTime;
    this.xnormal =xnormal;
    this.ynormal = ynormal;
  }
  public Collision(Collision collision){
    this.entity1 = collision.entity1;
    this.entity2 = collision.entity2;
    this.collisionTime = collision.collisionTime;
    this.xnormal =collision.xnormal;
    this.ynormal = collision.ynormal;
    
  }
}
