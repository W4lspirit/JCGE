package com.gg.jcge.displayable;

import com.gg.jcge.game.Displayable;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public abstract class Scene implements Displayable<SceneManager> { //FIXME : change class name for something more abstract

    private SceneManager sceneManager;
    private List<Pair<String, GameObject>> gameObjects;
    private Landscape Landscape;
    private Camera camera;

    public Scene() {
        this.camera = new Camera();
        this.Landscape = new Landscape();
        this.gameObjects = new ArrayList<>();
    }

    @Override
    public void init(SceneManager owner) {
        this.sceneManager = owner;
        this.Landscape.init(this);
        this.camera.init(this);
        this.init();
        this.getGameObjects().stream().forEach(go -> go.init(this));
    }

    protected abstract void init();

    @Override
    public void load() {
        this.Landscape.load();
        this.camera.load();
        this.getGameObjects().stream().forEach(GameObject::load);
    }

    @Override
    public void update() {
        this.camera.update();
        this.getGameObjects().stream().forEach(GameObject::update);
    }

    @Override
    public void render(Graphics g) {
        camera.render(g);
        this.getGameObjects().stream().forEach(go -> go.render(g));
    }

    /**
     * Recupere l'id du GameObject
     *
     * @param id L'id du GameObject
     *
     * @return Le GameObject
     */
    public GameObject getGameObject(String id) {
        return getGameObjects().stream().filter(go -> go.getId().equals(id)).findFirst().orElse(null);
    }

    public void addGameObject(String id, GameObject go) {
        this.gameObjects.add(new Pair<>(id, go));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects.stream().map(Pair::getValue).collect(Collectors.toList());
    }

    public Iterator<GameObject> iterateOverGO() {
        return this.getGameObjects().iterator();
    }

    public Landscape getLandscape() {
        return this.Landscape;
    }

    public Camera getCamera() {
        return camera;
    }

}
