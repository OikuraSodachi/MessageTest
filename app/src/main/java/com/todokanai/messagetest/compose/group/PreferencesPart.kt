package com.todokanai.messagetest.compose.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.messagetest.compose.presets.dropdownmenu.MyExposedDropdownMenu
import kotlinx.coroutines.flow.Flow

@Composable
fun PreferencesPart(
    soundOptions:List<String>,
    notiBarOptions:List<String>,
    setSoundOption:(String)->Unit,
    setNotiOption:(String)->Unit,
    sound: Flow<String>,
    noti:Flow<String>,
    modifier: Modifier = Modifier
){

    val selectedTest = sound.collectAsStateWithLifecycle(initialValue = soundOptions[0])
    val notiTest = noti.collectAsStateWithLifecycle(initialValue = notiBarOptions[0])
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            contents = soundOptions,
            onItemSelect = {setSoundOption(it)},
            selected = selectedTest.value
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        MyExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            contents = notiBarOptions,
            onItemSelect = {setNotiOption(it)},
            selected = notiTest.value
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
    }
}