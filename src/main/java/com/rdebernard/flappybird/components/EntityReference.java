package com.rdebernard.flappybird.components;

import com.rdebernard.phanes.entities.Component;
import com.rdebernard.phanes.entities.Entity;

public class EntityReference implements Component{
  public Entity entity;
  public EntityReference(){
    entity = null;
  }
  public EntityReference(Entity entity){
    this.entity = entity;
  }
  public EntityReference(EntityReference entityReference){
    this.entity = entityReference.entity;
  }
}
