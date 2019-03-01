package main

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import javafx.scene.web.HTMLEditor
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.text.html.HTML

const val TITLE = "Text App"
const val WIDTH = 1280.0
const val HEIGHT = 720.0

// TODO actually add save/load

class DocumentButton(val button: Button, file: File = File("files/new_file.txt"), textEditor: HTMLEditor) {
    init {
        if (!file.exists()) {
            file.createNewFile()
            if (file.exists()) {
                println("File " + file.name + " successfully created")
            }
        }

        println(file.absolutePath)

        button.setOnMouseClicked { textEditor.htmlText = file.readText() }
    }
}

class AppController{

    private val leftButtons = mutableListOf<DocumentButton>()

//    private var selectedButton = leftButtons.first()

    @FXML
    lateinit var leftBar: VBox

    @FXML
    lateinit var textEditor: HTMLEditor

    fun add() {
        val newFile = File("files/new_file.txt")
        leftButtons.add(DocumentButton(getMenuButton(), newFile, textEditor))
        val newDocButton = leftButtons.last()

        leftBar.children.add(newDocButton.button)
    }

    fun modifyText() {
        save()
    }
}

class TextApp : Application() {

    override fun start(primaryStage: Stage) {
        val rootNode = FXMLLoader.load(this.javaClass.getResource("/text_editor_fxml/text_editor.fxml")) as Pane
        val textEditor = rootNode.children[2] as HTMLEditor
        textEditor.htmlText = "Init Text"
        val leftBar = (rootNode.children[0] as Node)
        println(leftBar.toString())
        //println(rootNode.children.toString())
        //println(leftBar.toString())
        val root = rootNode as Parent
        primaryStage.scene = Scene(root, WIDTH, HEIGHT)
        primaryStage.title = TITLE
        // TODO remove window decoration, add support for resizing and movement, takes too much time rn
        primaryStage.initStyle(StageStyle.DECORATED)
        primaryStage.centerOnScreen()
        primaryStage.show()
    }

    override fun init() {
        super.init()
        val projectDir = File("files")

        if (!projectDir.exists()) {
            projectDir.mkdir()

            if (projectDir.exists()) {
                println("Directory successfully created.")
            }

        } else {
            load()
        }
    }

    override fun stop() {
        super.stop()
        save()
    }

}

fun getMenuButton() =
    FXMLLoader.load(TextApp::class.java.getResource("/text_editor_fxml/ui_elements/left_menu_button/left_menu_button.fxml")) as Button

fun save() = println("saving")

fun load() = println("loading")

fun main(args: Array<String>) {
    Application.launch(TextApp::class.java, *args)
}
