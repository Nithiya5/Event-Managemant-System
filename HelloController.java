package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.Parent;

import java.sql.*;
import java.io.IOException;

public class HelloController {

    @FXML
    public TextField usernameField;
    @FXML
    public TextArea mailID;
    @FXML
    public TextArea UserId;
    @FXML
    public TextArea uid;
    @FXML
    public TextArea IDemail;
    @FXML
    public TextArea eveid;
    @FXML
    public TextArea cityid;
    @FXML
    public TextArea tuserid;
    @FXML
    public TextArea typeid;
    @FXML
    public TextArea amountid;
    @FXML
    public TextArea pname;
    @FXML
    public TextArea accid;
    @FXML
    public TextArea tranid;
    @FXML
    public TextArea temailid;
    @FXML
    public TextArea evenameid;
    @FXML
    public TextArea notid;
    @FXML
    public TextField phid;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField passID;
    @FXML
    public Label label1id;
    @FXML
    public Label sid;
    @FXML
    public Label rlabel;
    @FXML
    public Label tilabel;
    @FXML
    public Label plabel;


    @FXML
    public void gotologin(ActionEvent event) throws IOException {
        String dburl = "jdbc:mysql://127.0.0.1:3306/event";
        String username = UserId.getText();
        String email = mailID.getText();
        String password = passID.getText();
        System.out.println("My details : " + username + " " + email + " " + password);
        boolean incompleteinformation = false;

        try {
            Connection connection = DriverManager.getConnection(dburl, "root", "root@123");
            String sql = "Insert into signup values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.execute();
            System.out.println("Inserted into signup Table");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            incompleteinformation = true;

        }

        if (!incompleteinformation) {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lOGIN.fxml"));
            Scene firstscene = new Scene(loginfxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(firstscene);

            stage.show();
        } else {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));

            Scene firstscene = new Scene(loginfxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Signup");
            stage.setScene(firstscene);
            sid.setText("Insufficient Information");
        }

    }


    @FXML
    void gotoevent(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dburl = "jdbc:mysql://127.0.0.1:3306/event";

        try {
            Connection connection = DriverManager.getConnection(dburl, "root", "root@123");
            String sql = "SELECT * FROM signup WHERE username = ?  AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Event.fxml"));
                Parent root = fxmlLoader.load();

                Stage currentStage = (Stage) usernameField.getScene().getWindow();
                currentStage.setScene(new Scene(root));
                currentStage.setTitle("EVENTS");
                currentStage.show();
            } else {
                label1id.setText("Invalid username or password.");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoregister(ActionEvent event) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Registration1.fxml"));

        Scene firstscene = new Scene(loginfxmlLoader.load());

        Stage stage = new Stage();
        stage.setTitle("Registration");
        stage.setScene(firstscene);

        stage.show();

    }

    @FXML
    void gototicket(ActionEvent event) throws IOException {
        String dburl = "jdbc:mysql://127.0.0.1:3306/event";
        String username = uid.getText();
        String emailid = IDemail.getText();
        String eventn = eveid.getText();
        String city = cityid.getText();
        String phonenumber = phid.getText();
        System.out.println("My details : " + username + " " + emailid + " " + eventn + " " + city + " " + phonenumber);

        boolean incompleteinformation = false;

        if (username.isEmpty() || emailid.isEmpty() || eventn.isEmpty() || city.isEmpty() || phonenumber.isEmpty()) {
            incompleteinformation = true;
        }

        try {
            Connection connection = DriverManager.getConnection(dburl, "root", "root@123");
            String sql = "INSERT INTO register VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, emailid);
            statement.setString(3, eventn);
            statement.setString(4, city);
            statement.setString(5, phonenumber);

            statement.execute();
            System.out.println("Inserted into register Table");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            incompleteinformation = true;
        }

        if (!incompleteinformation) {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ticket.fxml"));
            Scene firstscene = new Scene(loginfxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(firstscene);
            stage.show();
        } else {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Registration1.fxml"));
            Scene firstscene = new Scene(loginfxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(firstscene);
            rlabel.setText("Insufficient Information");
            stage.show();
        }
    }



    @FXML
    void gotopayment(ActionEvent event) throws IOException {
        String dburl = "jdbc:mysql://127.0.0.1:3306/event";
        String username = tuserid.getText();
        String emailid = temailid.getText();
        String tickettype = typeid.getText();
        String noofticket = notid.getText();
        String eventname = evenameid.getText();
        System.out.println("My details : " + username + " " + tickettype + " " + noofticket + " " + emailid + " " + eventname);
        boolean incompleteinformation = false;

        // Check for empty fields
        if (username.isEmpty() || emailid.isEmpty() || tickettype.isEmpty() || noofticket.isEmpty() || eventname.isEmpty()) {
            incompleteinformation = true;
        }

        try {
            Connection connection = DriverManager.getConnection(dburl, "root", "root@123");
            String sql = "INSERT INTO ticket VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, tickettype);
            statement.setString(3, noofticket);
            statement.setString(4, emailid);
            statement.setString(5, eventname);

            statement.execute();
            System.out.println("Inserted into ticket Table");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            incompleteinformation = true;
        }

        if (!incompleteinformation) {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Payment.fxml"));
            Scene firstscene = new Scene(loginfxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle(" Registration");
            stage.setScene(firstscene);

            stage.show();
        } else {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ticket.fxml"));

            Scene firstscene = new Scene(loginfxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(firstscene);
            tilabel.setText("Insufficient Information");
        }
    }

    @FXML
    void gotothankyou(ActionEvent event) throws IOException {
        String dburl = "jdbc:mysql://127.0.0.1:3306/event";
        String name = pname.getText();
        String amount = amountid.getText();
        String accountnumber = accid.getText();
        String  transactiontype= tranid.getText();
        System.out.println("My details : " + name + " " + amount  + " " + accountnumber + " " + transactiontype);
        boolean incompleteinformation = false;


        try {
            Connection connection = DriverManager.getConnection(dburl, "root", "root@123");
            String sql = "INSERT INTO payment VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, amount);
            statement.setString(3, accountnumber);
            statement.setString(4, transactiontype);

            statement.execute();
            System.out.println("Inserted into payment Table");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            incompleteinformation = true;

        }

        if (!incompleteinformation) {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ThankYou.fxml"));
            Scene firstscene = new Scene(loginfxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle(" Registration");
            stage.setScene(firstscene);

            stage.show();
        } else {
            FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Payment.fxml"));

            Scene firstscene = new Scene(loginfxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(firstscene);
            plabel.setText("Insufficient Information");
        }

    }
}