package de.ur.mi.bouncer.apps.tasks;

import de.ur.mi.bouncer.apps.WorldBouncerApp;
import de.ur.mi.bouncer.subtypes.WorldBouncer;
import de.ur.mi.bouncer.world.FieldColor;

@SuppressWarnings("serial")
public class Fliegen extends WorldBouncerApp {

	@Override
	public WorldBouncer createBouncer() {
		return new WorldBouncer();
	}

	@Override
	public void bounce() {
		loadLocalMap("Empty");
		bouncer.setStopsOnError(true);
		bouncer.turnLeft();
		bouncer.turnLeft();
		bouncer.move();
		bouncer.move();
		bouncer.move();
		bouncer.move();
		bouncer.move();
		for (int rowIdx = 0; rowIdx < bouncer.sizeOfWorld(); rowIdx++) {
			for (int colIdx = 0; colIdx < bouncer.sizeOfWorld(); colIdx++) {
				bouncer.jumpToRowAndColumn(rowIdx, colIdx);
				if (colIdx % 2 == 0) {
					if (rowIdx % 2 == 0) {
						bouncer.paintField(FieldColor.RED);
					} else {
						bouncer.paintField(FieldColor.BLUE);
					}
				} else {
					if (rowIdx % 2 == 0) {
						bouncer.paintField(FieldColor.GREEN);
					} else {
						bouncer.paintField(FieldColor.RED);
					}
				}
			}
		}
	}

}
