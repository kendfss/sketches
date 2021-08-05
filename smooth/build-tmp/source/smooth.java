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

public class smooth extends PApplet {

// int x = 0;

// void setup() {
//   fullScreen(P2D, SPAN);
//   smooth(4);
// }

// void draw() {
//   background(0);
//   ellipse(x, height/2, height/4, height/4);
//   x += 1;
// }

public void setup() {
  // size(400, 400, P3D);  // first try this
   // second try this
  background(-1);
}
public void draw() {
  strokeWeight(6);
  line(pmouseX, pmouseY, mouseX, mouseY);
}
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "smooth" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
