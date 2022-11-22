package com.rdebernard.flappybird;
import javax.swing.JFrame;
import com.rdebernard.flappybird.coremodules.Game;

public class FlappyBird{

  public static void main(String args[]){
    JFrame window = new JFrame();
    Game game = new Game(288,512);
    game.init();
    window.add(game);
    window.pack();
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
