package application;

import application.component.PlayerController;
import engine.component.collider.SphereCollider;
import engine.component.light.Light;
import engine.component.physic.Rigidbody;
import engine.graphics.component.Camera;
import engine.scene.GameObject;
import engine.scene.management.GameScene;

public class Main {
    public static void main(String[] args) {
        GameScene gameScene = new GameScene();

        GameObject cube = GameObject.createPrimitive();
        cube.getTransform().getPosition().set(0, 0, 10);
        
        GameObject player = new GameObject("Player");
        player.addComponent(new Camera());
        player.addComponent(new SphereCollider());
        player.addComponent(new Rigidbody());
        player.addComponent(new Light());
        player.addComponent(new PlayerController());
        
        GameObject camera = new GameObject("Cam");
        camera.addComponent(new Camera());
        
        gameScene.instantiate(cube);
        gameScene.instantiate(player);
        gameScene.instantiate(camera);

        gameScene.start();
    }
}
