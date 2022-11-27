package com.rdebernard.flappybird.systems;
import java.util.ArrayList;
import com.rdebernard.flappybird.components.*;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;


public class PhysicSystem extends SystemBase{
  private EntityQuery entityQuery;

  public PhysicSystem(World world){
    super(world);
    entityQuery = new EntityQuery();
    entityQuery.withAll(RigidBody.class,Transform.class);
  }
	@Override
	public void update(float dt) {
    entityQuery.query();
    ArrayList<RigidBody>rigidBodies = entityQuery.getComponents(RigidBody.class);
    ArrayList<Transform>transforms = entityQuery.getComponents(Transform.class);
    for(int a =0; a < rigidBodies.size(); a++){
      Transform transform = transforms.get(a);
      RigidBody rigidBody = rigidBodies.get(a);
      rigidBody.force.add(new Vec3d(0f,rigidBody.gravity * rigidBody.mass,0));
      Vec3d velocity = new Vec3d(rigidBody.velocity);
      velocity.mult(dt);
      transform.position.add(velocity);
      Vec3d  acceleration = new Vec3d(rigidBody.force);
      acceleration.div(rigidBody.mass);
      acceleration.mult(dt);
      rigidBody.velocity.add(acceleration);
      rigidBody.force = new Vec3d();
    }
		
	}
}
