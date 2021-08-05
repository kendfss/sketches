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

public class as_circumstance_consumes_motivation extends PApplet {

float ds, dx, dy, rx, ry, r0, r;
PVector v1, v2;
int tmax, t;



public void setup() {
    
    v1 = new PVector(random(0, width), random(0, height));
    v2 = new PVector(random(0, width), random(0, height));
    noFill();
    noStroke();
    // stroke(255, 102, 0);
    tmax = 1;
    r0 = r = (width + height) / 6;
    v2.x = 0;
    v2.y = 0;
}

public void draw() {
    if (tmax > t) {
        tmax++;
        t++;
        // background(255);
        dx = v1.x - v2.x; 
        dy = v1.y - v2.y; 
        ds = dist(v1.x, v1.y, v2.x, v2.y); 
        
        if (dx == 0) {
            println("dx == 0");
        }
        if (dy == 0) {
            println("dy == 0");
        }
        
        
        // line(85, 20, 10, 10);
        // line(90, 90, 15, 80);
        // stroke(0, 0, 0);
        // bezier(v1.x, v1.y, dx, dy, -dx, -dy, v2.x, v2.y);
        // bezier(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
        // arc(x, y, width, height, start, stop);
        fill(255, 0, 0);
        ellipse(v1.x, v1.y, 10, 10);
        fill(0, 0, 255);
        ellipse(v2.x, v2.y, 10, 10);
        noFill();
        stroke(0);
        path(PApplet.parseInt(v1.x), PApplet.parseInt(v1.y), PApplet.parseInt(dx), PApplet.parseInt(dy));
        noStroke();
        
        
        // v1.x = abs((v1.x - dx) % width);
        // v1.x = abs((v1.x + random(-width-1, width+1)) % width);
        // v1.x = abs((v1.x + random(0, dx+1)) % width);
        v1.x = (width / 2) + (r * cos(t));
        // v1.y = abs((v1.y - dy) % height);
        // v1.y = abs((v1.y + random(-height-1, height+1)) % height);
        // v1.y = abs((v1.y + random(0, dy+1)) % height);
        v1.y = (height / 2) + (r * sin(t));
        v2.x = lerp(v1.x, v2.x, .7f);
        v2.y = lerp(v1.y, v2.y, .7f);
        r = r0 * cos(ds/t+r);
    }
}

class Particle {
    PVector position, velocity, acceleration;
    Float mass;
    Particle(PVector p, PVector v, PVector a, Float m) {
        mass = m;
        position = p;
        velocity = v;
        acceleration = a;
    }
}

public void keyPressed() {
    tmax++;
}

public void path(int x, int y, int w, int h) {
    if (random(0, 1) > .5f) {
        line(x, y, x+w, y+h);
    } else {
        line(x+w, y, x, y+h);
    }
    
}
  public void settings() {  size(300, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "as_circumstance_consumes_motivation" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
