String hexchars = "0123456789ABCDEF";


String heks(int n) {
  int rem = n % 16;
  if (n - rem == 0) {

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

    hx += heks(tone);

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

  return color(unhex("FFFFFFFF")) - colour(px);
}
int[] rgb(color px) {
  String clr = hex(px);  
  int r = unhex(clr.substring(0, 2));
  int g = unhex(clr.substring(2, 4));
  int b = unhex(clr.substring(4, 6));


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

    return int(sqrt(pow(dx, 2) + pow(dy, 2)));
  }
  void update() {
    
  }
}
