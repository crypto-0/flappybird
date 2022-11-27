package com.rdebernard.flappybird.systems;
import com.rdebernard.phanes.entities.*;
import com.rdebernard.flappybird.coremodules.Display;
import com.rdebernard.flappybird.components.*;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class RendererSystem extends SystemBase{
  EntityQuery entityQuery;
  public RendererSystem(World world){
    super(world);
    entityQuery = new EntityQuery();
  }
	@Override
	public void update(float dt) {
    Graphics2D g2d = Display.getBuffer().createGraphics(); 
    if(g2d == null)return;
    g2d.setColor(Color.white);
    entityQuery.withAll(Transform.class,SpriteRenderer.class);
    entityQuery.query();
    ArrayList<SpriteRenderer> spriteRenderers= entityQuery.getComponents(SpriteRenderer.class);
    ArrayList<Transform> transforms= entityQuery.getComponents(Transform.class);
    if(spriteRenderers.size() == transforms.size()){
      int[] sortedIndices = IntStream.range(0,transforms.size()).boxed().sorted((a,b)->{
        return spriteRenderers.get(a).rendererPriority - spriteRenderers.get(b).rendererPriority;
      }).mapToInt(indice -> indice).toArray();
      for(int indice: sortedIndices){
        SpriteRenderer spriteRenderer = spriteRenderers.get(indice);
        if(!spriteRenderer.enabled)continue;
        Transform transform = transforms.get(indice);
        //g2d.drawImage(spriteRenderer.sprite.img,(int)transform.position.getX(),(int)transform.position.getY(),null);
        AffineTransform at = AffineTransform.getTranslateInstance((int)(transform.position.getX()),(int)transform.position.getY());
        at.rotate(Math.toRadians(transform.rotation.getZ()),spriteRenderer.sprite.img.getWidth()/2,spriteRenderer.sprite.img.getHeight()/2);
        g2d.drawImage(spriteRenderer.sprite.img,at,null);
      }
    }
    entityQuery.resetFilter();
    entityQuery.withAll(UIImage.class,Transform.class);
    entityQuery.query();
    ArrayList<UIImage> uiImages = entityQuery.getComponents(UIImage.class);
    transforms = entityQuery.getComponents(Transform.class);
    if(uiImages.size() == transforms.size()){
      int[] sortedIndices = IntStream.range(0,transforms.size()).boxed().sorted((a,b)->{
        return uiImages.get(a).rendererPriority - uiImages.get(b).rendererPriority;
      }).mapToInt(indice -> indice).toArray();
      for(int indice: sortedIndices){
        UIImage uiImage = uiImages.get(indice);
        if(!uiImage.enabled)continue;
        Transform transform = transforms.get(indice);
        g2d.drawImage(uiImage.sprite.img,(int)transform.position.getX(),(int)transform.position.getY(),null);
      }
    }

    /*
    entityQuery.resetFilter();
    entityQuery.withAll(Collider.class,Transform.class);
    entityQuery.query();
    transforms= entityQuery.getComponents(Transform.class);
    ArrayList<Collider> colliders = entityQuery.getComponents(Collider.class);
    for(int a=0; a< colliders.size();a++){
        Collider collider = colliders.get(a);
        Transform transform = transforms.get(a);
        g2d.drawRect((int)transform.position.getX(),(int)transform.position.getY(),collider.width,collider.height);
    }
    */
    g2d.dispose();
	}
}
