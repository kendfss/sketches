PImage img;
float tilewidth, tileheight, tilesize;
int tilesx, tilesy, step, t, hour, tmax;

void path(int x, int y, int w, int h) {
    if (random(0, 1) > .5) {
        line(x, y, x+w, y+h);
    } else {
        line(x+w, y, x, y+h);
    }
    
}


void setup() {
    size(320, 320);
    step = 20;
    hour = 160;
    tmax = 1;
}

void draw() {
    // line(0, 0, width, height);
    // path(0, 0, width, height);
    // if (t % hour == 0) {
    if (t < tmax) {
        for (int y=0; y<height; y+=step) {
            for (int x=0; x<width; x+=step) {
                path(x, y, step, step);
            }
        }
        t++;
    }
    
    
}

void keyPressed() {
    tmax++;
}
