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

public class joy_division extends PApplet {

PImage img;
float tilewidth, tileheight, tilesize;
int tilesx, tilesy, step, t, hour, tmax, len;
// Line[] paths;
int[][][] paths;

public void path(int x, int y, int w, int h) {
    if (random(0, 1) > .5f) {
        line(x, y, x+w, y+h);
    } else {
        line(x+w, y, x, y+h);
    }
    
}


public void setup() {
    
    step = 10;
    hour = 160;
    // tmax = 101;
    tmax = 1;
    len = (height + width) / 2;
    paths = new int[len][len][2];
}

public void draw() {
    if (t < tmax) {
        t++;
        for(int y = step; y <= height - step; y += step) {
            int[][] l = new int[len][2];
            paths[y] = l;
            for (int x = step; x < width - step; x += step) {
                // int[] p = {x, y + int(random(0, 10))};
                int[] p = {x, y};
                l[x] = p;
            }
        }
        
        for (int i = 0; i < paths.length; ++i) {
            int[][] l = paths[i];
            for (int k = 1; k < l.length; k++) {
                int[] p0 = l[k];
                int[] p1 = l[k-1];
                println("p0: "+p0);
                println("p1: "+p1);
                // curve(p0[0], p0[1], p1[0], p1[1], x3, y3, x4, y4);
                curve(p0[0], p0[1], p1[0], p1[1], p0[0], p0[1], p1[0], p1[1]);
                // line(p0[0], p0[1], p1[0], p1[1]);
            } 
        }
    }
    
    
}

public void keyPressed() {
    tmax++;
}
  public void settings() {  size(320, 320); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "joy_division" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
