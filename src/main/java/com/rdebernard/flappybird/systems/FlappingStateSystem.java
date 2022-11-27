package com.rdebernard.flappybird.systems;
import com.rdebernard.flappybird.components.*;
import com.rdebernard.flappybird.coremodules.Input;
import com.rdebernard.flappybird.coremodules.KeyCode;
import com.rdebernard.flappybird.coremodules.Vec3d;

import java.util.ArrayList;
import com.rdebernard.phanes.entities.*;

public class FlappingStateSystem extends SystemBase{
  private EntityQuery entityQuery;
  public FlappingStateSystem(World world){
    super(world);
    entityQuery = new EntityQuery();
    entityQuery.withAll(FlappingState.class,BirdMovement.class,Transform.class,RigidBody.class,Jumped.class);
  }

	@Override
	public void update(float dt) {
    entityQuery.query();
    ArrayList<BirdMovement> birdMovements = entityQuery.getComponents(BirdMovement.class);
    ArrayList<Transform> transforms = entityQuery.getComponents(Transform.class);
    ArrayList<RigidBody> rigidBodies = entityQuery.getComponents(RigidBody.class);
    ArrayList<Jumped> jumpeds = entityQuery.getComponents(Jumped.class);
    ArrayList<Entity> entities = entityQuery.getEntities();
    for(int a=0; a < entities.size(); a++){
      Transform transform = transforms.get(a);
      BirdMovement birdMovement = birdMovements.get(a);
      RigidBody rigidBody = rigidBodies.get(a);
      Jumped jumped = jumpeds.get(a);

      if(Input.getKey(KeyCode.Space)){
        Vec3d velocity = new Vec3d(0,birdMovement.jumpForce);
        rigidBody.velocity = new Vec3d();
        rigidBody.velocity.sub(velocity);
        jumped.position = new Vec3d(transform.position);
      }
      else{
        if(jumped.position.getY() <= transform.position.getY()){
          world.entityStateMachineManager.getStateMachine(entities.get(a)).changeEntityState("falling");
        }

      }
      if(transform.rotation.getZ() > birdMovement.flappingAngle){
        Vec3d rotation = new Vec3d(0,0,birdMovement.flappingAngle);
        transform.rotation.sub(birdMovement.rotationSpeed);
        if(transform.rotation.getZ() < birdMovement.flappingAngle){
          transform.rotation = rotation;
        }
      }
    }
	}
}
