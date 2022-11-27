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


public class MainScene extends Scene{

  AssetManager assetManager;
  public MainScene(World world){
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
    world.systemManager.addSystem(new FallingStateSystem(world));
    world.systemManager.addSystem(new FlappingStateSystem(world));
    world.systemManager.addSystem(new AnimationSystem(world));
    world.systemManager.addSystem(new ScoreSystem(world));
    world.systemManager.addSystem(new PipeSpawnSystem(world));
    world.systemManager.addSystem(new PhysicSystem(world));
    world.systemManager.addSystem(new LocalToWorldSystem(world));
    world.systemManager.addSystem(new CollisionDetectionSystem(world));
    world.systemManager.addSystem(new RendererSystem(world));
    world.systemManager.addSystem(new CheckGameOverSystem(world));

    //register components
    world.componentManager.registerComponents(com.rdebernard.flappybird.components.Pipe.class);
    world.componentManager.registerComponents(SpriteRenderer.class);
    world.componentManager.registerComponents(Transform.class);
    world.componentManager.registerComponents(UIImage.class);
    world.componentManager.registerComponents(Animation.class);
    world.componentManager.registerComponents(FlappingState.class);
    world.componentManager.registerComponents(FallingState.class);
    world.componentManager.registerComponents(RigidBody.class);
    world.componentManager.registerComponents(Jumped.class);
    world.componentManager.registerComponents(BirdMovement.class);
    world.componentManager.registerComponents(Score.class);
    world.componentManager.registerComponents(Children.class);
    world.componentManager.registerComponents(Parent.class);
    world.componentManager.registerComponents(ScoreHitBox.class);
    world.componentManager.registerComponents(Collider.class);
    world.componentManager.registerComponents(Collision.class);
    world.componentManager.registerComponents(EntityReference.class);
    world.componentManager.registerComponents(Ground.class);

    //create bird score
    Entity scoreEntity = world.entityManager.createEntity();
    Score score = new Score();
    world.componentManager.addComponent(scoreEntity, score);
    Children children = new Children();
    world.componentManager.addComponent(scoreEntity, children);
    int offsetvalue = 30;
    Vec3d basePosition = new Vec3d(Display.getBuffer().getWidth()/2 + offsetvalue,50);
    Transform transform ;
    for(int a=0; a < 3; a++){
      Vec3d offset = new Vec3d(offsetvalue * a,0);
      for(int b=0; b< 10; b++){
        Entity entity = world.entityManager.createEntity();
        Sprite sprite = assetManager.getSprite((String.valueOf(b)));
        transform = new Transform();
        Vec3d position = new Vec3d(basePosition);
        position.sub(offset);
        transform.position = position;
        UIImage uiImage = new UIImage(sprite,3);
        uiImage.enabled = false;
        world.componentManager.addComponent(entity, transform);
        world.componentManager.addComponent(entity, uiImage);
        children.children.add(entity);
      }
    }
    //bird entity
    Entity bird = world.entityManager.createEntity();
    SpriteRenderer spriteRenderer = new SpriteRenderer();
    spriteRenderer.rendererPriority = 3;
    if(redbird != null){
      spriteRenderer.sprite = redbird.sprites.get(0);
    }
    transform = new Transform();
    transform.position = new Vec3d(60,Display.getBuffer().getHeight()/2,0);
    transform.scale = new Vec3d(1.0f, 1.0f,1.0f);
    Animation animation = new Animation(redbird,4);
    RigidBody rigidBody = new RigidBody();
    rigidBody.gravity = .7f;
    Jumped jumped = new Jumped();
    BirdMovement birdMovement = new BirdMovement(4.0f,-20f,70f,8f);
    Collider collider = new Collider(34,24);
    world.componentManager.addComponent(bird, transform);
    world.componentManager.addComponent(bird,spriteRenderer);
    world.componentManager.addComponent(bird, animation);
    world.componentManager.addComponent(bird,rigidBody);
    world.componentManager.addComponent(bird,jumped);
    world.componentManager.addComponent(bird,birdMovement);
    world.componentManager.addComponent(bird,collider);
    world.componentManager.addComponent(bird, score);
    //world.componentManager.addComponent(bird, new EntityReference(scoreEntity));

    //create bird states
    EntityState flapping = new EntityState();
    flapping.addComponent(new FlappingState());
    EntityState falling = new EntityState();
    falling.addComponent(new FallingState());
    EntityStateMachine entityStateMachine = world.entityStateMachineManager.createStateMachine(bird);
    entityStateMachine.addEntityState("flapping", flapping);
    entityStateMachine.addEntityState("falling", falling);
    entityStateMachine.changeEntityState("falling");
    
    //create pipeprefab
    //int pipeGap = 90;
    Sprite pipeRed = assetManager.getSprite("pipe-red");
    Sprite pipeRedFlipped = assetManager.getSprite("pipe-red-flipped");
    //Transform pipeRedTransform = new Transform();
    SpriteRenderer pipeRedSpriteRenderer = new SpriteRenderer(pipeRed,2);
    Prefab pipeRedPrefab = world.prefabManager.createPrefab("pipe-red");
    pipeRedPrefab.addComponent(pipeRedSpriteRenderer);
    pipeRedPrefab.addComponent(new Collider(52,320));
    pipeRedPrefab.addComponent(new com.rdebernard.flappybird.components.Pipe());
    SpriteRenderer pipeRedFlippedSpriteRenderer = new SpriteRenderer(pipeRedFlipped,2);
    Prefab pipeRedFlippedPrefab = world.prefabManager.createPrefab("pipe-red-flipped");
    pipeRedFlippedPrefab.addComponent(pipeRedFlippedSpriteRenderer);
    pipeRedFlippedPrefab.addComponent(new Collider(52,320));
    pipeRedFlippedPrefab.addComponent(new com.rdebernard.flappybird.components.Pipe());
    Transform pipeBodyTransform = new Transform();
    pipeBodyTransform.position = new Vec3d(Display.getBuffer().getWidth(),0,0);
    RigidBody pipeBodyRigidBody = new RigidBody();
    pipeBodyRigidBody.gravity = 0;
    pipeBodyRigidBody.velocity = new Vec3d(-2,0,0);
    Children  pipeBodyChildren =  new Children();
    Prefab pipeBodyPrefab = world.prefabManager.createPrefab("pipe-body");
    pipeBodyPrefab.addComponent(pipeBodyChildren);
    pipeBodyPrefab.addComponent(pipeBodyRigidBody);
    pipeBodyPrefab.addComponent(pipeBodyTransform);
    

    //create ground entity
    for(int a=0; a< 1; a++){
      Entity ground = world.entityManager.createEntity();
      spriteRenderer = new SpriteRenderer();
      spriteRenderer.rendererPriority = 3;
      spriteRenderer.sprite = base;
      transform = new Transform();
      transform.scale = new Vec3d(1f, 1f,1f);
      transform.position = new Vec3d(a * spriteRenderer.sprite.img.getWidth(),Display.getBuffer().getHeight() - spriteRenderer.sprite.img.getHeight());
      world.componentManager.addComponent(ground, transform);
      world.componentManager.addComponent(ground,spriteRenderer);
      world.componentManager.addComponent(ground, transform);
      world.componentManager.addComponent(ground,spriteRenderer);
      world.componentManager.addComponent(ground, new Ground());
      world.componentManager.addComponent(ground, new Collider(336,112) );
    }
    //create background
    Entity backgroundEntity = world.entityManager.createEntity();
    spriteRenderer = new SpriteRenderer();
    spriteRenderer.rendererPriority = 0;
    spriteRenderer.sprite = background;
    transform = new Transform();
    transform.scale = new Vec3d(1f, 1f,1f);
    world.componentManager.addComponent(backgroundEntity, transform);
    world.componentManager.addComponent(backgroundEntity,spriteRenderer);
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
    //load bird spriteSheetResources
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
    //load background resources
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
    //load ui numbers
    spriteResources.clear();
    spriteResources.add("0");
    spriteResources.add("1");
    spriteResources.add("2");
    spriteResources.add("3");
    spriteResources.add("4");
    spriteResources.add("5");
    spriteResources.add("6");
    spriteResources.add("7");
    spriteResources.add("8");
    spriteResources.add("9");
    for(String resource: spriteResources){
      String resourceFullName = "/ui/" + resource+ ".png";
      BufferedImage groundBackground = getImage(resourceFullName);
    if(groundBackground !=null){
      Sprite sprite = new Sprite(groundBackground);
      assetManager.addSprite(resource, sprite);
    }
    }
    spriteResources.clear();
    //load pip resources
    spriteResources.add("pipe-red");
    spriteResources.add("pipe-red-flipped");
    spriteResources.add("pipe-green");
    spriteResources.add("pipe-green-flipped");
    for(String resource: spriteResources){
      String resourceFullName = "/pipe/" + resource+ ".png";
      BufferedImage pipe = getImage(resourceFullName);
    if(pipe !=null){
      Sprite sprite = new Sprite(pipe);
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
