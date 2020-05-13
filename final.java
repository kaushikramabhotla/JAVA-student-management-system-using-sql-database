/*  JAVA JDBC IMP : 1. CREATE CONNECTION CLASS
               2. CALL THAT IN MAIN METHOD
               3. FOR INSERTION OF VALUES INTO DATABASE, USE PREPARED STATEMENT, INITIATE CONNECTION,PS.SETSTRING/INT AS YOU WISH!.
               4. FOR READING DB DATA, USE RESULT SET CLASS AND STATEMENT CLASS AND RESULTSET.GETSTRING METHOS.
               5. CATCH THE EXCEPTIONS(MOSTLY 'SQLException' AND 'NullPointerException'.
               6. CLOSE ALL CONNECTIONS INCLUDING PREPARED STATEMENTS AND RESULT SETS WHEREVER NECESSARY.
               7. THAT'S IT FOLKS :)


               @author
               kaushik ramabhotla(dr3adl0cks)
               ramabhotlakaushik@gmail.com
*/
package com.dr3adl0cks.sqliteapp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.*;
import java.util.StringTokenizer;

public class Main {
     private static final String sql = "INSERT INTO users(email,pass) VALUES(?,?)";
   private static String username;
   private static Object NullPointerException;
   public Main() throws SQLException {
   }
   //==========================================================
    public static void main(String[] args) throws SQLException {
       System.out.println("");

        //insert("rajas@am.faculty.amrita.edu","rajas");//////////////////    FOR REFERENCE
        //showValues();                                 //////////////////    FOR REFERENCE
        //isLogin("rajath@am.students.amrita.edu","rajath");//////////////    FOR REFERENCE
       isLogin();//check credentials.
       //showValues();                                  /////////////////    FOR REFERENCE
    }
    ///////////////     FOR REFERENCE ONLY///////////////////////////////////
       /* private static void insert(String email, String pass) {
            PreparedStatement ps = null;
            try (Connection connection = sqlConnection.connection()) {

                String sql = "INSERT INTO users(email,pass) VALUES(?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, pass);
                ps.execute();
                ps.close();
                System.out.println("Data has been added to database");
            } catch (SQLException e) {
                System.out.println(e.toString());

            }catch (NullPointerException n){
                System.out.println(n);
            }
            finally {
               try{
                  ps.close();
               }
               catch(Exception e) {
                  System.out.println(e);
               }
            }
        }

    private static void showValues() {
        Connection con = sqlConnection.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM users";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("ALL USERS\n");
            while(rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("pass");


                System.out.println("Email: "+email);
                System.out.println("Password: "+password+"\n\n");

            }
        } catch(SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch(SQLException e) {
                System.out.println(e.toString());
            }
        }
    }*////////////////////////////////   REFERENCE ENDS HERE///////////////////////////
    public static void isLogin() throws SQLException {
       Scanner s = new Scanner(System.in);
       System.out.println("Enter username: ");
       username = s.next();
       System.out.println("Enter Password");
       String password = s.next();
       //===============================================================================
       Connection connection = sqlConnection.connection();
       Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT email,pass from users");
       //String s = "rajath@am.students.amrita.edu";
       boolean isUser = false;
       while (resultSet.next()) {
          if (resultSet.getString("email").contains(("faculty")) && resultSet.getString("pass").equals(password)) {
             System.out.println("Logged In ! as faculty");
             connection.close();
             welcomeFaculty();
             isUser = true;
          }
          else if (resultSet.getString("email").contains("students") && resultSet.getString("pass").equals(password)) {
             {
                connection.close();
                welcomeStudents();
                isUser = true;
             }
          }
          else {
             if(isUser = false){System.out.println("u are not authorised!");}
          }
       }
       connection.close();
       resultSet.close();
    }

   public static void welcomeFaculty() throws SQLException{
       StringTokenizer st = new StringTokenizer(username, "@");
       String s2 = st.nextToken();
       System.out.println("Welcome : " + s2);
       System.out.println();
       int option;
       do {
          System.out.println("Please select an option : \n" +
                "1. View Student Details\n" +
                "2. Add student to database\n" +
                "3. Add and view events\n" +
                "4. Update student marks\n" +
                "5. Update Attendance\n" +
                "6. Exit App\n");
          Scanner s = new Scanner(System.in);
          option = s.nextInt();
          if (option == 1){
             viewStudent();
          }
          //============*******=============
          if (option ==2){
             addStudent();
          }
          //============*******=============
          if (option==3){
             adandviewEvents();
          }
          //============*******=============
          if (option==4){
             updateMarks();
          }
          //============*******=============
          if (option==6){
             System.out.println("Exiting AUMS :)");
             System.out.println("===================================================");
             System.exit(0);
          }
          //============*******=============
          if (option==5){
             updateAttendance();
          }
          //============*******=============
       }while (option!=6);
    }
//============*******=============
   private static void updateAttendance() throws SQLException {
      System.out.println("'Welcome to Attendance portal' :)");
      //==========*********************===========
      Connection connection = sqlConnection.connection();
      Statement statement = connection.createStatement();
      PreparedStatement ps = null;
      String sql = "INSERT INTO studattendance(name,maths,datastructures,cultural,algorithms,oops,cir) VALUES(?,?,?,?,?,?,?)";
      ps =connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery("SELECT name FROM studentdetails");
      Scanner s = new Scanner(System.in);
      while (rs.next()){
         String name = rs.getString("name");
         System.out.println("Student name : " + name);
         ps.setString(1,name);
         //===================================================
         System.out.println("Enter Attendance in Mathematics : ");
         int maths = s.nextInt();
         ps.setInt(2,maths);
         //===================================================
         System.out.println("Enter Attendance in Data Structures : ");
         int ds = s.nextInt();
         ps.setInt(3,ds);
         //===================================================
         System.out.println("Enter attendance for culturals : ");
         int cult = s.nextInt();
         ps.setInt(4,ds);
         //===================================================
         System.out.println("Enter the attendance for algorithms : ");
         int algo = s.nextInt();
         ps.setInt(5,algo);
         //===================================================
         System.out.println("Enter attendance for oops : ");
         int oops = s.nextInt();
         ps.setInt(6,oops);
         //===================================================
         System.out.println("Enter attendance for cir :  ");
         int cir = s.nextInt();
         ps.setInt(7,cir);
         //===================================================
         ps.executeUpdate();
      }
      System.out.println("Atendance updated successfully! :)");
      connection.close();
      ps.close();rs.close();
   }
   //============*******=============
   private static void updateMarks() throws SQLException {
      System.out.println("Welcome to marks portal : ");
   Connection connection = sqlConnection.connection();
   Statement statement = connection.createStatement();
      //c,r,u,d
      PreparedStatement ps1 =null;
      String markssql = "INSERT INTO studentmarks(name,maths,oops,cir,datastructures) VALUES(?,?,?,?,?)";
      ps1 = connection.prepareStatement(markssql);

      ResultSet rs = statement.executeQuery("SELECT name FROM studentdetails");
      Scanner s = new Scanner(System.in);
      //PreparedStatement ps = null;
      while (rs.next()){
         String name = rs.getString("name");
         System.out.println("student names : " +name);
         ps1.setString(1,name);
         System.out.println("Enter marks for maths :" );
         //===================================================
         int maths = s.nextInt();
         ps1.setInt(2,maths);
         //===================================================
         System.out.println("Enter marks for oops :" );
         int oops = s.nextInt();
         ps1.setInt(3,oops);
         //===================================================
         System.out.println("Enter marks for oops :" );
         int cir = s.nextInt();
         ps1.setInt(4,cir);
         //===================================================
         System.out.println("Enter marks for DS :" );
         int data = s.nextInt();
         ps1.setInt(5,data);
         //===================================================
         ps1.executeUpdate();
      }
      System.out.println("Marks updated succesfully! :)");
      connection.close();
      rs.close();
      ps1.close();
   }
   //============*******=============
   private static void adandviewEvents() throws SQLException {
      //scanning details
      Scanner s = new Scanner(System.in);
      System.out.println("Welcome to events portal");
      System.out.println("Select an option :" +
            "1. Add an event: " +
            "2. View added events:  ");
      int option = s.nextInt();
      if(option ==1){
         System.out.println("Adding an event : ");
         System.out.println();
         System.out.println("Enter the event name : ");
         String name = s.next();
         System.out.println("enter the venue : ");
         String venue = s.next();
         System.out.println("enter dates of event : ");
         String dates = s.next();
         //===================================================================
         Connection connection = sqlConnection.connection();
         Statement statement = connection.createStatement();
         PreparedStatement ps = null;
         String sql = "INSERT INTO events(name,venue,dates) VALUES(?,?,?)";
         ps = connection.prepareStatement(sql);
         ps.setString(1,name);
         ps.setString(2,venue);
         ps.setString(3,dates);
         ps.executeUpdate();
         //===================================================
         System.out.println("Data has been added to database");
         //===================================================
         connection.close();
         ps.close();
         //===================================================

      }
      if (option == 2){
         System.out.println("viewing events : ");
         //===================================================
         Connection connection = sqlConnection.connection();
         Statement statement = connection.createStatement();
         ResultSet rs = statement.executeQuery("SELECT * from events");
         //===================================================
         while (rs.next()){
            String name = rs.getString("name");
            String venue = rs.getString("venue");
            String dates = rs.getString("dates");
            //===================================================
            System.out.println(" Name of the venue : " + name);
            System.out.println("Venue of the event is :  " + venue);
            System.out.println("Dates of "+name+"  are : "+ dates);
            System.out.println();
         }
      }
   }
//============*******=============*******====================******==========
   private static void addStudent() throws SQLException {

      //System.out.println("E");
      System.out.println("Welcome to student addition portal ");

      Scanner s = new Scanner(System.in);
      //===================================================

         Connection connection = sqlConnection.connection();
         Statement statement = connection.createStatement();
         //ResultSet resultSet = statement.executeQuery("I")
         PreparedStatement ps = null;
         String sql = "INSERT INTO studentdetails(name,age,gender,grp,ph,blood,city) VALUES(?,?,?,?,?,?,?)";

         ps = connection.prepareStatement(sql);
         //===================================================
         System.out.println("Enter name of student : ");
         String name = s.next();
         ps.setString(1,name);
          //===================================================
         System.out.println("Enter age of student : ");
         int age = s.nextInt();
         ps.setInt(2,age);
         //===================================================
         System.out.println("Enter group of student : ");
         String gender = s.next();
         ps.setString(3,gender);
         //===================================================
         System.out.println("Enter gender of student : ");
         char group = s.next().charAt(0);
         ps.setString(4, String.valueOf(group));
         //===================================================
         System.out.println("Enter ph no.  of student : ");
         int phno = s.nextInt();
         ps.setInt(5,phno);
         //===================================================
         System.out.println("Enter city Address of student : ");
         String bloodgrp = s.next();
         ps.setString(6,bloodgrp);
         //===================================================
         System.out.println("Enter name of student : ");
         String city = s.next();
         ps.setString(7,city);
      //===================================================
      ps.executeUpdate();
      connection.close();
      ps.close();
      //===================================================
      System.out.println("Data has been added to database");
   }
//============*******=============
   private static void viewStudent() throws SQLException {
      Scanner s = new Scanner(System.in);
      System.out.println("Enter name of the student u want to view the details : ");
      String name = s.next();

      Connection connection = sqlConnection.connection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * from studentdetails");
      //===================================================
      while (resultSet.next()){
         try {
            if (resultSet.getString("name").contains(name)) {
               //===================================================
               System.out.println(" details are present ");
               String name1 = resultSet.getString("name");
               int age = resultSet.getInt("age");
               String group = resultSet.getString("grp");
               String gender = resultSet.getString("gender");
               int phno = resultSet.getInt("ph");
               String city = resultSet.getString("city");
               String bloodgrp = resultSet.getString("blood");
               //===================================================
               //===================================================
               System.out.println(" Name: " + name1);
               System.out.println("Age : " + age);
               System.out.println("Group : " + group);
               System.out.println("ph no : " + phno);
               System.out.println("City : " + city);
               System.out.println(" Bloodgroup" + bloodgrp);
            }
         }catch (Exception e){
            System.out.println(e);
         }
      }
      connection.close();
      resultSet.close();
   }
   //OR 1=1 --
   //============*******=============
    public static void welcomeStudents() throws SQLException {
       StringTokenizer st = new StringTokenizer(username, "@");
       String s2 = st.nextToken();
       System.out.println("Welcome : " + s2);
       System.out.println();
       int option;
       do {
          System.out.println("Please select an option : \n" +
                "1. Show marks\n" +
                "2. show Attendance\n" +
                "3. Show events\n" +
                "4. Give faculty feedback\n" +
                "5. Exit App\n ");
          Scanner s = new Scanner(System.in);
          option = s.nextInt();
          if (option == 1) {
             showMarks();
          }
          if (option == 2) {
             showAttendance();
          }
          if (option == 3) {
             showEvents();
          }
          if (option == 4) {
             facFeedback();
          }
          if (option==5){
             System.out.println("Exiting AUMS :)");
             System.out.println("===================================================");
             System.exit(0);
          }

       } while (option != 5);

    }

   private static void facFeedback() throws SQLException {
      StringTokenizer st = new StringTokenizer(username, "@");
      String sur = st.nextToken();
      System.out.println("Welcome to Faculty feedback portal : " + sur);
      Connection connection = sqlConnection.connection();
      System.out.println("Please give feedback");
      System.out.println("Feedback rules are as follows : \n" +
            "Feedback ranging from 1 star to 5 star\n(Very poor,poor,average,satisfactory,good,excellent) respectively\n. please provide the no. of stars only!");
//kaush@am.students.amrita.edu
      PreparedStatement ps = null;
      Scanner s = new Scanner(System.in);
      String sql = "INSERT INTO facfeedback(name,sir1,sir2,sir3,sir4,sir5,sir6,sir7,sir8,sir9,sir10) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
      ps = connection.prepareStatement(sql);
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM facfeedback");
      System.out.println("Feedback for sir1 : ");
      String s1 = s.next();

      System.out.println("Feedback for sir2 : ");
      String  s2 = s.next();

      System.out.println("Feedback for sir3 : ");
      String  s3 = s.next();

      System.out.println("Feedback for sir4 : ");
      String  s4 = s.next();

      System.out.println("Feedback for sir5 : ");
      String  s5 = s.next();

      System.out.println("Feedback for sir6 : ");
      String  s6 = s.next();

      System.out.println("Feedback for sir7 : ");
      String  s7 = s.next();

      System.out.println("Feedback for sir8 : ");
      String  s8 = s.next();

      System.out.println("Feedback for sir9 : ");
      String  s9 = s.next();

      System.out.println("Feedback for sir10 : ");
      String  s10 = s.next();
      ///////////////////////////////////////////////////////////////////
      ps.setString(1,sur);
      ps.setString(2,s1);
      ps.setString(3,s2);
      ps.setString(4,s3);
      ps.setString(5,s4);
      ps.setString(6,s5);
      ps.setString(7,s6);
      ps.setString(8,s7);
      ps.setString(9,s8);
      ps.setString(10,s9);
      ps.setString(11,s10);

      while (rs.next()) {
         if (rs.getString("name").contains(sur)) {
            System.out.println("Your feedback : ");
            rs.getString("name");
            rs.getString("sir1");
            rs.getString("sir2");
            rs.getString("sir3");
            rs.getString("sir4");
            rs.getString("sir5");
            rs.getString("sir6");
            rs.getString("sir7");
            rs.getString("sir8");
            rs.getString("sir9");
            rs.getString("sir10");
         }
      }

      System.out.println("Feedback has been noted, Thank you for your valuable time : ");
      ps.close();
      rs.close();
      connection.close();

   }

   private static void showEvents() throws SQLException {
      System.out.println("EVENTS NEAR BY : PLEASE REFER TO THESE");
      Scanner s = new Scanner(System.in);
      //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
      Connection connection = sqlConnection.connection();
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM events");
      while (rs.next()){
         String name = rs.getString("name");
         String venue = rs.getString("venue");
         String dates = rs.getString("dates");

         System.out.println("EVENT NAME : "+name);
         System.out.println("EVENT VENUE : "+ venue);
         System.out.println("EVENT DATES : "+dates);
         System.out.println("*************************");
      }

      System.out.println("Today's date and time, so plan out for the events well before! ");
      LocalDateTime today = LocalDateTime.now();
      ZoneId id = ZoneId.of("Asia/Kolkata");
      ZonedDateTime zonedDateTime = ZonedDateTime.of(today, id);
      String formattedDateTime = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm")
            .format(zonedDateTime);
      System.out.println(formattedDateTime);
      connection.close();
      rs.close();
   }

   private static void showAttendance() throws SQLException {
      StringTokenizer st = new StringTokenizer(username, "@");
      String s2 = st.nextToken();
      System.out.println("Welcome to Attendance  portal : " + s2);
      System.out.println("Here is Your Attendance sheet : ");
      //================================================================
      Connection connection = sqlConnection.connection();
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM studattendance");
      while (rs.next()){
         if (rs.getString("name").contains(s2)){
            System.out.println("Getting your Attendance : ");
            int maths = rs.getInt("maths");
            int ds= rs.getInt("datastructures");
            int cul = rs.getInt("cultural");
            int algo = rs.getInt("algorithms");
            int oop = rs.getInt("oops");
            int cir = rs.getInt("cir");

            System.out.println("MATHS : "+maths+"%");
            System.out.println("DATASTRUCTURES: "+ds+"%");
            System.out.println("CULTURAL : "+cul+"%");
            System.out.println("ALGORITHMS : "+algo+"%");
            System.out.println("OOPS : "+oop+"%");
            System.out.println("CIR"+cir+"%");
         }

      }
      System.out.println("PLEASE NOTE THAT ATTENDANCE WIL BE UPDATED EVERY 35 WORKING DAYS. IN CASE OF ANY QUERIES, PLEASE CONTACT YOUR RESPECTIVE CLASS TEACHERS!");
      connection.close();
      rs.close();
   }
//===============================================================================
   private static void showMarks() throws SQLException {
      StringTokenizer st = new StringTokenizer(username, "@");
      String s2 = st.nextToken();
      System.out.println("Welcome to marks portal : " + s2);

      Connection connection = sqlConnection.connection();
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM studentmarks");

      while (rs.next()){
         try {
            if (rs.getString("name").contains(s2)){

               System.out.println("Getting your marks : ////||||////");
               int maths = rs.getInt("maths");
               int oops = rs.getInt("oops");
               int cir = rs.getInt("cir");
               int ds = rs.getInt("datastructures");
               //+++++++++++++++++++++++++++++++++++++++++++++++++++++
               System.out.println("MATHS SCORE : "+maths);
               System.out.println("*************************");
               System.out.println("OOPS SCORE : "+ oops);
               System.out.println("*************************");
               System.out.println("CIR SCORE : "+ cir);
               System.out.println("*************************");
               System.out.println("DATASTRUCTURES SCORE : "+ds);
               System.out.println("*************************");
               System.out.println("%%%%%%%%%%%%%%%%%%% YOUR PERCENTAGE IS %%%%%%%%%%%%%%%%%%% ");
               int sum = (maths+oops+cir+ds);
               System.out.println(sum/4);
            }
         }catch (Exception e){
            e.getMessage();
         }
      }
      connection.close();
      rs.close();
   }
}

