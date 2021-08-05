int rect_width, rect_height, radius;


void setup() {
  size(900, 900);
  rect_width = rect_height = radius = 600;
}
 // ​
void draw() {
  background(0);
  rectMode(CENTER);
  stroke(#f1f1f1);
  strokeWeight(4);
  noFill();

  float wave = sin(frameCount * 0.01) * 450;

  // Add the wave to the x-position
  rect(width/2 + wave, height/2, rect_width, rect_height);
  // ​
  push();
  translate(width/2, height/2);
  float x1 = 0;
  float y1 = -300;
  float x2 = 300;
  float y2 = 300;
  float x3 = -300;
  float y3 = 300;
  triangle(x1, y1, x2, y2, x3, y3);
  pop();
  // ​
  // subtract the wave-value from the x-position
  ellipse(width/2 - wave, height/2, radius, radius);
}
