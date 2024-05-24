module Proyecto.Hibernate {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires java.sql;


    opens com.jaroso to javafx.fxml;
    opens com.jaroso.entidades;
    opens com.jaroso.repositorios;
    exports com.jaroso;
    exports com.jaroso.repositorios;
    exports com.jaroso.controladores;
    opens com.jaroso.controladores to javafx.fxml;
}