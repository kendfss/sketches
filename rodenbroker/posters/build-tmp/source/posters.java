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

public class posters extends PApplet {

PGraphics pg;
PGraphics pg2;

PGraphics buffer;

PImage img;
PImage img2;

PFont sans;

public void setup() {
  // size(1200, 900);
  
  pg = createGraphics(586/3, 810/3);
  pg2 = createGraphics(586/3, 810/3);
  buffer = createGraphics(586/3, 810/3); 
  
  img = loadImage("a.jpg");
  img2 = loadImage("n.jpg");
  // sans = createFont("sans.otf", 1000);
  sans = createFont("consola.ttf", 30);
}

public void draw() {
  background(0);

  drawPg();
  drawPg2();

  buffer.beginDraw();
  buffer.background(0xfff1f1f1);
  buffer.noStroke();
  buffer.rectMode(CORNER);
  PImage i1 = pg.get();
  PImage i2 = pg2.get();
  
  float tilesX = pg.width/4;
  float tilesY = pg.height/4;
  float tileW = pg.width/tilesX;
  float tileH = pg.height/tilesY;
 


  for (int x = 0; x < tilesX; x++) {
    for (int y = 0; y < tilesY; y++) {
      int c1 = i1.get(PApplet.parseInt(x*tileW), PApplet.parseInt(y*tileH));
      int c2 = i2.get(PApplet.parseInt(x*tileW), PApplet.parseInt(y*tileH));
      float wave = map(sin(radians(frameCount * 3 + x * 0.3f + y * 0.3f)), -1, 1, 0, 1);
      int c3 = lerpColor(c1, c2, wave);
      float bri = brightness(c3);
      
      float sizeW = map(bri,0,255,tileW,0);
      float sizeH = map(bri,0,255,tileH,0);
   
      buffer.fill(0xff305dbf);   
      buffer.rect(x*tileW, y*tileW, sizeW*1.2f, sizeH*1.2f);
    }
  }

  buffer.endDraw();

  imageMode(CENTER);
  image(buffer, width/2, height/2);
 
  }
// }
public void drawPg(){
  
  pg.beginDraw();

  pg.background(0xff111111);
  pg.imageMode(CENTER);

  // Display Image

  pg.push();
  pg.translate(pg.width/2, pg.height/2);
  float wave1 = map(sin(radians(frameCount)),-1,1,3,1.6f);
  pg.scale(wave1);
  pg.image(img, 0, 0);
  pg.pop();

  // Define Style

  pg.textFont(sans);
  pg.textAlign(CENTER, CENTER);
  pg.textSize(900);
  pg.fill(0xff111111);

  // Display Type
  
  String txt = "POMPEIJ";
  float textWidth = pg.textWidth(txt);
  
  float wave2 = map(tan(radians(frameCount)),-1,1,-100,100);
  
  pg.push();
  pg.translate(pg.width/2 +wave2, pg.height/2-40);
  pg.text(txt, 0, 0);
  pg.pop();

  pg.endDraw();
}
public void drawPg2() {

  pg2.beginDraw();

  pg2.background(0xff111111);
  pg2.imageMode(CENTER);

  // Display Image

  pg2.push();
  pg2.translate(pg.width/2, pg.height/2);
  float wave1 = map(sin(radians(frameCount)), -1, 1, 1.3f, 2.4f);
  pg2.scale(wave1);
  pg2.image(img2, 0, 0);
  pg2.pop();

  // Define Style

  pg2.textFont(sans);
  pg2.textAlign(CENTER, CENTER);
  pg2.textSize(900);
  pg2.fill(0xfff1f1f1);

  // Display Type

  String txt = "NAPLES";
  float textWidth = pg.textWidth(txt);

  float wave2 = map(tan(radians(frameCount)), -1, 1, -100, 100);

  pg2.push();
  pg2.translate(pg.width/2 -wave2, pg.height/2-40);
  pg2.text(txt, 0, 0);
  pg2.pop();

  pg2.endDraw();
}
  public void settings() {  size(400, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "posters" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
