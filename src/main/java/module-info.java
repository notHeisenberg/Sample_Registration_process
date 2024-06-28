module com.oop.practice.midpracice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.practice.midpracice to javafx.fxml;
    exports com.oop.practice.midpracice;
}