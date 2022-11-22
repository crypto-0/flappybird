package com.rdebernard.flappybird.coremodules;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class Input implements KeyListener{
  private static HashMap<KeyCode,Boolean> keys = new HashMap<>();
  static {
    for(KeyCode keyCode: KeyCode.values()){
      keys.put(keyCode,false);
    }
  }
  public static boolean getKey(KeyCode keyCode){
    return keys.get(keyCode);
  }
	@Override
	public void keyPressed(KeyEvent keyEvent) {
    KeyCode keyCode = KeyCode.valueOf(keyEvent.getKeyCode());
    keys.replace(keyCode,true);
	}
	@Override
	public void keyReleased(KeyEvent keyEvent) {
    KeyCode keyCode = KeyCode.valueOf(keyEvent.getKeyCode());
    keys.replace(keyCode,false);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
