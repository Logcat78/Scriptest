package dev.firecrown.scriptest.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.firecrown.scriptest.data.repositories.BlockRepository




@Composable
internal fun DialogOverlay(){
    val title = BlockRepository.title.collectAsState().value?:"Title"
    val text = BlockRepository.text.collectAsState().value?:"Text"
    val options = BlockRepository.options.collectAsState().value?: listOf()
    val option = BlockRepository.option.collectAsState().value
    val textField = BlockRepository.textField.collectAsState().value
    val textFieldValue = BlockRepository.textFiledTypedText.collectAsState().value

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(20)),
        color = Colors.dialogLayerBackgroundColor
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
        ){
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            if(options.isNotEmpty()){
                options.forEach { optionText ->
                    Row(){
                        RadioButton(
                            selected = optionText == option,
                            onClick = { BlockRepository.option.value = optionText },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Colors.buttonColor,
                                unselectedColor = Color.LightGray
                            )
                        )

                        Column(){
                            Spacer(Modifier.height(17.dp))
                            Text(
                                text = optionText,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(1.dp))
                }
            }

            if(textField != null){
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20))
                    ,
                    label = {Text(textField)},
                    value = textFieldValue,
                    onValueChange = { newText ->
                        BlockRepository.textFiledTypedText.value = newText
                    },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Colors.textFieldColor,
                        unfocusedContainerColor = Colors.textFieldColor,
                        cursorColor = Colors.buttonColor,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedIndicatorColor = Colors.buttonColor,
                        focusedIndicatorColor = Colors.buttonColor,
                        unfocusedLabelColor = Colors.buttonColor,
                        focusedLabelColor = Colors.buttonColor,
                        ),
                )
            }

            if(options.isNotEmpty()){
                if(option != null) {
                    NextButton()
                }
            }else{
                NextButton()
            }

        }
    }
}
@Composable
internal fun NextButton(){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ){
        ScriptestButton("Далее"){
            BlockRepository.apply {
                isBlockExecuting.value = false
            }
        }
    }
}

@Composable
internal fun PointerOverlay(
    start: Float,
    end: Float,
    top: Float,
    bottom: Float
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val startDp = screenWidthDp * start
    val endDp = screenWidthDp * end
    val topDp = screenHeightDp * top
    val bottomDp = screenHeightDp * bottom


    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .size(startDp)
                    .background(
                        color = Color
                            .Black
                            .copy(alpha = 0.3f),
                    )

                ,
                content = {}
            )

            Column() {
                Column(
                    verticalArrangement = Arrangement.Top,
                ){
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color
                                    .Black
                                    .copy(alpha = 0.3f),
                            )
                            .size(
                                height = topDp,
                                width = (screenWidthDp + 1.dp) - (startDp + endDp)
                            )
                        ,
                        content = {},
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ){
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color
                                    .Black
                                    .copy(alpha = 0.3f),
                            )
                            .size(
                                height = bottomDp,
                                width = (screenWidthDp + 1.dp) - (startDp + endDp)
                            )
                        ,
                        content = {},
                    )
                }
            }



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(endDp)
                        .background(
                            color = Color
                                .Black
                                .copy(alpha = 0.3f),
                        )
                    ,
                    contentAlignment = Alignment.CenterEnd,
                    content = {},
                )
            }
        }
    }
}

@Composable
internal fun ScriptestButton(
    text: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = Colors.buttonColor
            )
    ){
        Text(
            text = text,
            color = Color.White
        )
    }
}