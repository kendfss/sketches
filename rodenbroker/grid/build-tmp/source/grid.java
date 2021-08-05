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

public class grid extends PApplet {

int amount = 5;
int[][] state = new int[amount][amount];
int fg = 0xff10E09C;
PImage img1, img2, img3;

int mx, my;

public void setup() {
  
  for (int x = 0; x < amount; x++) {
    for (int y = 0; y < amount; y++) {
      state[x][y] = PApplet.parseInt(random(0, 6));
    }
  }

  img1 = loadImage("1.jpg");
  img1.resize(width, height);

  img2 = loadImage("2.jpg");
  img2.resize(width, height);

  img3 = loadImage("3.jpg");
  img3.resize(width, height);
}

public void draw() {
  background(0xfff1f1f1);

  image(img3, 0, 0);
  float tileW = width/amount; 
  float tileH = height/amount; 
  fill(fg);
  noStroke();
  ellipseMode(CORNER);

  // Check where the mouse is
  mx = PApplet.parseInt(map(mouseX, 0, width, 0, amount));
  my = PApplet.parseInt(map(mouseY, 0, height, 0, amount));
  
  // Draw the visual

  for (int x = 0; x < amount; x++) {
    for (int y = 0; y < amount; y++) {

      if (state[x][y] == 0) {

        pushMatrix();
        translate(x*tileW, y*tileH);
        ellipse(0, 0, tileW, tileH);
        popMatrix();
      } else if (state[x][y] == 1) {

        pushMatrix();
        translate(x*tileW, y*tileH);
        rect(0, 0, tileW, tileH);
        popMatrix();
      } else if (state[x][y] == 2) {

        pushMatrix();
        translate(x*tileW, y*tileH);
        triangle(0, 0, tileW, tileH, 0, tileW);
        popMatrix();
      } else if (state[x][y] == 3) {

        int sx = PApplet.parseInt(tileW*x);
        int sy = PApplet.parseInt(tileH*y);
        int sw = PApplet.parseInt(tileW);
        int sh = PApplet.parseInt(tileH);

        int dx = sx;
        int dy = sy;
        int dw = sw;
        int dh = sh;

        copy(img1, sx, sy, sw, sh, dx, dy, dw, dh);
      } else if (state[x][y] == 4) {

        int sx = PApplet.parseInt(tileW*x);
        int sy = PApplet.parseInt(tileH*y);
        int sw = PApplet.parseInt(tileW);
        int sh = PApplet.parseInt(tileH);

        int dx = sx;
        int dy = sy;
        int dw = sw;
        int dh = sh;

        copy(img2, sx, sy, sw, sh, dx, dy, dw, dh);
      } else if (state[x][y] == 5) {
      }
    }
  }
}

public void countUp(int x, int y) {
  if (state[x][y] < 5) {
    state[x][y]++;
  } else {
    state[x][y] = 0;
  }
}

public void mouseReleased() {
  countUp(mx,my);
  saveFrame("out/visual####.jpg");
}
  public void settings() {  size(900, 900); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "grid" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
