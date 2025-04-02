module com.example.templatemodule2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.apache.servicemix.bundles.poi;

    opens com.example.templatemodule2 to javafx.fxml;
    exports com.example.templatemodule2;
    exports com.example.templatemodule2.essential;
    opens com.example.templatemodule2.essential to javafx.fxml;
}