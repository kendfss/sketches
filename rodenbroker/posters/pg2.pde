void drawPg2() {

  pg2.beginDraw();

  pg2.background(#111111);
  pg2.imageMode(CENTER);

  // Display Image

  pg2.push();
  pg2.translate(pg.width/2, pg.height/2);
  float wave1 = map(sin(radians(frameCount)), -1, 1, 1.3, 2.4);
  pg2.scale(wave1);
  pg2.image(img2, 0, 0);
  pg2.pop();

  // Define Style

  pg2.textFont(sans);
  pg2.textAlign(CENTER, CENTER);
  pg2.textSize(900);
  pg2.fill(#f1f1f1);

  // Display Type

  String txt = "NAPLES";
  float textWidth = pg.textWidth(txt);

  float wave2 = map(tan(radians(frameCount)), -1, 1, -100, 100);

  pg2.push();
  pg2.translate(pg.width/2 -wave2, pg.height/2-40);
  pg2.text(txt, 0, 0);
  pg2.pop();

  pg2.endDraw();
}
