package com.todokanai.messagetest.compose.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.messagetest.compose.presets.dropdownmenu.MyExposedDropdownMenu

@Composable
fun PreferencesPart(
    soundOptions:List<String>,
    notiBarOptions:List<String>,
    setSoundOption:(Boolean)->Unit,
    setNotiOption:(Boolean)->Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            contents = soundOptions.map { it.toString() },
            onItemSelect = {setSoundOption(it=="true")}
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        MyExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            contents = notiBarOptions,
            onItemSelect = {}
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
    }
}

@Preview
@Composable
private fun PreferencesPartPreview(){
    Surface {
        PreferencesPart(
            soundOptions = listOf("true"),
            notiBarOptions = listOf("1"),
            setSoundOption = {},
            setNotiOption = {},
            modifier = Modifier
                .background(Color.Red)
        )
    }
}