/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxhomework2;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Carlos Garcia
 */
public class JavaFXHomeWork2 extends Application {
    
    private Label amountWon;
    private Label totalWon;
    private Label totalSpend;
    private double totalAmountSpent;
    private double totalAmountWon;
    private TextField inputAmountInserted;
    private Label bottomText;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private Image image;
    private Image image2;
    private Image image3;

    @Override
    public void start(Stage primaryStage) {
       
        String input;
        
        //Sets the images to blank Fruit
        image = new Image("javafxhomework2/BlankFruit.png");
        image2 = new Image("javafxhomework2/BlankFruit.png");
        image3 = new Image("javafxhomework2/BlankFruit.png");
        
        
        //Puts it in imageView so it can be added to the hbox
        imageView = new ImageView(image);
        imageView2 = new ImageView(image2);
        imageView3 = new ImageView(image3);
        
        
        //Sets the top display
        HBox pictureDisplay = new HBox(20, imageView, imageView2, imageView3);
        pictureDisplay.setAlignment(Pos.CENTER);
        pictureDisplay.setPadding(new Insets(10));
        
        
        //Labels for the right side of the text
        amountWon = new Label("Amount Won This Spin: $  0.00");
        totalWon = new Label("Total Amount Won: $  0.00");
        totalSpend = new Label("Total Amount Spent: $  0.00");
        VBox rightTextInformation = new VBox(2,amountWon, totalWon, totalSpend);
        rightTextInformation.setAlignment(Pos.CENTER_RIGHT);
        
        //Label for the left side
        inputAmountInserted = new TextField();
        //Gets the text and puts the input into input
        input = inputAmountInserted.getText();
        Label amountInserted = new Label("Amount Inserted: $ ");
        HBox leftTextInformation = new HBox(5, amountInserted, inputAmountInserted);
        leftTextInformation.setAlignment(Pos.CENTER_LEFT);
        leftTextInformation.setPadding(new Insets(-10, 0, 0, 0));
        
        GridPane textInformation = new GridPane();
        textInformation.add(leftTextInformation, 0, 0);
        textInformation.add(rightTextInformation, 1, 0);
        textInformation.setAlignment(Pos.CENTER_RIGHT);
        textInformation.setHgap(40);
        textInformation.setPadding(new Insets(0, 46, 0, 0));
        //Puts all the Text information together
        //HBox textInformation = new HBox(20, leftTextInformation, rightTextInformation);
        //textInformation.setAlignment(Pos.CENTER);
        
        //Sets up the buttons
        Button spinBtn = new Button("Spin");
        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction(new ButtonResetHandler());
        spinBtn.setOnAction(new ButtonSpinHandler());
        HBox buttons = new HBox(10, spinBtn, resetBtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));
        
        //Sets the bottom text
        bottomText = new Label("Insert an amount to play.");
        HBox bottom = new HBox(bottomText);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(5));
        
        
        VBox root = new VBox(pictureDisplay, textInformation, buttons, bottom);
        
        Scene scene = new Scene(root, 550, 300);
        primaryStage.setTitle("Slot Machine Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    class ButtonResetHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            inputAmountInserted.setText("");
            amountWon.setText("Amount Won This Spin: $  0.00");
            totalWon.setText("Total Amount Won: $  0.00");
            totalSpend.setText("Total Amount Spent: $  0.00");
            totalAmountSpent = 0;
            totalAmountWon = 0;
            //Sets the images to blank Fruit
            image = new Image("javafxhomework2/BlankFruit.png");
            image2 = new Image("javafxhomework2/BlankFruit.png");
            image3 = new Image("javafxhomework2/BlankFruit.png");
        
            //Puts it in imageView so it can be added to the hbox
            imageView.setImage(image);
            imageView2.setImage(image2);
            imageView3.setImage(image3);
            
            bottomText.setText("Insert an amount to play.");
        }
    }
    class ButtonSpinHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
        
            if(inputAmountInserted.getText().isEmpty())
            {
                bottomText.setText("Error. Try a different amount.");
            }
            double amount = 0;
            boolean isNumber = true;
            try{
                amount = Double.parseDouble(inputAmountInserted.getText());
            }catch(NumberFormatException e){
                isNumber = false;
            }
            if(amount <= 0)
            {
                bottomText.setText("Error. Try a different amount.");
            }
            else{
                int firstNumber = setPicture(1);
                int secondNumber = setPicture(2);
                int thirdNumber = setPicture(3);
                totalAmountSpent = totalAmountSpent + amount;
                setText(firstNumber, secondNumber, thirdNumber, amount);
            }
        }
    }

    public void setText(int numberOne, int numberTwo, int numberThree, double totalSpent){
        String textNumber = new String(String.format("%.2f", totalAmountSpent));
        String text = new String("Total Amount Spent: $ "  + textNumber);
        totalSpend.setText(text);
        
        if(numberOne == numberTwo && numberTwo == numberThree)
        {
            double spinWon = totalSpent * 3;
            String text2 = new String(String.format("%.2f", spinWon));
            text = new String("Amount Won This Spin: $ " + text2);
            amountWon.setText(text);
            totalAmountWon = totalAmountWon + spinWon;
            text2 = new String(String.format("%.2f", totalAmountWon));
            text = new String("Total Amount Won: $ " + text2);
            totalWon.setText(text);
            bottomText.setText("Sweet! TRIPLE WIN x 3!!");
        }
        else if(numberOne == numberTwo || numberTwo == numberThree || numberOne == numberThree)
        {
            double spinWon = totalSpent * 2;
            String text2 = new String(String.format("%.2f", spinWon));
            text = new String("Amount Won This Spin: $ " + text2);
            amountWon.setText(text);
            totalAmountWon = totalAmountWon + spinWon;
            text2 = new String(String.format("%.2f", totalAmountWon));
            text = new String("Total Amount Won: $ " + text2);
            totalWon.setText(text);
            bottomText.setText("Sweet! DOUBLE WIN x 2!!");
        }
        else{
           text = new String("Amount Won This Spin: $  0.00");
           amountWon.setText(text);
           bottomText.setText("No Luck. Play again!"); 
        }
    }
    
    //random picture and number generator
    public int setPicture(int currentPic){
        Random randomizer = new Random();
        int numberChooser;
        int number = randomizer.nextInt(10);
        numberChooser = number;
        switch(number){
            case 0: image = new Image("javafxhomework2/Apple.bmp");
                    break;
            case 1: image = new Image("javafxhomework2/Banana.bmp");
                    break;
            case 2: image = new Image("javafxhomework2/Cherries.bmp");
                    break;
            case 3: image = new Image("javafxhomework2/Grapes.bmp");
                    break;
            case 4: image = new Image("javafxhomework2/Lemon.bmp");
                    break;
            case 5: image = new Image("javafxhomework2/Lime.bmp");
                    break;
            case 6: image = new Image("javafxhomework2/Orange.bmp");
                    break;
            case 7: image = new Image("javafxhomework2/Pear.bmp");
                    break;
            case 8: image = new Image("javafxhomework2/Strawberry.bmp");
                    break;
            case 9: image = new Image("javafxhomework2/Watermelon.bmp");
                    break;
            default:
                break;
        }
        if(currentPic == 1)
        {
            imageView.setImage(image);
        }
        else if(currentPic == 2)
        {
            imageView2.setImage(image);
        }
        else if(currentPic == 3){
            imageView3.setImage(image);
        }
        return numberChooser;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}