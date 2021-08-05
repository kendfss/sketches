String hexchars = "0123456789ABCDEF";


String heks(int n) {
  int rem = n % 16;
  if (n - rem == 0) {
    // println(n, hexchars.substring(rem, rem+1));
    return hexchars.substring(rem, rem+1);
  }
  return heks((n - rem) / 16) + hexchars.substring(rem, rem+1);
}

color grey(int px) {
  int[] clr = rgb(px);
  int g = 0;
  for (int o : clr) {
    g += o;
  }
  return color(g / clr.length);
}
color colour(int[] tones) {
  String hx = "";
  for (int tone : tones) {
    // hx += hex(tone);
    hx += heks(tone);
    // println(tone, heks(tone));
  }
  return color(unhex(hx));
}
float mean(int[] tones) {
  int g = 0;
  for (int tone : tones) {
    g += tone;
  }
  return float(g) / float(tones.length);
}
color negative(color c) {
  int[] px = rgb(c);
  // return colour(new int[] {255-px[0], 255-px[1], 255-px[2]});
  return color(unhex("FFFFFFFF")) - colour(px);
}
color globalAverage() {
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
  int red = int(mean(reds));
  int green = int(mean(greens));
  int blue = int(mean(blues));
  return colour(new int[] {red, green, blue});
}
int[] rgb(color px) {
  String clr = hex(px);  
  int r = unhex(clr.substring(0, 2));
  int g = unhex(clr.substring(2, 4));
  int b = unhex(clr.substring(4, 6));
  // int a = unhex(clr.substring(6, 8));
  // println(r, g, b, a, clr.substring(0, 2), clr.substring(2, 4), clr.substring(4, 6), clr.substring(6, 8));
  return new int[] {r, g, b};
}
int[] rgba(int px) {
  String clr = hex(px);  
  int r = unhex(clr.substring(0, 2));
  int g = unhex(clr.substring(2, 4));
  int b = unhex(clr.substring(4, 6));
  int a = unhex(clr.substring(6, 8));
  return new int[] {r, g, b, a};
}
void greyscale() {
  for (int y = 0; y < height; ++y) {
    for (int x = 0; x < width; ++x) {
      img.set(x, y, color(grey(img.get(x, y))));
    }
  }
}
int plusmin() {
  int[] vals = {-1, 1};
  int index = int(random(0, 255));
  return vals[index % 2];
}
int[] range(int start, int stop, int step) {
  int[] box = {};
  for (int i=start; i<stop; i+=step) {
    box = append(box, i);
  }
  return box;
}
int randex(int max) {
  int[] rack = range(0, max, 1);
  return rack[int(random(0, 2*rack.length)) % rack.length];
}

int[] force(int x, int y) {
  x = int(map(x, 0, width, 1, width));
  y = int(map(y, 0, height, 1, height));
  int dx = int(map(x-mouseX, -width, width, -10, 10));
  int dy = int(map(y-mouseY, -height, height, -10, 10));
  int f = int(sqrt(pow(dx, 2) + pow(dy, 2)));
  return new int[] {dx/f, dy/f};
}

color localAverage(int x0, int y0) {
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
  int red = int(mean(reds));
  int green = int(mean(greens));
  int blue = int(mean(blues));
  // int alpha = int(mean(alphas));
  return colour(new int[] {red, green, blue});
}
float Size(int x,int y) {
  float s = map(brightness(img.get(x, y)), 0, 255, 0, tilesize);
  if (s < 2) {
    s = 0;
  }
  // return int(s);
  return s;
}
int[][] neighbors(int x, int y) {
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
int[][] nullNeighbors(int x0, int y0) {
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
float localSize(int x, int y) {
  if (nullNeighbors(x, y).length > 2) {
    return 0;
  }
  return Size(x, y);
}
float hypotenuse(int x, int y) {
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
  void boost(int ecks, int why) {
    x += ecks;
    y += why;
  }
  int dist() {
    int dx = abs(x - mouseX);
    int dy = abs(y - mouseY);
    // int mag = int(sqrt(pow(dx, 2) + pow(dy, 2)));
    return int(sqrt(pow(dx, 2) + pow(dy, 2)));
  }
  void update() {
    
  }
}
