package com.rdebernard.flappybird.systems;

import java.util.ArrayList;

import com.rdebernard.flappybird.components.BirdMovement;
import com.rdebernard.flappybird.components.FallingState;
import com.rdebernard.flappybird.components.Transform;
import com.rdebernard.flappybird.coremodules.Input;
import com.rdebernard.flappybird.coremodules.KeyCode;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.*;


public class FallingStateSystem extends SystemBase{
  private EntityQuery entityQuery;
  public FallingStateSystem(World world){
    super(world);
    entityQuery = new EntityQuery();
    entityQuery.withAll(FallingState.class,BirdMovement.class,Transform.class);
  }

  @Override
  public void update(float arg0) {
    entityQuery.query();
    ArrayList<BirdMovement> birdMovements = entityQuery.getComponents(BirdMovement.class);
    ArrayList<Transform> transforms = entityQuery.getComponents(Transform.class);
    ArrayList<Entity> entities = entityQuery.getEntities();
    for(int a=0; a < entities.size(); a++){
      if(Input.getKey(KeyCode.Space)){
        world.entityStateMachineManager.getStateMachine(entities.get(a)).changeEntityState("flapping");
      }
      Transform transform = transforms.get(a);
      BirdMovement birdMovement = birdMovements.get(a);
      if(transform.rotation.getZ() < birdMovement.fallingAngle){
        Vec3d rotation = new Vec3d(0,0,birdMovement.fallingAngle);
        transform.rotation.add(birdMovement.rotationSpeed);
        if(transform.rotation.getZ() > birdMovement.fallingAngle){
          transform.rotation = rotation;
        }
      }
    }
  }
}

