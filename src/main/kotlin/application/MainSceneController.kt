package main.kotlin.application

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Group
import javafx.scene.SceneAntialiasing
import javafx.scene.SubScene
import javafx.scene.control.MenuBar
import javafx.scene.layout.*
import javafx.scene.paint.Color
import main.kotlin.elements.BetterButton
import main.kotlin.view.SphereView


class MainSceneController {
    @FXML
    lateinit var sphereContainer: AnchorPane;


    lateinit var sphereSubScene: SubScene;
    lateinit var objectParent:Group;

    lateinit var sphereView:SphereView;

    lateinit var exitButton:BetterButton;//ide kell a lateinit
    lateinit var rescaleButton:BetterButton;
    lateinit var minimizeButton:BetterButton;

    fun initScene()
    {
        sphereContainer.children.clear();

        objectParent=Group();
        sphereSubScene = SubScene(objectParent, sphereContainer.width, sphereContainer.width, true, SceneAntialiasing.BALANCED);
        sphereSubScene.fill= Color.BLACK;

        sphereContainer.children.add(sphereSubScene);

        sphereView= SphereView(sphereSubScene, sphereContainer);

        //resize children as well
        sphereContainer.layoutBoundsProperty().addListener() { _, oldValue, newValue ->
            if (oldValue.width != newValue.width || oldValue.height != newValue.height) {
                sphereSubScene.width=newValue.width;
                sphereSubScene.height=newValue.height;

                sphereView.onResize();
            }
        }

    }
}