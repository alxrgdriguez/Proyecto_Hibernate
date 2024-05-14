module Proyecto.Hibernate {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;

    opens com.jaroso to javafx.fxml;
    opens com.jaroso.entidades;
    exports com.jaroso;
    exports com.jaroso.controladores;
    opens com.jaroso.controladores to javafx.fxml;
}