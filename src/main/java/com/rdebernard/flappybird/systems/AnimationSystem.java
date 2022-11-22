package com.rdebernard.flappybird.systems;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;
import com.rdebernard.flappybird.components.*;

public class AnimationSystem extends SystemBase{
  private EntityQuery entityQuery;
  public AnimationSystem(World world){
    super(world);
    entityQuery = new EntityQuery();
    entityQuery.withAll(Animation.class,SpriteRenderer.class);
  }
	@Override
	public void update(float dt) {
    entityQuery.query();
    ArrayList<Animation> animations = entityQuery.getComponents(Animation.class);
    ArrayList<SpriteRenderer> spriteRenderers = entityQuery.getComponents(SpriteRenderer.class);
    for(int a =0; a< animations.size(); a++){
      Animation animation = animations.get(a);
      SpriteRenderer spriteRenderer = spriteRenderers.get(a);
      if(animation.spriteSheet == null)continue;
      if(animation.currentFrame == animation.frames){
        animation.finished = true;
      }
      if(animation.loop){
        animation.currentFrame = animation.currentFrame % animation.frames;
      }
      else{
        if(animation.finished)continue;
      }
      spriteRenderer.sprite = animation.spriteSheet.sprites.get(animation.currentFrame);
      if(animation.timeElapse >= animation.interval){
        animation.currentFrame++;
        animation.timeElapse =0;
      }
      animation.timeElapse += dt;

    }
	}
}
