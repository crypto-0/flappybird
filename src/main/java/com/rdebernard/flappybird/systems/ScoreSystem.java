package com.rdebernard.flappybird.systems;

import java.util.ArrayList;

import com.rdebernard.flappybird.components.BirdMovement;
import com.rdebernard.flappybird.components.Children;
import com.rdebernard.flappybird.components.Collision;
import com.rdebernard.flappybird.components.Score;
import com.rdebernard.flappybird.components.ScoreHitBox;
import com.rdebernard.flappybird.components.UIImage;
import com.rdebernard.phanes.entities.Entity;
import com.rdebernard.phanes.entities.SystemBase;
import com.rdebernard.phanes.entities.World;

public class ScoreSystem extends SystemBase{
  float currentTime;
  EntityQuery entityQuery;

  public ScoreSystem(World world) {
    super(world);
    entityQuery = new EntityQuery();

  }

  @Override
  public void update(float dt) {
    showScoreEntites();
  }
  private void updateTotalScore(){
    entityQuery.resetFilter();
    entityQuery.withAll(Score.class,Collision.class,BirdMovement.class);
    entityQuery.query();
    ArrayList<Collision> collisions = entityQuery.getComponents(Collision.class);
    ArrayList<Score> scores = entityQuery.getComponents(Score.class);

    for(int a=0; a < collisions.size(); a++){
      Collision collision = collisions.get(a);
      ScoreHitBox scoreHitBox = world.componentManager.getComponent(collision.entity2,ScoreHitBox.class);
      if(scoreHitBox != null && !scoreHitBox.scored){
        Score score = scores.get(a);
        score.score++;
        scoreHitBox.scored = true;
      }
    }
  }
  private void showScoreEntites(){
    entityQuery.resetFilter();
    entityQuery.withAll(Score.class,Children.class);
    entityQuery.query();
    ArrayList<Children> childrens = entityQuery.getComponents(Children.class);
    ArrayList<Score> scores = entityQuery.getComponents(Score.class);

    //disable previous score count entities
    for(int a =0; a < scores.size(); a++){
      Score score = scores.get(a);
      Children children = childrens.get(a);
      if(score.score < 10){
        int childOffset = score.score;
        if(childOffset < children.children.size()){
          Entity child = children.children.get(childOffset);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = false;
          }
        }
      }
      else if(score.score < 100){
        int childOffset = (score.score%10);
        int childOffset2 = (score.score/10) + 10;
        if(childOffset < children.children.size()){
          Entity child = children.children.get(childOffset);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = false;
          }
        }
        if(childOffset2 < children.children.size()){
          Entity child = children.children.get(childOffset2);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = false;
          }
        }
      }
      else if(score.score < 1000){
        int childOffset = (score.score%10);
        int childOffset2 = ((score.score/10) % 10) + 10;
        int childOffset3 = (score.score/100) + 20;
        if(childOffset < children.children.size()){
          Entity child = children.children.get(childOffset);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = false;
          }
        }
        if(childOffset2 < children.children.size()){
          Entity child = children.children.get(childOffset2);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = false;
          }
        }
        if(childOffset3 < children.children.size()){
          Entity child = children.children.get(childOffset3);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = false;
          }
        }
      }
    }
    updateTotalScore();
    //enable new score count entities
    for(int a =0; a < scores.size(); a++){
      Score score = scores.get(a);
      Children children = childrens.get(a);
      if(score.score < 10){
        int childOffset = score.score;
        if(childOffset < children.children.size()){
          Entity child = children.children.get(childOffset);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = true;
          }
        }
      }
      else if(score.score < 100){
        int childOffset = (score.score%10);
        int childOffset2 = (score.score/10) + 10;
        if(childOffset < children.children.size()){
          Entity child = children.children.get(childOffset);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = true;
          }
        }
        if(childOffset2 < children.children.size()){
          Entity child = children.children.get(childOffset2);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = true;
          }
        }
      }
      else if(score.score < 1000){
        int childOffset = (score.score % 10);
        int childOffset2 = ((score.score/10) % 10) + 10;
        int childOffset3 = (score.score / 100) + 20;
        if(childOffset < children.children.size()){
          Entity child = children.children.get(childOffset);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = true;
          }
        }
        if(childOffset2 < children.children.size()){
          Entity child = children.children.get(childOffset2);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = true;
          }
        }
        if(childOffset3 < children.children.size()){
          Entity child = children.children.get(childOffset3);
          UIImage uiImage = world.componentManager.getComponent(child,UIImage.class);
          if(uiImage !=null){
            uiImage.enabled = true;
          }
        }
      }
    }
  }
}
