class Bundle extends ArrayList<Cluster> {
    boolean has(Cluster it) {
        for (Cluster mine : this) {
            if (it.equals(mine)) {
                return true;
            }
        }
        return false;
    }
    void removeAll() {
        final int len = this.size();
        for (int i = 0; i < len; ++i) {
            this.remove(0);
        }
    }
    void fillFrom(Bundle other) {
        this.removeAll();
        for (Cluster c : other) {
            this.add(c);
        }
    }
    void swap(int i, int j) {
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
    Bundle clone() {
        Bundle c = new Bundle();
        for (int i = 0; i < this.size(); ++i) {
            c.add(this.get(i));
        }
        return c;
    }
    boolean equals(Bundle that) {
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
    void killAll() {
        for (Cluster c : this) {
            c.killAll();
        }
    }
}
