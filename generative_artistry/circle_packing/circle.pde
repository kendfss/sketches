class Circle {
    int x, y, c, id, age;
    boolean dead;
    float r;
    Circle(int identifier, int ecks, int why, color col, float radius) {
        this.id = identifier;
        this.x = ecks;
        this.y = why;
        this.c = col;
        this.r = radius;
        this.age = 0;
        this.dead = false;
    }
    void draw() {
        fill(this.c);
        ellipse(this.x, this.y, this.r, this.r);
        fill(negative(this.c));
        ellipse(this.x, this.y, 1, 1);
        noFill();
    }
    void update() {
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
    void shake(int times) {
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
    int ind() {
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
    
    boolean touches(Circle that) {
        if (that.id != this.id) {
            float dx = that.x - this.x;
            float dy = that.y - this.y;
            float ds0 = sqrt(dx*dx + dy*dy);
            // float ds = dist(that.x, that.y, this.x, this.y);
            float ds = int(dist(that.x, that.y, this.x, this.y));
            float r = int(this.r + that.r);
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
    Cluster cluster() {
        Cluster bag = new Cluster();
        bag.add(this);
        for (Circle that : circles) {
            if (this.touches(that)) {
                bag.add(that);
            }
        }
        return bag;
    }
    boolean equals(Circle that) {
        // death-insensitive
        return that.x==this.x && that.y==this.y && that.id==this.id && that.id==this.id && that.age==this.age;
    }
    boolean walls() {
        if (this.x+this.r >= width || this.x-this.r <= 0) {
            return true;
        } else if (this.y+this.r >= height || this.y-this.r <= 0) {
            return true;
        }
        return false;
    }
    boolean reachedLimit() {
        return this.walls() || colliders.has(this) || this.cluster().size() > 1;
    }
}
