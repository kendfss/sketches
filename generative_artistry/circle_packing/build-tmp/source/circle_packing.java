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

public class circle_packing extends PApplet {

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
int bg;

// ArrayList<Circle> circles, newCircles, colliders; //position, radius
Cluster circles, newCircles, colliders; // Exdends ArrayList<Circle>
Bundle clusters; // Extends ArrayList<Cluster>
ArrayList visited; //position, radius
int[] colors;

public void setup() {
    
    centery = height / 2;
    centerx = width / 2;
    
    total_circles = 500;
    
    colors = new int[]{0};
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

public void draw() {
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
public Circle initCircle(int index) {
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

public boolean used(int c) {
    if (c == bg) {
        return true;
    }
    for (int col : colors) {
        if (col == c) {
            return true;
        }
    }
    return false;
}
public int uniqueColor() {
    int c = newColor();
    while (used(c)) {
        c = color(floor(random(0, 256)), floor(random(0, 256)), floor(random(0, 256)));
    }
    colors = append(colors, c);
    return c;
}
public int newColor() {
    int c = color(hu, floor(random(0, 256)), floor(random(0, 256)));
    return c;
}

public int index(int c) {
    for (int i = 0; i < colors.length; ++i) {
        if (colors[i] == c) {
            return i;
        }
    }
    return -1;
}

public boolean bool() {
    // return a random boolean value
    return random(1) >= .5f;
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




public ArrayList clone(ArrayList orig) {
    ArrayList c = new ArrayList();
    for (int i = 0; i < orig.size(); ++i) {
        c.add(orig.get(i));
    }
    return c;
}
public ArrayList swap(ArrayList orig, int i, int j) {
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


class Bundle extends ArrayList<Cluster> {
    public boolean has(Cluster it) {
        for (Cluster mine : this) {
            if (it.equals(mine)) {
                return true;
            }
        }
        return false;
    }
    public void removeAll() {
        final int len = this.size();
        for (int i = 0; i < len; ++i) {
            this.remove(0);
        }
    }
    public void fillFrom(Bundle other) {
        this.removeAll();
        for (Cluster c : other) {
            this.add(c);
        }
    }
    public void swap(int i, int j) {
        Bundle n = new Bundle();
        for (int k = 0; k < this.size(); ++k) {
            if (k == i) {
                n.add(this.get(j));
            } else if (k == j) {
                n.add(this.get(i));
            } else {
                n.add(this.get(k));
            }
        }
        this.fillFrom(n);
    }
    public Bundle clone() {
        Bundle c = new Bundle();
        for (int i = 0; i < this.size(); ++i) {
            c.add(this.get(i));
        }
        return c;
    }
    public boolean equals(Bundle that) {
        // order sensitive
        if (this.size() != that.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); ++i) {
            Cluster mine = this.get(i);
            Cluster its = that.get(i);
            if (mine.equals(its)) {
                continue;
            }
            return false;
        }
        return true;
    }
    public void killAll() {
        for (Cluster c : this) {
            c.killAll();
        }
    }
}
class Circle {
    int x, y, c, id, age;
    boolean dead;
    float r;
    Circle(int identifier, int ecks, int why, int col, float radius) {
        this.id = identifier;
        this.x = ecks;
        this.y = why;
        this.c = col;
        this.r = radius;
        this.age = 0;
        this.dead = false;
    }
    public void draw() {
        fill(this.c);
        ellipse(this.x, this.y, this.r, this.r);
        fill(negative(this.c));
        ellipse(this.x, this.y, 1, 1);
        noFill();
    }
    public void update() {
        if (this.r < rmin) {
            this.r = rmin;
        } else if (this.r > rmax) {
            this.r = rmax;
        }
        if (this.r + dr < rmax) {
            this.r += dr;
        }
        
        // if (walled(this) || colliders.has(this) || this.cluster().size() > 1) {
        if (this.reachedLimit()) {
            this.r -= dr;
        }
        this.age++;
    }
    public void shake(int times) {
        for (int i = 0; i < times; ++i) {
            if (bool()) {
                this.x += bool() ? 1 : -1;
                this.x %= width;
            } else {
                this.y += bool() ? 1 : -1;
                this.y %= width;
            }
        }
    }
    public int ind() {
        return index(this.c);
    }
    // String repr() {
    //     id = "(id=" + String(this.id) + ", ";
    //     x = "x=" + String(this.x) + ", ";
    //     y = "y=" + String(this.y) + ", ";
    //     c = "c=" + String(this.c) + ", ";
    //     r = "r=" + String(this.r) + ")";
    //     return "Circle" + id + x + y + c + r;
    // }
    
    public boolean touches(Circle that) {
        if (that.id != this.id) {
            float dx = that.x - this.x;
            float dy = that.y - this.y;
            float ds0 = sqrt(dx*dx + dy*dy);
            // float ds = dist(that.x, that.y, this.x, this.y);
            float ds = PApplet.parseInt(dist(that.x, that.y, this.x, this.y));
            float r = PApplet.parseInt(this.r + that.r);
            // if (this.r + that.r >= ds*2 || ds==0) {
            if (ds - r <= 0) {
                colliders.add(this);
                colliders.add(that);
                println(this.id, that.id, ds, ds0, r, this.r, that.r);
                that.c = this.c;
                // fill(negative(bg));
                stroke(negative(bg));
                line(this.x, this.y, that.x, that.y);
                noStroke();
                return true;
            }
        }
        return false;
    }
    public Cluster cluster() {
        Cluster bag = new Cluster();
        bag.add(this);
        for (Circle that : circles) {
            if (this.touches(that)) {
                bag.add(that);
            }
        }
        return bag;
    }
    public boolean equals(Circle that) {
        // death-insensitive
        return that.x==this.x && that.y==this.y && that.id==this.id && that.id==this.id && that.age==this.age;
    }
    public boolean walls() {
        if (this.x+this.r >= width || this.x-this.r <= 0) {
            return true;
        } else if (this.y+this.r >= height || this.y-this.r <= 0) {
            return true;
        }
        return false;
    }
    public boolean reachedLimit() {
        return this.walls() || colliders.has(this) || this.cluster().size() > 1;
    }
}
class Cluster extends ArrayList<Circle> {
    public Circle first() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.id < dom.id) {
                dom = c;
            }
        }
        return dom;
    }
    public Circle last() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.id > dom.id) {
                dom = c;
            }
        }
        return dom;
    }
    public Circle largest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.r > dom.r) {
                dom = c;
            }
        }
        return dom;
    }
    public Circle smallest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.r < dom.r) {
                dom = c;
            }
        }
        return dom;
    }
    public Circle eldest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.age > dom.age) {
                dom = c;
            }
        }
        return dom;
    }
    public Circle youngest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.age < dom.age) {
                dom = c;
            }
        }
        return dom;
    }
    public void reduce() {
        Circle dom = this.dominant();
        for (Circle cir: this) {
            if (dom.id != cir.id) {
                dom.x += cir.x;
                dom.y += cir.y;
                dom.c += cir.c;
                dom.r += cir.r;
                dom.age += cir.age;
                cir.dead = true;
            }
        }
        dom.x /= this.size();
        dom.y /= this.size();
        dom.c /= this.size();
        dom.r /= this.size();
        dom.id += this.idsum() / this.size();
        newCircles.add(dom);
        removeAll(this);
    }
    public ArrayList ages() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.age);
        }
        return keys;
    }
    public ArrayList sizes() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.r);
        }
        return keys;
    }
    public ArrayList colors() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.c);
        }
        return keys;
    }
    public ArrayList ids() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.id);
        }
        return keys;
    }
    public int idsum() {
        int val = 0;
        for (Circle c : this) {
            val += c.id;
        }
        return val;
    }
    public Circle dominant() {
        return this.first();
    }
    public boolean has(Circle it) {
        for (Circle that : this) {
            if (that.id==it.id) {
                return true;
            }
        }
        return false;
    }
    public void removeAll() {
        int len = this.size();
        for (int i = 0; i < len; ++i) {
            this.remove(0);
        }
    }
    public void fillFrom(Cluster other) {
        this.removeAll();
        for (Circle c : other) {
            this.add(c);
        }
    }
    public void swap(int i, int j) {
        Cluster n = new Cluster();
        for (int k = 0; k < this.size(); ++k) {
            if (k == i) {
                n.add(this.get(j));
            } else if (k == j) {
                n.add(this.get(i));
            } else {
                n.add(this.get(k));
            }
        }
        this.fillFrom(n);
    }
    public Cluster clone() {
        Cluster c = new Cluster();
        for (int i = 0; i < this.size(); ++i) {
            c.add(this.get(i));
        }
        return c;
    }
    public boolean equals(Cluster that) {
        // order sensitive
        if (this.size() != that.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); ++i) {
            Circle mine = this.get(i);
            Circle its = that.get(i);
            if (mine.equals(its)) {
                continue;
            }
            return false;
        }
        return true;
    }
    public void killAll() {
        for (Circle c : this) {
            c.dead = true;
        }
    }
}
String hexchars = "0123456789ABCDEF";


public String heks(int n) {
  int rem = n % 16;
  if (n - rem == 0) {

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

    hx += heks(tone);

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

  return color(unhex("FFFFFFFF")) - colour(px);
}
public int[] rgb(int px) {
  String clr = hex(px);  
  int r = unhex(clr.substring(0, 2));
  int g = unhex(clr.substring(2, 4));
  int b = unhex(clr.substring(4, 6));


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

    return PApplet.parseInt(sqrt(pow(dx, 2) + pow(dy, 2)));
  }
  public void update() {
    
  }
}
  public void settings() {  size(320, 320); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "circle_packing" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
