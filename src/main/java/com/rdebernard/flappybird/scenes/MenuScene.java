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
    Sprite message = assetManager.getSprite("message");
    //register systems
    world.systemManager.addSystem(new RendererSystem(world));
    world.systemManager.addSystem(new AnimationSystem(world));
    world.systemManager.addSystem(new MenuSceneSystem(world));
    //register components
    world.componentManager.registerComponents(SpriteRenderer.class);
    world.componentManager.registerComponents(Transform.class);
    world.componentManager.registerComponents(UIImage.class);
    world.componentManager.registerComponents(Animation.class);
    //player
    //EntityArchetype birdArchetype = world.entityManager.createArchetype(SpriteRenderer.class);
    //Entity player = world.entityManager.createEntity(birdArchetype);
    Entity bird = world.entityManager.createEntity();
    SpriteRenderer spriteRenderer = new SpriteRenderer();
    spriteRenderer.rendererPriority = 3;
    if(redbird != null){
      spriteRenderer.sprite = redbird.sprites.get(0);
    }
    Transform transform = new Transform();
    transform.position = new Vec3d(60,Display.getBuffer().getHeight()/2,0);
    transform.scale = new Vec3d(1.0f, 1.0f,1.0f);
    Animation animation = new Animation(redbird,4);
    world.componentManager.addComponent(bird, transform);
    world.componentManager.addComponent(bird,spriteRenderer);
    world.componentManager.addComponent(bird, animation);
    
    //create ground entity
    for(int a=0; a< 1; a++){
      Entity ground = world.entityManager.createEntity();
      spriteRenderer = new SpriteRenderer();
      spriteRenderer.sprite = base;
      spriteRenderer.rendererPriority = 1;
      transform = new Transform();
      transform.scale = new Vec3d(1f, 1f,1f);
      transform.position = new Vec3d(a * spriteRenderer.sprite.img.getWidth(),Display.getBuffer().getHeight() - spriteRenderer.sprite.img.getHeight());
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
    Entity messageEntity = world.entityManager.createEntity();
    UIImage uiImage =  new UIImage();
    uiImage.sprite = message;
    uiImage.rendererPriority = 2;
    transform = new Transform();
    transform.scale = new Vec3d(1f, 1f,1f);
    transform.position = new Vec3d(55, 50, 0);
    world.componentManager.addComponent(messageEntity, transform);
    world.componentManager.addComponent(messageEntity, uiImage);
	}

	@Override
	public void onDeactivate() {
    assetManager.clearAssets();
    world.entityManager.clearEntities();
    world.componentManager.clearComponents();
    world.entityStateMachineManager.clearStateMachines();
    world.prefabManager.clearPrefabs();
    world.systemManager.clearSystems();
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
