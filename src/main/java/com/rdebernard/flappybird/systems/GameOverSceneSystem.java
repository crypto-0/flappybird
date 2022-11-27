package com.rdebernard.flappybird.systems;

import com.rdebernard.flappybird.coremodules.Input;
import com.rdebernard.flappybird.coremodules.KeyCode;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;

public class GameOverSceneSystem extends SystemBase{

	public GameOverSceneSystem(World world) {
		super(world);
	}

	@Override
	public void update(float arg0) {
    if(Input.getKey(KeyCode.Space)){
      World.sceneManager.loadScene("menu", false);
      Input.resetKey(KeyCode.Space);
    }

	}
}
