package com.todokanai.messagetest.compose.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.messagetest.compose.presets.dropdownmenu.MyExposedDropdownMenu

@Composable
fun PreferencesPart(
    soundOptions:List<String>,
    notiBarOptions:List<String>,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        MyExposedDropdownMenu(
            modifier = Modifier,
            contents = soundOptions,
            onItemSelect = {}
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        MyExposedDropdownMenu(
            modifier = Modifier,
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
            soundOptions = listOf("1"),
            notiBarOptions = listOf("1"),
            modifier = Modifier
                .background(Color.Red)
        )
    }
}