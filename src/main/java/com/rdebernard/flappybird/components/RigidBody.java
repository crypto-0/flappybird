package com.rdebernard.flappybird.components;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.Component;

public class RigidBody implements Component{
  public Vec3d velocity ;
  public Vec3d force;
  public float mass;
  public float gravity;

  public RigidBody(){
   velocity = new Vec3d();
   force = new Vec3d();
   mass =1;
   gravity = 9.8f;
  }
  public RigidBody(Vec3d velocity, Vec3d force, float mass, float gravity) {
    this.velocity = velocity;
    this.force = force;
    this.mass = mass;
    this.gravity = gravity;
  }

  public RigidBody(RigidBody rigidBody){
    this.velocity = rigidBody.velocity;
    this.force = rigidBody.force;
    this.mass = rigidBody.mass;
    this.gravity = rigidBody.gravity;
  }
}
