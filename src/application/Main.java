package application;

import java.awt.Color;

import application.component.PlayerController;
import engine.component.collider.SphereCollider;
import engine.component.light.Light;
import engine.component.physic.Rigidbody;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.scene.GameObject;
import engine.scene.PrimitiveType;
import engine.scene.management.GameScene;
import engine.util.Mathf;

public class Main {
    public static void main(String[] args) {
        GameScene gameScene = new GameScene();
        
        GameObject player = new GameObject("Player");
        player.addComponent(new Camera(90, Color.BLUE));
        // player.addComponent(new SphereCollider());
        player.addComponent(new Rigidbody());
        // player.addComponent(new Light());
        player.addComponent(new PlayerController());
        player.getTransform().getPosition().set(10, 30, 0);
        player.getTransform().getRotation().set(-75, 0, 0);
        
        GameObject plane = GameObject.createPrimitive(PrimitiveType.CUBE);
        plane.getTransform().getScale().set(2, 2, 2);
        
        gameScene.instantiate(player);
        gameScene.instantiate(plane);
        
        gameScene.start();
    }

    public static void spawnCubes(GameScene gameScene){
        for (int i = 0; i < 50; i++) {
            GameObject cube = GameObject.createPrimitive();
            cube.getTransform().getPosition().set(new Vector3(Mathf.random(i), 0, Mathf.random(i)));
            gameScene.instantiate(cube);
        }
    }
}
