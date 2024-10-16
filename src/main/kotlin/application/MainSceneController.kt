package main.kotlin.application

import javafx.fxml.FXML
import javafx.geometry.Point3D
import javafx.scene.DirectionalLight
import javafx.scene.Group
import javafx.scene.PerspectiveCamera
import javafx.scene.SceneAntialiasing
import javafx.scene.SubScene
import javafx.scene.effect.Light
import javafx.scene.effect.Lighting
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Sphere
import javafx.scene.transform.Rotate
import javafx.scene.transform.Translate


class MainSceneController {
    @FXML
    lateinit var sphereView: AnchorPane;


    lateinit var sphereSubScene: SubScene;
    var objectParent:Group=Group();

    fun initScene()
    {
        var sphere:Sphere=Sphere();
        sphere.apply{
            transforms.clear();
            transforms.addAll(Translate(0.0,0.0,10.0));
        }

        var camera:PerspectiveCamera = PerspectiveCamera(true);
        camera.apply {
            nearClip=1.0;
            farClip=100.0;

            fieldOfView=45.0;

            var direction: Point3D = Point3D(sphere.translateX-camera.translateX,sphere.translateY-camera.translateY,sphere.translateZ-camera.translateZ);
            direction.normalize();
            var rotation:Rotate =Rotate(0.0, Point3D(0.0, 1.0, 0.0));
            var translate:Translate=Translate(0.0,0.0,0.0);
            transforms.clear();
            transforms.addAll(rotation,translate);
        }

        var light:DirectionalLight = DirectionalLight();
        light.apply{
            var rotation:Rotate=Rotate(30.0,Point3D(1.0,0.0,0.0));
            var translation:Translate=Translate(0.0,10.0,0.0);
            transforms.clear();
            transforms.addAll(rotation,translation);
        }


        objectParent.children.add(sphere);
        objectParent.children.add(light);
        sphereSubScene = SubScene(objectParent, sphereView.width, sphereView.width, true, SceneAntialiasing.BALANCED);
        sphereView.children.clear();
        sphereSubScene.camera=camera;
        sphereSubScene.fill= Color.BLACK;

        sphereView.children.add(sphereSubScene);
        sphereView.layoutBoundsProperty().addListener() { _, oldValue, newValue ->
            if (oldValue.width != newValue.width || oldValue.height != newValue.height) {
                sphereSubScene.width=newValue.width;
                sphereSubScene.height=newValue.height;
            }
        }
    }
}