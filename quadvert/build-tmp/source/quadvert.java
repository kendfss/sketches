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

public class quadvert extends PApplet {
  public void setup() {
noFill();
strokeWeight(4);
beginShape();
vertex(20, 20);
quadraticVertex(80, 20, 50, 50);
endShape();

// example pic

noFill();
strokeWeight(4);
beginShape();
vertex(20, 20);
quadraticVertex(80, 20, 50, 50);
quadraticVertex(20, 80, 80, 80);
vertex(80, 60);
endShape();
    noLoop();
  }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "quadvert" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
