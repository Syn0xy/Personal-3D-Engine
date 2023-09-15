package application.component;

import engine.component.Component;
import engine.component.physic.Rigidbody;
import engine.geometric.Vector3;
import engine.graphics.component.Camera;
import engine.input.Input;
import engine.input.KeyCode;
import engine.util.Cooldown;
import engine.util.Mathf;
import engine.util.Time;

public class PlayerController extends Component{
    private Camera camera;
    private Vector3 cameraRotation;
    private Rigidbody rigidbody;
    
    private double speed = 1;
    private double sensitivityX = 0.2;
    private double sensitivityY = 0.2;
    private double jumpForce = 2;
    private double jumpCooldown = 1;
    private Cooldown readyToJump;
    
    private double cameraRotationLimit = 90;
    
    private double xMov;
    private double zMov;
    private double xRot;
    private double yRot;

    private double xMovMultiplier;
    private double zMovMultiplier;

    private boolean jumping;
    private boolean sprinting;
    private boolean crouching;
    
    public void start(){
        readyToJump = new Cooldown(0);
        camera = getComponent(Camera.class);
        rigidbody = getComponent(Rigidbody.class);
        cameraRotation = camera.getTransform().getRotation();
    }

    public void update(){
        myInput();
        refreshMultiplier();
        performMovement();
    }
    
    public void onCollisionEnter(){
        System.out.println("Collision enter !");
    }

    private void myInput(){
        xMov = Input.getAxis("Horizontal");
        zMov = Input.getAxis("Vertical");
        xRot = Input.getAxis("Mouse X");
        yRot = Input.getAxis("Mouse Y");
        
        jumping = Input.getButton("Jump");
        sprinting = Input.getButton("Sprint");
        crouching = Input.getButton("Crouch");
    }

    private void performMovement(){
        if(jumping && isReadyToJump()) jump();
        if(crouching) crouch();
        //isGround();
        
        double x = xMov * xMovMultiplier * speed * Time.getDeltaTime();
        double z = zMov * zMovMultiplier * speed * Time.getDeltaTime();
        double mouseX = xRot * sensitivityX;
        double mouseY = yRot * sensitivityY;

        rigidbody.addForceForward(z);
        rigidbody.addForceSideward(x);
        
        if(Input.getKey(KeyCode.MOUSE_3)){
            cameraRotation.plusY(mouseX);
            cameraRotation.plusX(mouseY);
            cameraRotation.setX(Mathf.clamp(cameraRotation.getX(), -cameraRotationLimit, cameraRotationLimit));
        }
    }

    private void refreshMultiplier(){
        if(sprinting){
            xMovMultiplier = 2;
            zMovMultiplier = 2;
        }
        
        else if(crouching){
            xMovMultiplier = 0.5;
            zMovMultiplier = 0.5;
        }
        
        else if(!sprinting && !crouching){
            xMovMultiplier = 1;
            zMovMultiplier = 1;
        }
    }

    private boolean isReadyToJump(){
        if(readyToJump.isValid()){
            readyToJump.stop();
            return true;
        }
        return false;
    }

    // private void isGround(){
    //     readyToJump.end();
    // }

    private void jump(){
        readyToJump.set(jumpCooldown);
        rigidbody.addForce(Vector3.UP().multiplyCopy(jumpForce/8));
    }

    private void crouch(){
        rigidbody.addForce(Vector3.DOWN().multiplyCopy(jumpForce/4).multiplyCopy(Time.getDeltaTime()));
    }
}