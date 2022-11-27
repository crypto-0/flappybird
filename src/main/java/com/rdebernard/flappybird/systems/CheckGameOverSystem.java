package com.rdebernard.flappybird.systems;

import java.util.ArrayList;

import com.rdebernard.flappybird.components.BirdMovement;
import com.rdebernard.flappybird.components.Collision;
import com.rdebernard.flappybird.components.Ground;
import com.rdebernard.flappybird.components.Pipe;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;

public class CheckGameOverSystem extends SystemBase{
private EntityQuery entityQuery;
  public CheckGameOverSystem(World world) {
		super(world);
    entityQuery = new EntityQuery();
    //entityQuery.withAll(BirdMovement.class,Collision.class);
  }

  @Override
  public void update(float arg0) {
    entityQuery.withAll(BirdMovement.class,Collision.class);
    entityQuery.query();
    ArrayList<Collision> collisions = entityQuery.getComponents(Collision.class);
    collisions.forEach(c->{
      Pipe pipe = world.componentManager.getComponent(c.entity2,Pipe.class);
      Ground ground = world.componentManager.getComponent(c.entity2,Ground.class);
      if(pipe !=null || ground !=null){
        World.sceneManager.loadScene("gameover", true);
        world.systemManager.removeSystem(PhysicSystem.class);
        world.systemManager.removeSystem(CheckGameOverSystem.class);
        world.systemManager.removeSystem(AnimationSystem.class);
        return;
      }
    });
  }
}
