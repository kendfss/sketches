PGraphics pg;
PGraphics pg2;

PGraphics buffer;

PImage img;
PImage img2;

PFont sans;

void setup() {
  // size(1200, 900);
  size(400, 300);
  pg = createGraphics(586/3, 810/3);
  pg2 = createGraphics(586/3, 810/3);
  buffer = createGraphics(586/3, 810/3); 
  
  img = loadImage("a.jpg");
  img2 = loadImage("n.jpg");
  // sans = createFont("sans.otf", 1000);
  sans = createFont("consola.ttf", 30);
}

void draw() {
  background(0);

  drawPg();
  drawPg2();

  buffer.beginDraw();
  buffer.background(#f1f1f1);
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
      color c1 = i1.get(int(x*tileW), int(y*tileH));
      color c2 = i2.get(int(x*tileW), int(y*tileH));
      float wave = map(sin(radians(frameCount * 3 + x * 0.3 + y * 0.3)), -1, 1, 0, 1);
      color c3 = lerpColor(c1, c2, wave);
      float bri = brightness(c3);
      
      float sizeW = map(bri,0,255,tileW,0);
      float sizeH = map(bri,0,255,tileH,0);
   
      buffer.fill(#305dbf);   
      buffer.rect(x*tileW, y*tileW, sizeW*1.2, sizeH*1.2);
    }
  }

  buffer.endDraw();

  imageMode(CENTER);
  image(buffer, width/2, height/2);
 
  }
// }
