package com.rdebernard.flappybird.scenes;
import com.rdebernard.phanes.entities.*;
import com.rdebernard.phanes.scenes.Scene;
import com.rdebernard.flappybird.components.*;
import com.rdebernard.flappybird.coremodules.*;
import com.rdebernard.flappybird.systems.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MenuScene extends Scene{

  AssetManager assetManager;
  public MenuScene(World world){
    super(world);
    assetManager = new AssetManager();
  }
	@Override
	public void onDestroy() {
		
	}

	@Override
	public void onActivate() {
    loadResources();
    SpriteSheet redbird = assetManager.getSpriteSheet("redbird");
    Sprite base = assetManager.getSprite("base");
    Sprite background = assetManager.getSprite("background-night");
    //register systems
    world.systemManager.addSystem(new RendererSystem(world));
    //register components
    world.componentManager.registerComponents(SpriteRenderer.class);
    world.componentManager.registerComponents(Transform.class);
    //player
    //EntityArchetype birdArchetype = world.entityManager.createArchetype(SpriteRenderer.class);
    //Entity player = world.entityManager.createEntity(birdArchetype);
    Entity player = world.entityManager.createEntity();
    SpriteRenderer spriteRenderer = new SpriteRenderer();
    spriteRenderer.rendererPriority = 3;
    if(redbird != null){
      spriteRenderer.sprite = redbird.sprites.get(0);
    }
    Transform transform = new Transform();
    transform.position = new Vec3d(60,Display.getBuffer().getHeight()/2,0);
    transform.scale = new Vec3d(1.0f, 1.0f,1.0f);
    world.componentManager.addComponent(player, transform);
    world.componentManager.addComponent(player,spriteRenderer);
    //create player states
    EntityState idleEntityState = new EntityState();
    //idleEntityState.addComponent(new IdleState());
    EntityStateMachine entityStateMachine = world.entityStateMachineManager.createStateMachine(player);
    entityStateMachine.addEntityState("idle", idleEntityState);
    //create ground entity
    for(int a=-1; a< -1; a++){
    Entity ground = world.entityManager.createEntity();
    spriteRenderer = new SpriteRenderer();
    spriteRenderer.sprite = base;
    transform = new Transform();
    transform.scale = new Vec3d(1f, 1f,1f);
    if(spriteRenderer.sprite != null){
      transform.position = new Vec3d(a * spriteRenderer.sprite.texture.getWidth(),0);
    }
    world.componentManager.addComponent(ground, transform);
    world.componentManager.addComponent(ground,spriteRenderer);
    }
    //create background
    Entity backgroundEntity = world.entityManager.createEntity();
    spriteRenderer = new SpriteRenderer();
    spriteRenderer.sprite = background;
    transform = new Transform();
    transform.scale = new Vec3d(1f, 1f,1f);
    world.componentManager.addComponent(backgroundEntity, transform);
    world.componentManager.addComponent(backgroundEntity,spriteRenderer);
    //create message
    Entity message = world.entityManager.createEntity();
    /*
    UIimage uiImage =  new UIimage(assetManager.getSprite("message").img,3);
    transform2 = new Transform();
    transform2.scale = new Vec3d(1f, 1f,1f);
    transform2.position.x = 55;
    transform2.position.y = 50;
    world.addComponent(message.id, transform2);
    world.addComponent(message.id,uiImage);
    */
	}

	@Override
	public void onDeactivate() {
    assetManager.clearAssets();
	}

  private void loadResources(){
    Map<String,Integer> spriteSheetResources = new HashMap<>();
    spriteSheetResources.put("redbird",4);
    for(Map.Entry<String, Integer> resource: spriteSheetResources.entrySet()){
      String resourceFullName = "/bird/" + resource.getKey() + ".png";
      BufferedImage spriteSheetImg = getImage(resourceFullName);
      if(spriteSheetImg !=null){
        SpriteSheet spriteSheet = new SpriteSheet(spriteSheetImg,resource.getValue(),34);
        assetManager.addSpriteSheet(resource.getKey(),spriteSheet);
      }
    }
    ArrayList<String> spriteResources = new ArrayList<>();
    spriteResources.add(0,"background-night");
    spriteResources.add(1,"base");
    for(String resource: spriteResources){
      String resourceFullName = "/background/" + resource+ ".png";
      BufferedImage groundBackground = getImage(resourceFullName);
    if(groundBackground !=null){
      Sprite sprite = new Sprite(groundBackground);
      assetManager.addSprite(resource, sprite);
    }
    }
    spriteResources.clear();
    spriteResources.add(0,"message");
    for(String resource: spriteResources){
      String resourceFullName = "/ui/" + resource+ ".png";
      BufferedImage groundBackground = getImage(resourceFullName);
    if(groundBackground !=null){
      Sprite sprite = new Sprite(groundBackground);
      assetManager.addSprite(resource, sprite);
    }
    }
  }
  private BufferedImage getImage(String resourceName){
    BufferedImage img;
    try{
      InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
      img = ImageIO.read(inputStream) ;
    }
    catch(IOException e){
      java.lang.System.out.println("Coult not load image: " + resourceName);
      img = null;
    }
    return img;
  }


}
