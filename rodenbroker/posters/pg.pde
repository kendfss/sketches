void drawPg(){
  
  pg.beginDraw();

  pg.background(#111111);
  pg.imageMode(CENTER);

  // Display Image

  pg.push();
  pg.translate(pg.width/2, pg.height/2);
  float wave1 = map(sin(radians(frameCount)),-1,1,3,1.6);
  pg.scale(wave1);
  pg.image(img, 0, 0);
  pg.pop();

  // Define Style

  pg.textFont(sans);
  pg.textAlign(CENTER, CENTER);
  pg.textSize(900);
  pg.fill(#111111);

  // Display Type
  
  String txt = "POMPEIJ";
  float textWidth = pg.textWidth(txt);
  
  float wave2 = map(tan(radians(frameCount)),-1,1,-100,100);
  
  pg.push();
  pg.translate(pg.width/2 +wave2, pg.height/2-40);
  pg.text(txt, 0, 0);
  pg.pop();

  pg.endDraw();
}
