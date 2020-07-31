package ru.javabegin.training.javafx.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.javafx.entity.Person;
import ru.javabegin.training.javafx.fxml.EditView;
import ru.javabegin.training.javafx.fxml.MainView;
import ru.javabegin.training.javafx.objects.Lang;
import ru.javabegin.training.javafx.service.AddressBook;
import ru.javabegin.training.javafx.utils.LocaleManager;

import java.lang.reflect.Method;
import java.util.Observable;
import java.util.ResourceBundle;

@Component
public class MainController extends Observable{

    @Autowired
    private AddressBook addressBook;

    @Autowired
    private MainView mainView;

    @Autowired
    private EditView editView;

    private ObservableList<Person> personList;


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private CustomTextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView tableAddressBook;

    @FXML
    private TableColumn<Person, String> columnFIO;

    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private Label labelCount;

    @FXML
    private ComboBox comboLocales;

    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @Autowired
    private EditController editController;

    private Stage editDialogStage;

    private ResourceBundle resourceBundle;

    private static final String RU_CODE="ru";
    private static final String EN_CODE="en";


    @FXML
    public void initialize() {
        this.resourceBundle = mainView.getResourceBundle();
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        setupClearButtonField(txtSearch);
        fillData();
        initListeners();
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
//            customTextField.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    if (txtSearch.getText().trim().length() == 0) {// если полностью очистили текст - вернуть все записи
//                        addressBookImpl.getPersonList().clear();
//                    }
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void fillData() {
        fillTable();
        fillLangComboBox();
        updateCountLabel();
    }

    private void fillTable() {
        personList = addressBook.findAll();
        tableAddressBook.setItems(personList);
    }

    private void fillLangComboBox() {

        Lang langRU = new Lang(0, RU_CODE, resourceBundle.getString("ru"), LocaleManager.RU_LOCALE);
        Lang langEN = new Lang(1, EN_CODE, resourceBundle.getString("en"), LocaleManager.EN_LOCALE);

        comboLocales.getItems().add(langRU);
        comboLocales.getItems().add(langEN);

        if (LocaleManager.getCurrentLang() == null){
            comboLocales.getSelectionModel().select(0);
            LocaleManager.setCurrentLang(langRU);
        }else{
            comboLocales.getSelectionModel().select(LocaleManager.getCurrentLang().getIndex());
        }
    }

    private void initListeners() {

        // слушает изменения в коллекции для обновления надписи "Кол-во записей"
        personList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

    }

    private void updateCountLabel() {
        labelCount.setText(resourceBundle.getString("count") + ": " + personList.size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

    }

    public void actionSearch(ActionEvent actionEvent) {

    }

}
