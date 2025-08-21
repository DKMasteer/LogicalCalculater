module com.dkmasteer.logicalcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;
    requires javafx.graphics;
    requires java.desktop;

    opens com.dkmasteer.logicalcalculator.run to javafx.fxml;
    exports com.dkmasteer.logicalcalculator.run;
    opens com.dkmasteer.logicalcalculator.controller to javafx.fxml;
    exports com.dkmasteer.logicalcalculator.controller;
    exports com.dkmasteer.logicalcalculator;
    opens com.dkmasteer.logicalcalculator to javafx.fxml;
}