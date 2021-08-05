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

public class freedraw extends PApplet {

/* built with Studio Sketchpad: 
 *   http://sketchpad.cc
 * 
 * observe the evolution of this sketch: 
 *   http://studio.sketchpad.cc/sp/pad/view/ro.Wxsib$lHXuL/rev.6
 * 
 * authors: 
 *   GoToLoop
 
 * license (unless otherwise specified): 
 *   creative commons attribution-share alike 3.0 license.
 *   http://creativecommons.org/licenses/by-sa/3.0/ 
*/
 
 
 
/**
 * Free Drawing (v3.01)
 * by KingDragonRider (2013/Jul)
 * modders TFGuy44 & GoToLoop
 *
 * http://forum.processing.org/topic/how-to-draw-lines-without-using-any-line-functions
 * http://studio.processingtogether.com/sp/pad/export/ro.9ZSvPnI4AVjwR/latest
*/
 

static final int SCALE = 10;
PGraphics pg;
int c;
 
public void setup() {
  
  // size(600, 600, JAVA2D);
  noLoop();
 
  pg = createGraphics(width/SCALE, height/SCALE, JAVA2D);
 
  pg.smooth(4);
  pg.beginDraw();
  pg.background(0);
  pg.endDraw();
 
  mousePressed();
} 
 
public void draw() {
  scale(SCALE);
  image(pg, 0, 0);
}
 
public void mousePressed() {
  if (mouseButton == CENTER)  pg.background(c);
  c = (int) random(0x1000000) | 0xff000000;
  mouseMoved();
}
 
public void mouseMoved() {
  pg.stroke(c);
  pg.line(mouseX/SCALE, mouseY/SCALE, pmouseX/SCALE, pmouseY/SCALE);
  pg.endDraw();
 
  redraw();
}
  public void settings() {  size(600, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "freedraw" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
