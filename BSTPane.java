package BinarySearchTree.doc;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;

public class BSTPane extends Pane {


    private BinarySearchTree<Integer> tree;
    private double radius = 19;
    private double vGap = 55;
    private double hGap = 500;

    protected BSTPane(){}

    BSTPane(BinarySearchTree<Integer> tree){
        this.tree = tree;
        this.getStylesheets().add("BinarySearchTree/doc/Style/Styles.css");
        this.getStyleClass().add("background");

        //create Title
        Text text = new Text();
        text.setX(10.0f);
        text.setY(10.0f);
        text.setCache(true);
        text.setText("BINARY SEARCH TREE");
        text.setId("fancytext");


        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        text.setEffect(reflect);
        text.setTranslateY(0);

        VBox treetitle = new VBox(10);
        treetitle.getChildren().add(text);
        treetitle.setAlignment(Pos.CENTER);

        getChildren().add(treetitle);
        setStatus("Tree is Empty");
        setStautsProperties("IN-ORDER - PRE-ORDER - POST-ORDER - BREADTH-FIRST");
    }

    public void setStatus(String message) {
        Text t = new Text(message);
        t.setX(500);
        t.setY(700);
        t.setId("message");

        InnerShadow in = new InnerShadow();
        in.setOffsetX(4.0f);
        in.setOffsetY(4.0f);

        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        t.setEffect(reflect);
        t.setTranslateY(0);
        getChildren().add(t);
    }

    public void setStautsProperties(String message){

        Text t = new Text(message);
        t.setX(100);
        t.setY(300);
        t.setId("messageproperties");

        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        t.setEffect(reflect);
        t.setTranslateY(0);

        getChildren().add(t);

    }

    public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth()/4, Color.BLUE);
        }
    }

    public void displayTree(BstNode<Integer> root,double x, double y, double hGap, Color color){

        if(root.left != null){

            Line l = new Line();
            l.setStartX(x - hGap);
            l.setStartY(y + vGap);
            l.setFill(Color.RED);
            l.setStroke((Color.RED));
            l.setEndX(x);
            l.setEndY(y);
            getChildren().add(l);
            displayTree(root.left,x - hGap, y + vGap, hGap / 2, color);
            System.out.println(x);
            System.out.println(y);
        }

        if (root.right != null){
            Line l2 = new Line();
            l2.setStartX(x + hGap);
            l2.setStartY(y + vGap);
            l2.setFill(Color.BLUE);
            l2.setStroke((Color.BLUE));
            l2.setEndX(x);
            l2.setEndY(y);
            getChildren().add(l2);
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, color);
        }

        Stop[] stops = new Stop[] { new Stop(0, Color.BLUE), new Stop(1, Color.RED)};

        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        Circle circle = new Circle(x, y, radius);
        circle.setFill(lg1);
        circle.setStroke(Color.BLACK);

        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        circle.setEffect(reflect);
        circle.setTranslateY(0);

        getChildren().addAll(circle, new Text(x - 4, y + 4, root.getData() + ""));
    }

}