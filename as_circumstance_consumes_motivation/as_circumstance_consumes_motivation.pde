float ds, dx, dy, rx, ry, r0, r;
PVector v1, v2;
int tmax, t;



void setup() {
    size(300, 300);
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

void draw() {
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
        path(int(v1.x), int(v1.y), int(dx), int(dy));
        noStroke();
        
        
        // v1.x = abs((v1.x - dx) % width);
        // v1.x = abs((v1.x + random(-width-1, width+1)) % width);
        // v1.x = abs((v1.x + random(0, dx+1)) % width);
        v1.x = (width / 2) + (r * cos(t));
        // v1.y = abs((v1.y - dy) % height);
        // v1.y = abs((v1.y + random(-height-1, height+1)) % height);
        // v1.y = abs((v1.y + random(0, dy+1)) % height);
        v1.y = (height / 2) + (r * sin(t));
        v2.x = lerp(v1.x, v2.x, .7);
        v2.y = lerp(v1.y, v2.y, .7);
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

void keyPressed() {
    tmax++;
}

void path(int x, int y, int w, int h) {
    if (random(0, 1) > .5) {
        line(x, y, x+w, y+h);
    } else {
        line(x+w, y, x, y+h);
    }
    
}
