package com.rdebernard.flappybird.systems;

import java.util.ArrayList;

import com.rdebernard.flappybird.components.Children;
import com.rdebernard.flappybird.components.Parent;
import com.rdebernard.flappybird.components.Transform;
import com.rdebernard.flappybird.coremodules.Vec3d;
import com.rdebernard.phanes.entities.Entity;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;

public class LocalToWorldSystem extends SystemBase{

  private EntityQuery entityQuery;
	public LocalToWorldSystem(World world) {
		super(world);
    entityQuery = new EntityQuery();
    entityQuery.withAll(Transform.class,Children.class);
    entityQuery.withNone(Parent.class);
	}

	@Override
	public void update(float dt) {
    entityQuery.query();
    ArrayList<Children> childrens = entityQuery.getComponents(Children.class);
    ArrayList<Transform> transforms = entityQuery.getComponents(Transform.class);
    for(int a=0; a < childrens.size();a++){
      Transform transform = transforms.get(a);
      Children children = childrens.get(a);
      children.children.forEach(c->{
        updateChildren(c,transform);
      });

    }
		
	}

  private void updateChildren(Entity child,Transform parentTransform){
    Children children = world.componentManager.getComponent(child,Children.class);
    Transform childTransform = world.componentManager.getComponent(child,Transform.class);
    if(parentTransform != null && childTransform != null){
      Vec3d position = new Vec3d(childTransform.localPosition);
      Vec3d scale = new Vec3d(parentTransform.scale);
      Vec3d rotation = new Vec3d(parentTransform.rotation);
      position.add(parentTransform.position);
      childTransform.position = position;
      childTransform.rotation = rotation;
      childTransform.scale = scale;
      if(children != null && children.children !=null){
        children.children.forEach(e->{
          updateChildren(e,parentTransform);
        });
      }
    }
  }
}
