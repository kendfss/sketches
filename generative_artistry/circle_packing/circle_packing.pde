/*
    AND(x,y) = xy
    NOT(x) = 1 − x
    OR(x,y) = 1 − (1 − x)(1 − y)
    BUT(x, expectation) = AND(NOT(expectation), x)
    
Terms
    Reduction
        an algorithm which consolidates a cluster of circles into a single one, usually dominated by the one with the lowest value of a common attribute
        
Simulations
    Reduction by 
        id
        radius
        x
        y
        xy
        age
        
*/


float rmin, rmax, dr, t, dt;

// -16118260, -13822431, -7878728, -7292265, -2498663, -12709098, -13487053, -3490868, -12828605, -8403582, -13752102, -13133838, -4345983, -16246715, -10009277, -3627877, -6220755
int total_circles, sat, light, hu, centerx, centery;
color bg;

// ArrayList<Circle> circles, newCircles, colliders; //position, radius
Cluster circles, newCircles, colliders; // Exdends ArrayList<Circle>
Bundle clusters; // Extends ArrayList<Cluster>
ArrayList visited; //position, radius
color[] colors;

void setup() {
    size(320, 320);
    centery = height / 2;
    centerx = width / 2;
    
    total_circles = 500;
    
    colors = new color[]{0};
    circles = new Cluster();
    colliders = new Cluster();
    clusters = new Bundle();
    visited = new ArrayList();
    // touchers = new ArrayList();
    // circles = new Circle[total_circles];
    // println(colorMode());
    colorMode(HSB);
    noStroke();
    
    // dr = 0.05;
    dr = 1;
    dt = 60;
    
    // sat = floor(random(0, 101));
    // light = floor(random(0, 101));
    hu = floor(random(120, 241));
    // hue(color);
    
    // bg = newColor();
    // bg = uniqueColor();
    bg = color(hu, floor(random(0, 256)), floor(random(0, 256)));
    println(bg);
    background(bg);
    colors = append(colors, bg);
    
    rmin = 2;
    rmax = (width + height) / 2;
    
    for (int i=0; i<total_circles; i++) {
        initCircle(i);
    }
    println("setup complete");
}

void draw() {
    background(bg);
    
    for (Circle c : circles) {
        c.update();
        c.draw();
        t += dt;
    }
    // removeAll(colliders);
    colliders.removeAll();
    println(circles.sizes());
    // remove_all(collie);
    println(circles.eldest().age, colliders.size());
}










// void initCircle(int index) {
Circle initCircle(int index) {
    Circle circle;
    circle = new Circle(
        index,
        floor(random(0, width)), 
        floor(random(0, height)), 
        uniqueColor(),
        rmin
    );
    // if (!coincides(circle)) {
    if (!circle.reachedLimit()) {
        circles.add(circle);
        colors = append(colors, circle.c);
        circle.draw();
        return circle;
    } 
    return initCircle(index);
}

// boolean coincides(Circle self) {
//     if (walled(self)) {
//         return true;
//     }
//     for (Circle other : circles) {
//         if (other.id == self.id) {
//             continue;
//         } else {
//             float ds = dist(other.x, other.y, self.x, self.y);
//             if (self.r + other.r >= ds*2 || ds==0) {
//                 self.c = other.c;
//                 return true;
//             }
//             if (collide(self, other)) {
//                 return true;
//             }
//         }
//     }
//     return false;
// }

// boolean collide(Circle self, Circle other) {
//     if (other.id != self.id) {
//         // float dx = other.x - self.x;
//         // float dy = other.y - self.y;
//         // float ds = sqrt(dx*dx + dy*dy);
//         // width
//         float ds = dist(other.x, other.y, self.x, self.y);
//         if (self.r + other.r >= ds || ds==0) {
//             self.c = other.c;
//             return true;
//         }
//     }
//     return false;
// }
// boolean walled(Circle self) {
//     if (self.x+self.r >= width || self.x-self.r <= 0) {
//         return true;
//     } else if (self.y+self.r >= height || self.y-self.r <= 0) {
//         return true;
//     }
//     return false;
// }

boolean used(color c) {
    if (c == bg) {
        return true;
    }
    for (color col : colors) {
        if (col == c) {
            return true;
        }
    }
    return false;
}
color uniqueColor() {
    color c = newColor();
    while (used(c)) {
        c = color(floor(random(0, 256)), floor(random(0, 256)), floor(random(0, 256)));
    }
    colors = append(colors, c);
    return c;
}
color newColor() {
    color c = color(hu, floor(random(0, 256)), floor(random(0, 256)));
    return c;
}

int index(color c) {
    for (int i = 0; i < colors.length; ++i) {
        if (colors[i] == c) {
            return i;
        }
    }
    return -1;
}

boolean bool() {
    // return a random boolean value
    return random(1) >= .5;
}

// void consolidate(Circle self, Circle other) {
//     ArrayList<Circle> newCircles = new ArrayList<Circle>();
//     color c = (self.c + other.c) / 2;
//     int x = (self.x + other.x) / 2;
//     int y = (self.y + other.y) / 2;
//     float r = (self.r + other.r) / 2;
//     circles.remove(self.id);
//     circles.remove(other.id);
//     int i = 0;
//     while (i < circles.size()) {
//         if (circles.get(i).id != i) {
//             circles.get(i).id = i;
//             newCircles.add(circles.get(i));
//         }
//         i++;
//     }
//     circles.add(new Circle(circles.size()-1, x, y, c, r));
// }

// void reduce() {
//     for (Circle self : circles) {
//         for (Circle other : circles) {
//             if (self.id!=other.id) {
//                 if (collide(self, other)) {
//                     consolidate(self, other);
//                 }
//             }
//         }
//     }
// }




ArrayList clone(ArrayList orig) {
    ArrayList c = new ArrayList();
    for (int i = 0; i < orig.size(); ++i) {
        c.add(orig.get(i));
    }
    return c;
}
ArrayList swap(ArrayList orig, int i, int j) {
    ArrayList n = new ArrayList();
    for (int k = 0; k < orig.size(); ++k) {
        if (k == i) {
            n.add(orig.get(j));
        } else if (k == j) {
            n.add(orig.get(i));
        } else {
            n.add(orig.get(k));
        }
    }
    return n;
}
// boolean sorted(ArrayList orig) {
//     for (int i = 1; i < orig.size(); ++i) {
//         int ii = orig.get(i);
//         int jj = orig.get(j);
//         if (ii < jj) {
//             return false;
//         }
//     }
//     return true;
// }
// ArrayList sort(ArrayList orig, int func)  {
//     ArrayList c = clone(orig);
//     // ArrayList n = new ArrayList();
//     while (!sorted(c)) {
//         for (i=1; i<orig.size(); i++) {
//             // if (orig.get(i) < orig.get(i-1)) {
//             if (func(i) < func(i-1)) {
//                 c = swap(c, i, i-1);
//             }
//         }
//     }
//     return c;
// }


