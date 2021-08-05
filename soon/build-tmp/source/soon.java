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

public class soon extends PApplet {

int area, tilewidth, tileheight, tilesx, tilesy, cx, cy, x, y;
int glob, bg, fg;
float tiles, tilesize, angle, time, z, r, dr, dt, t;
// float tilesize;




// interface

PImage img;
int halfImage;

public void setup() {
  
  // size(displayWidth, displayHeight);
  // size(displayHeight, displayHeight);
  
  colorMode(HSB);
  
  // img = loadImage("../rasterist/lenna_191.png");
  // img = loadImage("../rasterist/lenna.png");
  img = loadImage("C:\\Users\\Kenneth\\Pictures\\filterShoppes\\marilyn_2.png");
  // img = loadImage("C:\\Users\\Kenneth\\Pictures\\filterShoppes\\canvas_17.png");
  // img = loadImage("canvas_13.pngenna_191.png");
  // img = loadImage("../rasterist/canvas_21.png");
  // img = loadImage("futa.jpg");
  // img = loadImage("../rasterist/marilyn_3.png");
  img.resize(width, height);
  area = width * height;
  angle = 0;
  cx = x = width/2;
  cy = y = height/2;
  // r = cx;
  r = 0;
  dr = .1f;
  // imag
  // glob = globalAverage();
  // greyscale();
  frameRate(10);  
  bg = color(PApplet.parseInt(random(0, 255)), PApplet.parseInt(random(0, 255)), PApplet.parseInt(random(0, 255)));
  fg = negative(bg);
  // fill(fg);
  background(bg);
  noStroke();
  // arc(x, y, width, height, start, stop);
  tilesize = 5;
  fill(img.get(x, y));
  ellipse(x, y, tilesize, tilesize);
}

public void draw() {
  dt = map(width-mouseX, 0, width-1, 2, .1f);
  // translate(width / 2, height / 2);
  
  float dx = (PApplet.parseFloat(x) - PApplet.parseFloat(cx));
  float dy = (PApplet.parseFloat(y) - PApplet.parseFloat(cy));
  // float ds = sqrt(pow(x, 2) + pow(y, 2));
  
  
  angle = atan(dy/dx);
  // t = tan(time/angle);
  t = sin(time/angle);
  
  x = PApplet.parseInt(r * cos(time)) + cx;
  y = PApplet.parseInt(r * sin(time)) + cy;
  
  // fill(img.get(cx + x, cy + y));
  fill(img.get(x, y));
  // fill(negative(img.get(cx + x, cy + y)));
  
  ellipse(x, y, tilesize, tilesize);
  time += dt;
  // time = sin(angle/dt);
  r += dr;
  
  float ds = dist(cx, cx, x, y);
  // println(ds, x, y, cx, cy, r, dr);
  
  if (ds>=cx||ds>=cy) {
    r = (cx+cy)/2;
    dr *= -1;
    r += dr;
  }
}
String hexchars = "0123456789ABCDEF";


public String heks(int n) {
  int rem = n % 16;
  if (n - rem == 0) {
    // println(n, hexchars.substring(rem, rem+1));
    return hexchars.substring(rem, rem+1);
  }
  return heks((n - rem) / 16) + hexchars.substring(rem, rem+1);
}

public int grey(int px) {
  int[] clr = rgb(px);
  int g = 0;
  for (int o : clr) {
    g += o;
  }
  return color(g / clr.length);
}
public int colour(int[] tones) {
  String hx = "";
  for (int tone : tones) {
    // hx += hex(tone);
    hx += heks(tone);
    // println(tone, heks(tone));
  }
  return color(unhex(hx));
}
public float mean(int[] tones) {
  int g = 0;
  for (int tone : tones) {
    g += tone;
  }
  return PApplet.parseFloat(g) / PApplet.parseFloat(tones.length);
}
public int negative(int c) {
  int[] px = rgb(c);
  // return colour(new int[] {255-px[0], 255-px[1], 255-px[2]});
  return color(unhex("FFFFFFFF")) - colour(px);
}
public int globalAverage() {
  int[] reds = {};
  int[] greens = {};
  int[] blues = {};
  for (int y = 0; y < height; ++y) {
    for (int x = 0; x < width; ++x) {
      int[] px = rgb(img.get(x, y));
      reds = append(reds, px[0]);
      greens = append(greens, px[1]);
      blues = append(blues, px[2]);
    }
  }
  int red = PApplet.parseInt(mean(reds));
  int green = PApplet.parseInt(mean(greens));
  int blue = PApplet.parseInt(mean(blues));
  return colour(new int[] {red, green, blue});
}
public int[] rgb(int px) {
  String clr = hex(px);  
  int r = unhex(clr.substring(0, 2));
  int g = unhex(clr.substring(2, 4));
  int b = unhex(clr.substring(4, 6));
  // int a = unhex(clr.substring(6, 8));
  // println(r, g, b, a, clr.substring(0, 2), clr.substring(2, 4), clr.substring(4, 6), clr.substring(6, 8));
  return new int[] {r, g, b};
}
public int[] rgba(int px) {
  String clr = hex(px);  
  int r = unhex(clr.substring(0, 2));
  int g = unhex(clr.substring(2, 4));
  int b = unhex(clr.substring(4, 6));
  int a = unhex(clr.substring(6, 8));
  return new int[] {r, g, b, a};
}
public void greyscale() {
  for (int y = 0; y < height; ++y) {
    for (int x = 0; x < width; ++x) {
      img.set(x, y, color(grey(img.get(x, y))));
    }
  }
}
public int plusmin() {
  int[] vals = {-1, 1};
  int index = PApplet.parseInt(random(0, 255));
  return vals[index % 2];
}
public int[] range(int start, int stop, int step) {
  int[] box = {};
  for (int i=start; i<stop; i+=step) {
    box = append(box, i);
  }
  return box;
}
public int randex(int max) {
  int[] rack = range(0, max, 1);
  return rack[PApplet.parseInt(random(0, 2*rack.length)) % rack.length];
}

public int[] force(int x, int y) {
  x = PApplet.parseInt(map(x, 0, width, 1, width));
  y = PApplet.parseInt(map(y, 0, height, 1, height));
  int dx = PApplet.parseInt(map(x-mouseX, -width, width, -10, 10));
  int dy = PApplet.parseInt(map(y-mouseY, -height, height, -10, 10));
  int f = PApplet.parseInt(sqrt(pow(dx, 2) + pow(dy, 2)));
  return new int[] {dx/f, dy/f};
}

public int localAverage(int x0, int y0) {
  // int[] px = pixelinfo(img.get(x0, y0));
  int[] reds = {};
  int[] greens = {};
  int[] blues = {};
  // int[] alphas = {};
  // int dx = x0%tilewidth;
  int dx = x0%tilewidth;
  for (int y = y0; y < y0 + tileheight; ++y) {
    for (int x = x0; x < x0 + tilewidth; ++x) {
      int[] px = rgb(img.get(x, y));
      // // println(px, px.length);
      reds = append(reds, px[0]);
      greens = append(greens, px[1]);
      blues = append(blues, px[2]);
      // // alphas = append(alphas, px[3]);
    }
  }
  int red = PApplet.parseInt(mean(reds));
  int green = PApplet.parseInt(mean(greens));
  int blue = PApplet.parseInt(mean(blues));
  // int alpha = int(mean(alphas));
  return colour(new int[] {red, green, blue});
}
public float Size(int x,int y) {
  float s = map(brightness(img.get(x, y)), 0, 255, 0, tilesize);
  if (s < 2) {
    s = 0;
  }
  // return int(s);
  return s;
}
public int[][] neighbors(int x, int y) {
  int[][] neighbs = {{},};
  int xmin, xmax, ymin, ymax, ctr;
  xmin = (x < 1) ? width-1 : x-1;
  xmax = (x >= width-1) ? 0 : x+1;
  ymin = (y < 1) ? height-1 : y-1;
  ymax = (y < height-1) ? 1 : y+1;
  ctr = 0;
  for (int j=ymin; j<=ymax; j++) {
    for (int i=xmin; i<=xmax; i++) {
      if (!(i==x && j==y)) {
        int[] elem = {i, j};
        // neighbs = append(neighbs, new int[] {});
        neighbs[ctr] = elem;
        ctr++;
      }
    }
  }
  return neighbs;
}
public int[][] nullNeighbors(int x0, int y0) {
  int[][] nulls = {};
  int[][] neighbs = neighbors(x0, y0);
  for (int i = 0; i < neighbs.length; ++i) {
    int[] n = neighbs[i];
    int x, y;
    x = n[0];
    y = n[1];
    if (Size(x, y) == 0) {
      nulls[i] = n;
    }
  }
  return nulls;
}
public float localSize(int x, int y) {
  if (nullNeighbors(x, y).length > 2) {
    return 0;
  }
  return Size(x, y);
}
public float hypotenuse(int x, int y) {
  return sqrt(pow(x, 2) + pow(y, 2));
}

class Particle {
  int x, y, x0, y0;
  Particle(int ecks, int why) {
    x = ecks;
    x0 = ecks;
    y = why;
    y0 = why;
  }
  public void boost(int ecks, int why) {
    x += ecks;
    y += why;
  }
  public int dist() {
    int dx = abs(x - mouseX);
    int dy = abs(y - mouseY);
    // int mag = int(sqrt(pow(dx, 2) + pow(dy, 2)));
    return PApplet.parseInt(sqrt(pow(dx, 2) + pow(dy, 2)));
  }
  public void update() {
    
  }
}
  public void settings() {  size(300, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "soon" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
