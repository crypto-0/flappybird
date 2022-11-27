package com.rdebernard.flappybird.components;

import com.rdebernard.flappybird.coremodules.Renderer;
import com.rdebernard.flappybird.coremodules.Sprite;
import com.rdebernard.phanes.entities.Component;

public class UIImage extends Renderer implements Component{

  public UIImage(){
    this.sprite = null;
    this.color = null;
    this.rendererPriority = 0;
    this.enabled = true;
  }
  public UIImage(Sprite sprite,int rendererPriority){
    this.sprite = sprite;
    this.rendererPriority = rendererPriority;
    this.color = null;
    this.enabled = true;

  }
  public UIImage(UIImage uiImage){
    this.sprite = uiImage.sprite;
    this.color = uiImage.color;
    this.rendererPriority = uiImage.rendererPriority;
    this. enabled = uiImage.enabled;
  }
}
