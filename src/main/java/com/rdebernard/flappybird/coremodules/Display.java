package com.rdebernard.flappybird.coremodules;
import java.awt.image.BufferedImage;

public class Display{
  private static BufferedImage buffer = null;
  public static void setRenderingResolution(int w,int h){
    if(w > 0 && h > 0){
      buffer = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    }
  }
  public static BufferedImage getBuffer() {
    return buffer;
  }
}
