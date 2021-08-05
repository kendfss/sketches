int x = 0;

void setup() {
  fullScreen(P2D, SPAN);
  smooth(4);
}

void draw() {
  background(0);
  ellipse(x, height/2, height/4, height/4);
  x += 1;
}


// ------------------------------------------------

// void setup() {
//   size(400, 400, P3D);  // first try this
//   // size(400, 400); // second try this
//   background(-1);
// }
// void draw() {
//   strokeWeight(6);
//   line(pmouseX, pmouseY, mouseX, mouseY);
// }
