package de.ur.mi.bouncer.demo;

import de.ur.mi.bouncer.apps.BouncerApp;

/**
 * Created by Alexander Bazo on 12/10/2016.
 */
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
