// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import javax.swing.JFileChooser

@Composable
@Preview
fun App() {
    var flashFilePicker = java.awt.FileDialog(ComposeWindow())
    var eepromFilePicker = java.awt.FileDialog(ComposeWindow())

    var flashFile = remember { mutableStateOf("") }
    var eepromFile = remember { mutableStateOf("") }

    var highFuseValue = remember { mutableStateOf("") }
    var lowFuseValue = remember { mutableStateOf("") }

    MaterialTheme(colors = Colors(
        Color(13, 158, 81),
        Color(52, 209, 94),
        Color(88, 169, 245),
        Color(52, 133, 209),
        Color(241, 235, 245),
        Color(233, 230, 250),
        Color(247, 148, 148),
        Color(247, 245, 245),
        Color(247, 245, 245),
        Color(13, 12, 12),
        Color(13, 12, 12),
        Color(247, 245, 245),
        true
    )
    ) {
        Row(Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
            Column(Modifier.wrapContentSize(),
                Arrangement.spacedBy(50.dp), Alignment.CenterHorizontally){

                Column(Modifier.wrapContentSize(),
                Arrangement.spacedBy(5.dp),
                    Alignment.CenterHorizontally){
                    Text(text = "write on FLASH memory")
                    Button(onClick = {
                        flashFilePicker.isVisible = true
                        flashFile.value = flashFilePicker.file
                    }){
                        Text(if(flashFile.value.isEmpty()) "Select a .hex file..." else flashFile.value)
                    }
                    Button(onClick = {},
                        Modifier.align(Alignment.CenterHorizontally)){
                        Text("Write")
                    }
                }

                Column(Modifier.wrapContentSize(),
                    Arrangement.spacedBy(5.dp),
                    Alignment.CenterHorizontally){
                    Text(text = "write on EEPROM memory")
                    Button(onClick = {
                        eepromFilePicker.isVisible = true
                        eepromFile.value = eepromFilePicker.file
                    }){
                        Text(if(eepromFile.value.isEmpty()) "Select a .hex file..." else eepromFile.value)
                    }
                    Button(onClick = {},
                    Modifier.align(Alignment.CenterHorizontally)){
                        Text("Write")
                    }
                }
            }

            Column(Modifier.wrapContentSize(),
            Arrangement.spacedBy(5.dp),
            Alignment.CenterHorizontally) {
                Row(Modifier.wrapContentSize(),
                Arrangement.spacedBy(2.dp), Alignment.CenterVertically){
                    Text(text = "High Fuse")
                    TextField(
                        value = highFuseValue.value,
                        onValueChange = {highFuseValue.value = it},
                        modifier = Modifier.wrapContentSize()
                            .clip(RoundedCornerShape(100.dp))

                    )
                }

                Row(Modifier.wrapContentSize(),
                    Arrangement.spacedBy(2.dp),Alignment.CenterVertically){
                    Text(text = "Low Fuse")
                    TextField(
                        value = lowFuseValue.value,
                        onValueChange = {lowFuseValue.value = it},
                        modifier = Modifier.wrapContentSize()
                            .clip(RoundedCornerShape(100.dp))
                    )
                }

                Row(Modifier.wrapContentSize(),
                    Arrangement.spacedBy(2.dp)){
                    Button(onClick = {}){
                        Text("Read")
                    }

                    Button(onClick = {}){
                        Text("Write")
                    }
                }

            }

        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
    title = "sUPer Burner",
    state = WindowState(position = WindowPosition(Alignment.Center))) {
        App()
    }
}
