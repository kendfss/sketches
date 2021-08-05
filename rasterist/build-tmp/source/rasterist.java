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

public class rasterist extends PApplet {

int area, tilewidth, tileheight, tilesx, tilesy;
int glob;
float tiles, tilesize;
// float tilesize;

String chars = "0123456789ABCDEF";


// interface

PImage img;
int halfImage;

public void setup() {
  
  // size(displayWidth, displayHeight/2);
  // fullScreen();
  colorMode(HSB);
  // img = loadImage("../rasterist/lenna_191.png");
  // img = loadImage("C:\\Users\\Kenneth\\Pictures\\filterShoppes\\marilyn_2.png");
  img = loadImage("C:\\Users\\Kenneth\\Pictures\\filterShoppes\\marilyn_10.png");
  // img = loadImage("../rasterist/canvas_21.png");
  // img = loadImage("futa.jpg");
  // img = loadImage("marilyn_3.png");
  img.resize(width, height);
  area = width * height;
  // glob = globalAverage();
  greyscale();
  frameRate(10);  
}

public void draw() {
  // tiles = area / int(map(mouseX * mouseY, 0, area, 1, area));
  tiles = mouseX / 3;
  // tiles = mouseX;
  tilesize = width / tiles;
  tilewidth = PApplet.parseInt(tilesize);
  tileheight = PApplet.parseInt(tilesize);
  tilesx = PApplet.parseInt(tiles);
  tilesy = PApplet.parseInt(tiles);
  
  noStroke();
  clear();
  
  // color bg = color(int(random(0, 255)));
  // color bg = (int(random(0, 255)));
  int bg = color(PApplet.parseInt(random(0, 255)), PApplet.parseInt(random(0, 255)), PApplet.parseInt(random(0, 255)));
  // color fg = 255-(bg);
  int fg = negative(bg);
  background(bg);
  // println(fg, hex(fg));
  fill(fg);
  // println(getWindow());
  // println()
  // screen.height
  // int br = int(random(0, 255));
  // int bg = int(random(0, 255));
  // int bb = int(random(0, 255));
  // background(grey(colour(new int[] {br, bg, bb})));
  // background(br, bg, bb);
  // background(colour(new int[] {br, bg, bb}));
  // image(img, 0, 0);
  // background(glob);
  // background(255);
  // background(0);
  // clear();
  // fill(negative(glob));
  // fill(0);
  // fill(255-br, 255-bg, 255-bb);
  
  translate(tilesize / 2, tilesize / 2);
  
  for (int y=0; y<tiles; y++) {
    for (int x=0; x<tiles; x++) {
      int nx = PApplet.parseInt(x * tilesize);
      int ny = PApplet.parseInt(y * tilesize);
      int ix = ((nx >= 5) ? plusmin() : 1);
      int iy = ((ny >= 5) ? plusmin() : 1);
      // println(ix, iy);
      nx += PApplet.parseInt(random(0, 5)) * ix;
      ny += PApplet.parseInt(random(0, 5)) * iy;
      int c = img.get(nx, ny);
      float size = map(brightness(c), 0, 255, 0, tilesize);
      // float size = map(brightness(c), 0, 255, tilesize, 0);
      // fill(localAverage(nx, ny));
      // fill(grey(c));
      // fill(c);
      // fill(negative(c));
      // fill(glob);
      // fill(negative(grey(c)));
      // println(grey(c), negative(grey(c)));
      // println(hex(grey(c)), hex(negative(grey(c))));
      // ellipse(x * tilesize, y * tilesize, size, size);
      ellipse(nx, ny, size, size);
    }
  }
}
public String heks(int n) {
  int rem = n % 16;
  if (n - rem == 0) {
    // println(n, chars.substring(rem, rem+1));
    return chars.substring(rem, rem+1);
  }
  return heks((n - rem) / 16) + chars.substring(rem, rem+1);
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
    String[] appletArgs = new String[] { "rasterist" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
