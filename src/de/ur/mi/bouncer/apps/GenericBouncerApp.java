package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.events.DefaultEventBus;
import de.ur.mi.bouncer.events.EventBus;
import de.ur.mi.bouncer.events.OnWorldChangedListener;
import de.ur.mi.bouncer.ui.GraphicsContext;
import de.ur.mi.bouncer.ui.WorldScene;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;
import de.ur.mi.bouncer.world.loader.WorldLoader;
import de.ur.mi.graphicsapp.GraphicsApp;

public abstract class GenericBouncerApp<T extends Bouncer> extends GraphicsApp
		implements GraphicsContext, OnWorldChangedListener {
	private static final long serialVersionUID = 4728854238050763260L;
	private final AppConfiguration appConfig;
	private WorldLoader worldLoader;
	protected T bouncer;
	private TwoDimensionalWorld world;
	private WorldScene worldScene;
	private int windowSize;
	private int fps = 7;
	private final int INCREASE_AMOUNT = 3;

	private static final int KEY_CODE_F4 = 115;
	private static final int KEY_CODE_F5 = 116;

	public GenericBouncerApp() {
		this(new WorldLoader());
	}

	public GenericBouncerApp(WorldLoader worldLoader) {
		super();
		appConfig = new AppConfiguration();
		this.worldLoader = worldLoader;
	}

	public void setWorldLoader(WorldLoader worldLoader) {
		this.worldLoader = worldLoader;
	}

	@Override
	public void setup() {
		super.setup();
		setupApplet();
		world = TwoDimensionalWorld.emptyWorld();
		EventBus eventBus = newEventBus();
		setupBouncer(eventBus);
		eventBus.addOnWordChangedListener(this);
		worldScene = new WorldScene(world, bouncer, windowSize, appConfig);
		startBounceThread();
	}

	public void setupBouncer(EventBus eventBus) {
		bouncer = createBouncer();
		bouncer.setEventBus(eventBus);
	}

	private EventBus newEventBus() {
		return new DefaultEventBus();
	}

	private void setupApplet() {
		windowSize = appConfig.windowSizeFor(displayHeight);
		size(windowSize, windowSize);
		//Processing 3
		//smooth(appConfig.smoothLevel());
	}

	public abstract T createBouncer();

	private void startBounceThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				bounce();
			}
		}).start();
	}

	public abstract void bounce();

	public final void loadMap(String mapName) {
		loadLocalMap(mapName);
	}

	public final void loadLocalMap(String mapName) {
		world = worldLoader.loadLocalMap(mapName);
		if (worldScene != null) {
			worldScene.setWorld(world);
		}
		bouncer.placeInWorld(world);
	}

	public final void loadOnlineMap(String mapName) {
		world = worldLoader.loadOnlineMap(mapName);
		if (worldScene != null) {
			worldScene.setWorld(world);
		}
		worldScene.setWorld(world);
		bouncer.placeInWorld(world);
	}

	public final void setAnimationSpeedInFramesPerSecond(int fps) {
		appConfig.setFrameRate(fps);
	}

	public void onWorldChanged() {
		try {
			Thread.sleep(1000 / appConfig.frameRate());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		world.purgeCollisions();
	}

	@Override
	public void draw() {
		//Processing 3
		//super.draw();
		worldScene.draw(this);
	}

	@Override
	public void keyPressed(processing.event.KeyEvent event) {
		super.keyPressed(event);
		if (event.getKeyCode() == KEY_CODE_F4) {
			decreaseSpeed();
		}else if (event.getKeyCode() == KEY_CODE_F5) {
			increaseSpeed();
		}
	}

	public void rectModeCorner() {
		rectMode(CORNER);
	}

	private void increaseSpeed(){
		fps += INCREASE_AMOUNT;
		setAnimationSpeedInFramesPerSecond(fps);
	}

	private void decreaseSpeed(){
		fps -= INCREASE_AMOUNT;
		if(fps < 1){
			fps = 1;
		}
		setAnimationSpeedInFramesPerSecond(fps);
	}

}
