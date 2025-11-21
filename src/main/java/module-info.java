module io.github.mortenjenne.fridgechef {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.zaxxer.hikari;

    opens io.github.mortenjenne.fridgechef to javafx.fxml, com.google.gson;

    exports io.github.mortenjenne.fridgechef;
    exports io.github.mortenjenne.fridgechef.controller;
    opens io.github.mortenjenne.fridgechef.controller to com.google.gson, javafx.fxml;
    exports io.github.mortenjenne.fridgechef.model;
    opens io.github.mortenjenne.fridgechef.model to com.google.gson, javafx.fxml;
    exports io.github.mortenjenne.fridgechef.logic;
    opens io.github.mortenjenne.fridgechef.logic to com.google.gson, javafx.fxml;
    exports io.github.mortenjenne.fridgechef.util;
    opens io.github.mortenjenne.fridgechef.util to com.google.gson, javafx.fxml;
}