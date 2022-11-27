package com.rdebernard.flappybird.systems;

import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;
import com.rdebernard.flappybird.coremodules.Input;
import com.rdebernard.flappybird.coremodules.KeyCode;

public class MenuSceneSystem extends SystemBase{

	public MenuSceneSystem(World world) {
		super(world);
	}

	@Override
	public void update(float dt) {
    if(Input.getKey(KeyCode.Space)){
      World.sceneManager.loadScene("main", false);
    }
		
	}
}
