package main

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.web.HTMLEditor
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.io.File

const val TITLE = "Text App"
const val DIRECTORY = "files"
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

class AppController {
//    private var selectedButton = leftButtons.first()

    private val leftButtons = mutableListOf<DocumentButton>()

    @FXML
    lateinit var leftBar: VBox

    @FXML
    lateinit var textEditor: HTMLEditor

    fun add() {
        val newFile = File("files/new_file.txt")
        val newDocButton = DocumentButton(getMenuButton(), newFile, textEditor)
        leftButtons.add(newDocButton)

        leftBar.children.add(newDocButton.button)
    }

    fun modifyText() {
        save()
    }
}

class TextApp : Application() {

    private var xOffset = 0.0
    private var yOffset = 0.0

    override fun start(primaryStage: Stage) {
        val rootNode = FXMLLoader.load(this.javaClass.getResource("/text_editor_fxml/text_editor.fxml")) as Pane

        // rootNode.onMousePressed =
        //     EventHandler{
        //         xOffset = it.sceneX
        //         yOffset = it.sceneY
        //     }

        // rootNode.onMouseDragged =
        //     EventHandler {
        //         primaryStage.x = it.screenX - xOffset
        //         primaryStage.y = it.screenY - yOffset
        //     }

        val root = rootNode as Parent
        primaryStage.scene = Scene(root, WIDTH, HEIGHT)
        primaryStage.title = TITLE
        // TODO add window resize, close, minimize, and maximize
        primaryStage.initStyle(StageStyle.DECORATED)
        primaryStage.centerOnScreen()
        primaryStage.show()
    }

    override fun init() {
        super.init()
        val projectDir = File(DIRECTORY)

        if (!projectDir.exists()) {
            projectDir.mkdir()

            if (projectDir.exists()) {
                println("Directory successfully created.")
            }

        } else {
            load(File(DIRECTORY))
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

fun load(dir: File) {
    println("loading")

    val files = dir.listFiles()

    files.forEach{
        println(it.name)
        //leftButtons.add(DocumentButton(getMenuButton(), it, null))
    }
}

fun main(args: Array<String>) {
    Application.launch(TextApp::class.java, *args)
}
