/**
 *
 * Name: Carlos Mendoza
 * Description:For this assignment we will be implementing a version of a Binary Search Tree.
 */
package BinarySearchTree.doc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class tests all my methods from the BinarySearchTree class. This is where everything is tested.
 */
public class tester extends Application {

    private static ArrayList<Integer> nodes = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {

        music();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();


        BorderPane pane = new BorderPane();

        BSTPane visualizer = new BSTPane(tree);

        setPane(pane, visualizer, tree);
        setStage(pane, stage, "Binary Search Tree");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"This is a BinarySearchTree visualizer. This demonstrates the operations of insertion and deletion.\n\n" +
                "Insert button inserts a node, delete button deletes a node.", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();

    }
    MediaPlayer mp;
    public void music(){
        String s = "src/BinarySearchTree/doc/Amadeus/Future World/futureworld.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mp = new MediaPlayer(h);
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mp.seek(Duration.ZERO);
            }
        });
        mp.play();
    }

    public void setStage(BorderPane pane, Stage primaryStage, String title){
        Scene scene = new Scene(pane,1000,800);
        primaryStage.setTitle(title);
//        primaryStage.getIcons().add(new Image("BinarySearchTree/doc/Style/stars.jpg"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPane(BorderPane pane, BSTPane visualizer, BinarySearchTree<Integer> tree){
        pane.setCenter(visualizer);
        TextField textField = new TextField();
        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("Insert");
        insert.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        insert.setId("insertbutton");
        Button delete = new Button("Delete");
        delete.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        delete.setId("deletebutton");

        //Buttons for breadthfirst, preorder, inorder, postorder
        Button inorder = new Button("inorder");
        inorder.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        inorder.setId("inorder");
        Button preorder = new Button("preorder");
        preorder.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        preorder.setId("preorder");
        Button postorder = new Button("postorder");
        postorder.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        postorder.setId("postorder");
        Button breadthfirst = new Button("breadthfirst");
        breadthfirst.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        breadthfirst.setId("breadthfirst");
        Stop[] stops = new Stop[] { new Stop(0, Color.BLUE), new Stop(1, Color.RED)};

        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        Label label = new Label();
        label.setText("Enter a value");
        label.setFont(Font.font(20));
        label.setTextFill(lg1);
        addFunctionalities(textField, insert, delete, inorder, preorder, postorder, breadthfirst, tree, visualizer);


        HBox hBox = new HBox(5);
        hBox.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        hBox.setId("hboxcolor");
        hBox.getChildren().addAll(label,textField, insert, delete, inorder, preorder, postorder, breadthfirst);
        pane.setBottom(hBox);

    }

    public void addFunctionalities(TextField textField, Button insert, Button delete, Button inorder, Button preorder,
                                   Button postorder, Button breadthfirst, BinarySearchTree<Integer> tree, BSTPane visualizer){
        insert.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                nodes.add(key);
                if (tree.searchTree(key)) {
                    visualizer.displayTree();
                    visualizer.setStatus(key + " is already present!");
                } else {
                    tree.insert(key);
                    visualizer.displayTree();
                    visualizer.setStatus(key + " is inserted!");
                }
                textField.clear();
            }
        });

        delete.setOnAction(e->{
            int key = Integer.parseInt(textField.getText());
            if(!tree.searchTree(key)){
                visualizer.displayTree();
                visualizer.setStatus(key +" is not present!");
            }
            else{
                tree.delete(key);
                visualizer.displayTree();
                visualizer.setStatus(key+" is replaced by its predecessor and is deleted!");
            }
            textField.clear();
        });

        inorder.setOnAction(e-> {

            if (tree.isEmpty())
            {
                visualizer.displayTree();
                visualizer.setStautsProperties("Tree is empty");
            }
            else
            {
                visualizer.displayTree();
                visualizer.setStautsProperties("IN-ORDER:" + tree.inorder2().toString());
            }
        });

        preorder.setOnAction(e-> {
                    if (tree.isEmpty())
                    {
                        visualizer.displayTree();
                        visualizer.setStautsProperties("Tree is empty");
                    }
                    else {
                        visualizer.displayTree();
                        visualizer.setStautsProperties("PRE-ORDER:" + tree.preorder2().toString());
                    }
        });

        postorder.setOnAction(e-> {
                    if (tree.isEmpty())
                    {
                        visualizer.displayTree();
                        visualizer.setStautsProperties("Tree is empty");
                    }
                    else {
                        visualizer.displayTree();
                        visualizer.setStautsProperties("POST-ORDER:" + tree.postorder2().toString());
                    }
        });


        breadthfirst.setOnAction(e-> {
                    if (tree.isEmpty())
                    {
                        visualizer.displayTree();
                        visualizer.setStautsProperties("Tree is empty");
                    }
                    else {
                        visualizer.displayTree();
                        visualizer.setStautsProperties("BREADTH-FIRST:" + tree.breadthFirst2().toString());
                    }
        });


    }
}