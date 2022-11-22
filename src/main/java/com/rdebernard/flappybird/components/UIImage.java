package com.rdebernard.flappybird.components;

import com.rdebernard.flappybird.coremodules.Renderer;

public class UIImage extends Renderer{

  public UIImage(){
    this.sprite = null;
    this.color = null;
    this.rendererPriority = 0;
    this.enabled = false;
  }
  public UIImage(UIImage uiImage){
    this.sprite = uiImage.sprite;
    this.color = uiImage.color;
    this.rendererPriority = uiImage.rendererPriority;
    this. enabled = uiImage.enabled;
  }
}
