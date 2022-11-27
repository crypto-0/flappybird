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


public class GameOverScene extends Scene{

  AssetManager assetManager;
  public GameOverScene(World world){
    super(world);
    assetManager = new AssetManager();
  }
	@Override
	public void onDestroy() {
		
	}

	@Override
	public void onActivate() {
    loadResources();
    Sprite gameOver = assetManager.getSprite("gameover");
    //register systems
    world.systemManager.addSystem(new RendererSystem(world));
    world.systemManager.addSystem(new GameOverSceneSystem(world));
    //register components
    world.componentManager.registerComponents(SpriteRenderer.class);
    world.componentManager.registerComponents(Transform.class);
    world.componentManager.registerComponents(UIImage.class);
    //create gameover
    Entity gameOverEntity = world.entityManager.createEntity();
    UIImage uiImage =  new UIImage();
    uiImage.sprite = gameOver;
    uiImage.rendererPriority = 2;
    Transform transform = new Transform();
    transform.scale = new Vec3d(1f, 1f,1f);
    transform.position = new Vec3d((Display.getBuffer().getWidth()/2) - (gameOver.img.getWidth()/2), (Display.getBuffer().getHeight()/2) - (gameOver.img.getHeight()/2), 0);
    world.componentManager.addComponent(gameOverEntity, transform);
    world.componentManager.addComponent(gameOverEntity, uiImage);
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
    ArrayList<String> spriteResources = new ArrayList<>();
    spriteResources.add(0,"gameover");
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
