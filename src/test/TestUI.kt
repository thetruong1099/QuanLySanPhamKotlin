package test

import Ui.QuanLySPUI
import javax.swing.JFrame

fun main() {
    var gui = JFrame("Chương trình quản lý sản phẩm")
    var spUI = QuanLySPUI()
    gui.contentPane=spUI.pnMain
    gui.defaultCloseOperation= JFrame.EXIT_ON_CLOSE
    gui.setSize(500,500)
    gui.setLocationRelativeTo(null)
    gui.isVisible=true
}