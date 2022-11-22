package com.rdebernard.flappybird.coremodules;

public class Vec3d{
  private float x;
  private float y;
  private float z;
  public Vec3d(){
    x=0;
    y=0;
    z=0;
  }
  public Vec3d(float x,float y){
    this.x = x;
    this.y = y;
    this.z = 0;
  }
  public Vec3d(float x,float y,float z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public Vec3d(Vec3d vec3d){
    this.x = vec3d.x;
    this.y = vec3d.y;
    this.z = vec3d.z;
  }
  public void add(Vec3d other){
    this.x +=other.x;
    this.y +=other.y;
    this.z +=other.z;
  }
  public void add(float value){
    this.x +=value;
    this.y +=value;
    this.z +=value;
  }
  public void sub(Vec3d other){
    this.x -=other.x;
    this.y -=other.y;
    this.z -=other.z;
  }
  public void sub(float value){
    this.x -=value;
    this.y -=value;
    this.z -=value;
  }
  public void mult(Vec3d other){
    this.x *=other.x;
    this.y *=other.y;
    this.z *=other.z;
  }
  public void mult(float value){
    this.x *=value;
    this.y *=value;
    this.z *=value;
  }
  public float getX() {
    return x;
  }
  public float getY() {
    return y;
  }
  public float getZ() {
    return z;
  }
}

