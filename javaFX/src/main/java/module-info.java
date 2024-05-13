module com.jaroso.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jaroso.javafx to javafx.fxml;
    exports com.jaroso.javafx;
}