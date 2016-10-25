package de.ur.mi.bouncer.demo;

import de.ur.mi.bouncer.apps.BouncerApp;

public class BouncerDemo extends BouncerApp {
    @Override
    public void bounce() {
        loadMap("Strasse");
        while(true) {
            bouncer.move();
            bouncer.move();
            bouncer.move();
            bouncer.move();
            bouncer.move();
            bouncer.move();
            bouncer.turnLeft();
            bouncer.turnLeft();
        }
    }
}
