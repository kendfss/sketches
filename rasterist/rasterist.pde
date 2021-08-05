int area, tilewidth, tileheight, tilesx, tilesy;
color glob;
float tiles, tilesize;
// float tilesize;

String chars = "0123456789ABCDEF";


// interface

PImage img;
int halfImage;

void setup() {
  size(300, 300);
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

void draw() {
  // tiles = area / int(map(mouseX * mouseY, 0, area, 1, area));
  tiles = mouseX / 3;
  // tiles = mouseX;
  tilesize = width / tiles;
  tilewidth = int(tilesize);
  tileheight = int(tilesize);
  tilesx = int(tiles);
  tilesy = int(tiles);
  
  noStroke();
  clear();
  
  // color bg = color(int(random(0, 255)));
  // color bg = (int(random(0, 255)));
  color bg = color(int(random(0, 255)), int(random(0, 255)), int(random(0, 255)));
  // color fg = 255-(bg);
  color fg = negative(bg);
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
      int nx = int(x * tilesize);
      int ny = int(y * tilesize);
      int ix = ((nx >= 5) ? plusmin() : 1);
      int iy = ((ny >= 5) ? plusmin() : 1);
      // println(ix, iy);
      nx += int(random(0, 5)) * ix;
      ny += int(random(0, 5)) * iy;
      color c = img.get(nx, ny);
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
