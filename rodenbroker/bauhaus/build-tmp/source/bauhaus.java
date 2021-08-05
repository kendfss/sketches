import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class bauhaus extends PApplet {

int rect_width, rect_height, radius;


public void setup() {
  
  rect_width = rect_height = radius = 600;
}
 // ​
public void draw() {
  background(0);
  rectMode(CENTER);
  stroke(0xfff1f1f1);
  strokeWeight(4);
  noFill();

  float wave = sin(frameCount * 0.01f) * 450;

  // Add the wave to the x-position
  rect(width/2 + wave, height/2, rect_width, rect_height);
  // ​
  push();
  translate(width/2, height/2);
  float x1 = 0;
  float y1 = -300;
  float x2 = 300;
  float y2 = 300;
  float x3 = -300;
  float y3 = 300;
  triangle(x1, y1, x2, y2, x3, y3);
  pop();
  // ​
  // subtract the wave-value from the x-position
  ellipse(width/2 - wave, height/2, radius, radius);
}
  public void settings() {  size(900, 900); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "bauhaus" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
