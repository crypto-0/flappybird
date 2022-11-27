package com.rdebernard.flappybird.components;
import com.rdebernard.phanes.entities.Component;

public class BirdMovement implements Component{
  public float jumpForce;
  public float flappingAngle;
  public float fallingAngle;
  public float rotationSpeed;
  public BirdMovement(){
    jumpForce = 0;
    flappingAngle = 0;
    fallingAngle = 0;
    rotationSpeed = 0;
  }
  public BirdMovement(float jumpForce,float flappingAngle,float fallingAngle,float rotationSpeed){
    this.jumpForce = jumpForce;
    this.flappingAngle = flappingAngle;
    this.fallingAngle = fallingAngle;
    this.rotationSpeed = rotationSpeed;
  }
  public BirdMovement(BirdMovement birdMovement){
    this.jumpForce = birdMovement.jumpForce;
    this.flappingAngle = birdMovement.fallingAngle;
    this.fallingAngle = birdMovement.fallingAngle;
    this.rotationSpeed = birdMovement.rotationSpeed;
  }
}

