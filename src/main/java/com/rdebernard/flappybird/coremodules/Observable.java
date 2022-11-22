package com.rdebernard.flappybird.coremodules;
import java.awt.event.KeyEvent;

public interface Observable{
  void notifyObservers(KeyEvent keyEvent);
}
