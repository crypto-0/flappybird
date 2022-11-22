package com.rdebernard.flappybird.components;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.Component;

public class Transform implements Component{
  public Vec3d position ;
  public Vec3d rotation ;
  public Vec3d scale ;
  public Vec3d localPosition ;
  public Vec3d localRotation ;
  public Vec3d localScale ;
  public boolean isDirty;
  public Transform(){
    position =new Vec3d();
    rotation = new Vec3d();
    scale = new Vec3d(1,1,1);
    localPosition=new Vec3d();
    localRotation = new Vec3d();
    localScale = new Vec3d(1,1,1);
    isDirty = true;

  }
  public Transform(Vec3d position, Vec3d rotation,Vec3d scale){
    this.position = position;
    this.rotation = rotation;
    this.scale = scale;
    localPosition=new Vec3d();
    localRotation = new Vec3d();
    localScale = new Vec3d(1,1,1);
    isDirty = true;
  }
  public Transform(Transform transform){
    this.position = new Vec3d(transform.position);
    this.rotation = new Vec3d(transform.rotation);
    this.scale = new Vec3d(transform.scale);
    localPosition=new Vec3d(transform.localPosition);
    localRotation = new Vec3d(transform.localRotation);
    localScale = new Vec3d(transform.localScale);
    isDirty = transform.isDirty;
  }
}
