package main

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.*
import javafx.stage.Stage

const val TITLE = "Text App"
const val WIDTH = 1280.0
const val HEIGHT = 720.0

class AppController {
    @FXML
    lateinit var leftBar: VBox

    fun add() {
        leftBar.children.add(getMenuButton())
    }

    fun select() {
        println("selected")
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

fun getMenuButton() = FXMLLoader.load(TextApp::class.java.getResource("/text_editor_fxml/left_menu_button.fxml")) as Node

fun main(args: Array<String>) {
    Application.launch(TextApp::class.java, *args)
}
