int area, tilewidth, tileheight, tilesx, tilesy, cx, cy, x, y;
color glob, bg, fg;
float tiles, tilesize, angle, time, z, r, dr, dt, t;
// float tilesize;




// interface

PImage img;
int halfImage;

void setup() {
  // size(300, 300);
  // size(displayWidth, displayHeight);
  size(displayHeight, displayHeight);
  
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
  dr = .1;
  // imag
  // glob = globalAverage();
  // greyscale();
  frameRate(10);  
  bg = color(int(random(0, 255)), int(random(0, 255)), int(random(0, 255)));
  fg = negative(bg);
  // fill(fg);
  background(bg);
  noStroke();
  // arc(x, y, width, height, start, stop);
  tilesize = 5;
  fill(img.get(x, y));
}

void draw() {
  dt = map(width-mouseX, 0, width-1, 2, .1);
  // translate(width / 2, height / 2);
  
  float dx = (float(x) - float(cx));
  float dy = (float(y) - float(cy));
  // float ds = sqrt(pow(x, 2) + pow(y, 2));
  
  
  angle = atan(dy/dx);
  // t = tan(time/angle);
  t = sin(time/angle);
  
  x = int(r * cos(time)) + cx;
  y = int(r * sin(time)) + cy;
  
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
