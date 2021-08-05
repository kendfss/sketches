class Cluster extends ArrayList<Circle> {
    Circle first() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.id < dom.id) {
                dom = c;
            }
        }
        return dom;
    }
    Circle last() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.id > dom.id) {
                dom = c;
            }
        }
        return dom;
    }
    Circle largest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.r > dom.r) {
                dom = c;
            }
        }
        return dom;
    }
    Circle smallest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.r < dom.r) {
                dom = c;
            }
        }
        return dom;
    }
    Circle eldest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.age > dom.age) {
                dom = c;
            }
        }
        return dom;
    }
    Circle youngest() {
        Circle dom = this.get(0);
        for (Circle c : this) {
            if (c.age < dom.age) {
                dom = c;
            }
        }
        return dom;
    }
    void reduce() {
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
    ArrayList ages() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.age);
        }
        return keys;
    }
    ArrayList sizes() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.r);
        }
        return keys;
    }
    ArrayList colors() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.c);
        }
        return keys;
    }
    ArrayList ids() {
        ArrayList keys = new ArrayList();
        for (Circle c : this) {
            keys.add(c.id);
        }
        return keys;
    }
    int idsum() {
        int val = 0;
        for (Circle c : this) {
            val += c.id;
        }
        return val;
    }
    Circle dominant() {
        return this.first();
    }
    boolean has(Circle it) {
        for (Circle that : this) {
            if (that.id==it.id) {
                return true;
            }
        }
        return false;
    }
    void removeAll() {
        int len = this.size();
        for (int i = 0; i < len; ++i) {
            this.remove(0);
        }
    }
    void fillFrom(Cluster other) {
        this.removeAll();
        for (Circle c : other) {
            this.add(c);
        }
    }
    void swap(int i, int j) {
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
    Cluster clone() {
        Cluster c = new Cluster();
        for (int i = 0; i < this.size(); ++i) {
            c.add(this.get(i));
        }
        return c;
    }
    boolean equals(Cluster that) {
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
    void killAll() {
        for (Circle c : this) {
            c.dead = true;
        }
    }
}
