package com.oop.practice.midpracice;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class MidExamSceneController
{
    @javafx.fxml.FXML
    private ComboBox<Integer> sectionComboBox;
    @javafx.fxml.FXML
    private Label courseCreditsLabel;
    @javafx.fxml.FXML
    private ComboBox<Integer> sectionCountComboBox;
    @javafx.fxml.FXML
    private Label sectionCountLabel;
    @javafx.fxml.FXML
    private RadioButton theoryRadioButton;
    @javafx.fxml.FXML
    private TextField scholarshipTextField;
    @javafx.fxml.FXML
    private TextArea registeredCourseDetailTextarea;
    @javafx.fxml.FXML
    private CheckBox hasScholarshipCheckBox;
    @javafx.fxml.FXML
    private TextField studentIDTextField;
    @javafx.fxml.FXML
    private ComboBox<String> courseComboBox;
    @javafx.fxml.FXML
    private RadioButton labRadioButton;

    ToggleGroup courseTypeToggleGroup = new ToggleGroup();
    ArrayList<RegisteredCourse> registeredCourses = new ArrayList<>();
    @javafx.fxml.FXML
    private Label studentInfoLabel;

    @javafx.fxml.FXML
    public void initialize() {
        courseComboBox.getItems().addAll("CSE101", "CSE101L", "CSE213", "CSE213L", "PHY101", "PHY101L", "ENG105", "MAT104", "CSE201", "CSE214");
        sectionComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        sectionCountComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        scholarshipTextField.setText("0");
        theoryRadioButton.setToggleGroup(courseTypeToggleGroup);
        labRadioButton.setToggleGroup(courseTypeToggleGroup);
    }

    @javafx.fxml.FXML
    public void proceedToRegisterOnAction(ActionEvent actionEvent) {
        Integer totalCredit = 0;
        Integer totalFee = 0;
        for (RegisteredCourse registeredCourse : registeredCourses) {
            totalCredit += registeredCourse.getCourseCredit();
            totalFee += registeredCourse.getTotalFee();
        }
        if (totalCredit == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("No course registered");
            alert.setContentText("Please register at least one course.");
            alert.showAndWait();
            return;
        } else if (totalCredit >=6 && totalCredit <= 18) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText("Registration Successful");
            alert.setContentText("You have successfully registered " + totalCredit + " credits.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Credit");
            alert.setContentText("You can only register between 6 to 18 credits.");
            alert.showAndWait();
            return;
        }

        for (RegisteredCourse registeredCourse : registeredCourses) {
            registeredCourseDetailTextarea.appendText(registeredCourse.toString());
        }

        if (hasScholarshipCheckBox.isSelected() && Integer.parseInt(scholarshipTextField.getText()) > 0
        && totalCredit >= 12){
            totalFee -= totalFee * Integer.parseInt(scholarshipTextField.getText()) / 100;
        }
        registeredCourseDetailTextarea.appendText("\nCredits enrolled:" + totalCredit + ".Total Payable:" +totalFee +
                "Tk");
    }

    @javafx.fxml.FXML
    public void sectionCountButtonOnAction(ActionEvent actionEvent) {
        String selectedRadioButton = ((RadioButton) courseTypeToggleGroup.getSelectedToggle()).getText();
        Integer sectionToCount = sectionCountComboBox.getValue();

        int sectionCount = 0;
        for (RegisteredCourse registeredCourse : registeredCourses) {
            if (registeredCourse.getSection().equals(sectionToCount) && registeredCourse.getCourseType().equals(selectedRadioButton)) {
                sectionCount++;
            }
        }
        sectionCountLabel.setText("No of " + selectedRadioButton + " courses having section-" + sectionToCount + " is: " + sectionCount);
    }

    @javafx.fxml.FXML
    public void addCourseToRegisterOnAction(ActionEvent actionEvent) {
        Integer studentID = Integer.parseInt(studentIDTextField.getText());
        Boolean hasScholarship = hasScholarshipCheckBox.isSelected();
        Integer scholarshipRate = Integer.parseInt(scholarshipTextField.getText());
        Student student = new Student(studentID,hasScholarship,scholarshipRate);
        registeredCourseDetailTextarea.setText(student.toString());

        String courseID = courseComboBox.getValue();
        Integer courseCredit = Integer.parseInt(courseCreditsLabel.getText());
        Integer section = sectionComboBox.getValue();

        RegisteredCourse newRegisterCourse = student.registerCourse(courseID,courseCredit,section);

        for (RegisteredCourse registeredCourse :registeredCourses){
            if(registeredCourse.getCourseID().equals(newRegisterCourse.getCourseID())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Course already registered");
                alert.setContentText("You have already registered this course.");
                alert.showAndWait();
                return;
            }
        }
        registeredCourses.add(newRegisterCourse);

    }

    @javafx.fxml.FXML
    public void selectCourseOnAction(ActionEvent actionEvent) {
        courseCreditsLabel.setText(
                courseComboBox.getValue().endsWith("L") ? "1" : "3"
        );
    }

    @javafx.fxml.FXML
    public void hasScholarshipCheckBoxOnAction(ActionEvent actionEvent) {
        scholarshipTextField.setDisable(!hasScholarshipCheckBox.isSelected());
        if (!hasScholarshipCheckBox.isSelected()) {
            scholarshipTextField.setText("0");
        } else if (scholarshipTextField.getText().equals("0")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Scholarship Amount cannot be 0");
            alert.setContentText("Please enter a valid scholarship amount.");
            alert.showAndWait();
        }
    }
}