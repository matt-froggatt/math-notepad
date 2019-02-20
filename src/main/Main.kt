package main

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.stage.Stage

const val TITLE = "Text App"
const val WIDTH = 1280.0
const val HEIGHT = 720.0

// TODO modularize buttons, restructure files for more intuitive modularization, change button themeing slightly, and actually add save/load

class AppController {
    @FXML
    lateinit var leftBar: VBox

    fun add() {
        val newButton = getMenuButton()
        newButton.setOnMouseClicked {
            println("selected")
        }
        leftBar.children.add(newButton)
    }

    fun parseText() {
        println("parsing")
    }
}

class TextApp : Application() {

    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load(this.javaClass.getResource("/text_editor_fxml/text_editor.fxml")) as Parent
        primaryStage.scene = Scene(root, WIDTH, HEIGHT)
        primaryStage.title = TITLE
        primaryStage.centerOnScreen()
        primaryStage.show()
    }


}

fun getMenuButton() = FXMLLoader.load(TextApp::class.java.getResource("/text_editor_fxml/ui_elements/left_menu_button/left_menu_button.fxml")) as Button

fun main(args: Array<String>) {
    Application.launch(TextApp::class.java, *args)
}
