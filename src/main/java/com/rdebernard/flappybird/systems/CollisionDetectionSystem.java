package com.rdebernard.flappybird.systems;

import java.util.ArrayList;

import com.rdebernard.flappybird.components.BirdMovement;
import com.rdebernard.flappybird.components.Collider;
import com.rdebernard.flappybird.components.Collision;
import com.rdebernard.flappybird.components.Ground;
import com.rdebernard.flappybird.components.Pipe;
import com.rdebernard.flappybird.components.ScoreHitBox;
import com.rdebernard.flappybird.components.Transform;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.Entity;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;

public class CollisionDetectionSystem extends SystemBase{
  private EntityQuery entityQuery;

	public CollisionDetectionSystem(World world) {
		super(world);
    entityQuery = new EntityQuery();
	}

	@Override
	public void update(float arg0) {
    entityQuery.withAll(Collider.class);
    entityQuery.query();
    ArrayList<Entity> entities = entityQuery.getEntities();
    entities.forEach(e->{
      world.componentManager.removeComponent(e,Collision.class);
    });
    scoreHitBoxDetection();
    pipeDetection();
    groundDetection();
	}
  private void scoreHitBoxDetection(){
    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,ScoreHitBox.class);
    entityQuery.query();
    ArrayList<Transform> transforms = entityQuery.getComponents(Transform.class);
    ArrayList<Collider> colliders = entityQuery.getComponents(Collider.class);
    ArrayList<Entity> entities = entityQuery.getEntities();

    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,BirdMovement.class);
    entityQuery.query();
    ArrayList<Transform> birdTransforms = entityQuery.getComponents(Transform.class);
    ArrayList<Collider> birdColliders = entityQuery.getComponents(Collider.class);
    ArrayList<Entity> birdEntities = entityQuery.getEntities();

    for(int a=0; a<birdTransforms.size(); a++){
      Collider collider = birdColliders.get(a);
      Vec3d position = birdTransforms.get(a).position;
      for(int b=0; b< transforms.size(); b++){
        Collider collider2 = colliders.get(b);
        Vec3d position2 = transforms.get(b).position;
        if(position.getX() < position2.getX() + collider2.width && position.getX() + collider.width > position2.getX() && position.getY() < position2.getY() + collider2.height && position.getY() + collider.height > position2.getY()){
          Entity entity1 = birdEntities.get(a);
          Entity entity2 = entities.get(b);
          world.componentManager.addComponent(entity1, new Collision(entity1,entity2));
        }
      }
    }
  }
  private void pipeDetection(){
    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,Pipe.class);
    entityQuery.query();
    ArrayList<Transform> transforms = entityQuery.getComponents(Transform.class);
    ArrayList<Collider> colliders = entityQuery.getComponents(Collider.class);
    ArrayList<Entity> entities = entityQuery.getEntities();

    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,BirdMovement.class);
    entityQuery.query();
    ArrayList<Transform> birdTransforms = entityQuery.getComponents(Transform.class);
    ArrayList<Collider> birdColliders = entityQuery.getComponents(Collider.class);
    ArrayList<Entity> birdEntities = entityQuery.getEntities();

    for(int a=0; a<birdTransforms.size(); a++){
      Collider collider = birdColliders.get(a);
      Vec3d position = birdTransforms.get(a).position;
      for(int b=0; b< transforms.size(); b++){
        Collider collider2 = colliders.get(b);
        Vec3d position2 = transforms.get(b).position;
        if(position.getX() < position2.getX() + collider2.width && position.getX() + collider.width > position2.getX() && position.getY() < position2.getY() + collider2.height && position.getY() + collider.height > position2.getY()){
          Entity entity1 = birdEntities.get(a);
          Entity entity2 = entities.get(b);
          world.componentManager.addComponent(entity1, new Collision(entity1,entity2));
        }
      }
    }
  }
  private void groundDetection(){
    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,Ground.class);
    entityQuery.query();
    ArrayList<Transform> transforms = entityQuery.getComponents(Transform.class);
    ArrayList<Collider> colliders = entityQuery.getComponents(Collider.class);
    ArrayList<Entity> entities = entityQuery.getEntities();

    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,BirdMovement.class);
    entityQuery.query();
    ArrayList<Transform> birdTransforms = entityQuery.getComponents(Transform.class);
    ArrayList<Collider> birdColliders = entityQuery.getComponents(Collider.class);
    ArrayList<Entity> birdEntities = entityQuery.getEntities();

    for(int a=0; a<birdTransforms.size(); a++){
      Collider collider = birdColliders.get(a);
      Vec3d position = birdTransforms.get(a).position;
      for(int b=0; b< transforms.size(); b++){
        Collider collider2 = colliders.get(b);
        Vec3d position2 = transforms.get(b).position;
        if(position.getX() < position2.getX() + collider2.width && position.getX() + collider.width > position2.getX() && position.getY() < position2.getY() + collider2.height && position.getY() + collider.height > position2.getY()){
          Entity entity1 = birdEntities.get(a);
          Entity entity2 = entities.get(b);
          world.componentManager.addComponent(entity1, new Collision(entity1,entity2));
        }
      }
    }
  }
}
