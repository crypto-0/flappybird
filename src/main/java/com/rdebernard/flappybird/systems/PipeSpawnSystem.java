package com.rdebernard.flappybird.systems;

import com.rdebernard.flappybird.components.Children;
import com.rdebernard.flappybird.components.Collider;
import com.rdebernard.flappybird.components.ScoreHitBox;
import com.rdebernard.flappybird.components.Transform;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.Entity;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;

public class PipeSpawnSystem extends SystemBase{
  int spawnRate = 100;
  int gapWidth = 95;
  int minY = -290;
  int maxY = -40; 
  int pipeHeight = 320;

  float elapseTime = 100;

	public PipeSpawnSystem(World world) {
		super(world);
	}

	@Override
	public void update(float dt) {
    if(elapseTime >=spawnRate){
      elapseTime = 0;
      int ypos = minY + (int)(Math.random() * (maxY - minY));
      Entity scoreHitBox = world.entityManager.createEntity();
      ScoreHitBox scoreHitBoxTrigger = new ScoreHitBox();
      Transform scoreHitBoxTransform = new Transform();
      scoreHitBoxTransform.localPosition = new Vec3d(0,pipeHeight + ypos);
      Collider scoreHitBoxCollider = new Collider(52,gapWidth);
      Entity pipeBody = world.prefabManager.instanciatePrefab("pipe-body");
      Entity pipeRed = world.prefabManager.instanciatePrefab("pipe-red");
      Transform pipeRedTransform = new Transform();
      pipeRedTransform.localPosition = new Vec3d(0,ypos,0);
      Entity pipeRedFlipped = world.prefabManager.instanciatePrefab("pipe-red-flipped");
      Transform pipeRedFlippedTransform = new Transform();
      pipeRedFlippedTransform.localPosition = new Vec3d(0,ypos + gapWidth + pipeHeight,0);
      Children children = world.componentManager.getComponent(pipeBody,Children.class);
      children.children.add(pipeRedFlipped);
      children.children.add(pipeRed);
      children.children.add(scoreHitBox);
      world.componentManager.addComponent(pipeRed, pipeRedTransform);
      world.componentManager.addComponent(pipeRedFlipped, pipeRedFlippedTransform);
      world.componentManager.addComponent(scoreHitBox,scoreHitBoxCollider);
      world.componentManager.addComponent(scoreHitBox,scoreHitBoxTransform);
      world.componentManager.addComponent(scoreHitBox,scoreHitBoxTrigger);
    }
    elapseTime +=dt;
	}
}
