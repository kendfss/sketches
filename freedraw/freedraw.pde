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
color c;
 
void setup() {
  size(600, 600);
  // size(600, 600, JAVA2D);
  noLoop();
 
  pg = createGraphics(width/SCALE, height/SCALE, JAVA2D);
 
  pg.smooth(4);
  pg.beginDraw();
  pg.background(0);
  pg.endDraw();
 
  mousePressed();
} 
 
void draw() {
  scale(SCALE);
  image(pg, 0, 0);
}
 
void mousePressed() {
  if (mouseButton == CENTER)  pg.background(c);
  c = (int) random(0x1000000) | #000000;
  mouseMoved();
}
 
void mouseMoved() {
  pg.stroke(c);
  pg.line(mouseX/SCALE, mouseY/SCALE, pmouseX/SCALE, pmouseY/SCALE);
  pg.endDraw();
 
  redraw();
}
