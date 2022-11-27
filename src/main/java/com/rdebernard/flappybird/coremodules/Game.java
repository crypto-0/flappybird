package com.rdebernard.flappybird.coremodules;
import com.rdebernard.flappybird.scenes.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import com.rdebernard.phanes.entities.*;
import com.rdebernard.phanes.scenes.*;
public class Game extends JPanel implements Runnable
{
  private Thread animator;
  private volatile boolean running = false;
  private int TARGET_FPS = 60;
  private int NO_DELAY_PER_YIELD = 10;
  private long period = 1000 / TARGET_FPS;

  public Game(int  PWIDTH,int PHEIGHT) {
    setBackground(Color.white);
    setFocusable(true);
    setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
    Display.setRenderingResolution(PWIDTH,PHEIGHT);
  }

  public void init(){
    Scene menuScene= new MenuScene(new World());
    Scene mainScene= new MainScene(new World());
    Scene gameOver = new GameOverScene(new World());
    World.sceneManager.addScene("menu",menuScene);
    World.sceneManager.addScene("main",mainScene);
    World.sceneManager.addScene("gameover",gameOver);
    World.sceneManager.loadScene("menu",false);
    //World.sceneManager.loadScene("gameover",false);
    addKeyListener(new Input());
    start();
  }

  public void start() {
    if (animator == null || !running) {
      animator = new Thread(this);
      animator.start();
    }
  }

  @Override
  public void run() {
    long beforeTime, timeDiff=0, sleepTime, afterTime;
    long overSleepTime = 0l;
    long dt =0;
    int noDelays = 0;
    beforeTime = java.lang.System.currentTimeMillis();
    running = true;;
    while (running) {
      //world.systemManager.update(dt * .001f * TARGET_FPS);
      World.sceneManager.update(dt * .001f * TARGET_FPS);
      paintScreen();
      afterTime = java.lang.System.currentTimeMillis();
      timeDiff = afterTime - beforeTime;
      sleepTime = period - timeDiff - overSleepTime;
      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
        }
        overSleepTime = (java.lang.System.currentTimeMillis() - afterTime) - sleepTime;
      }
      else {
        overSleepTime = 0;
        if (++noDelays >= NO_DELAY_PER_YIELD) {
          Thread.yield();
          noDelays = 0;
        }
      }
      dt = java.lang.System.currentTimeMillis() - beforeTime;
      beforeTime = java.lang.System.currentTimeMillis();
    }
  }
   public void paintScreen() {
    Graphics g;
    try{
      g = this.getGraphics();
      BufferedImage bufferedImage = Display.getBuffer();
      if((g != null) && (bufferedImage !=null)){
        g.drawImage(bufferedImage, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
      }
      Graphics2D g2d = Display.getBuffer().createGraphics(); 
      if(g2d == null)return;
      int bufferW = Display.getBuffer().getWidth();
      int bufferh = Display.getBuffer().getHeight();
      g2d.setColor(Color.white);
      g2d.fillRect(0,0,bufferW,bufferh);
      }
    catch (Exception e) {
      java.lang.System.out.println("Graphics context error: " + e);
    }
  }

}
