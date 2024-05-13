package inc.generics.create_new_group

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.generics.create_new_group.routing.CreateNewGroupScreenRouting
import inc.generics.presentation.R
import inc.generics.presentation.components.DefaultFilledTonalButtonDuet
import inc.generics.presentation.components.DuetAlertDialogError
import inc.generics.presentation.components.TitleTopAppBarDuet
import inc.generics.presentation.components.defaultTextFieldStyle
import inc.generics.presentation.components.defaultTextStyleDuet
import inc.generics.presentation.theme.DuetTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateNewGroupScreen(
    routing: CreateNewGroupScreenRouting,
    viewModel: CreateNewGroupViewModel = koinViewModel()
) {
    val createStatus by viewModel.statusCreateGroup.observeAsState()
    val nameGroupState = remember {
        mutableStateOf("")
    }

    SetupEmptyNameGroupBottomSheet()
    SetupRoutOnCreateGroup(routing = routing)
    SetupErrorDialog()

    Scaffold(
        topBar = {
            TitleTopAppBarDuet(
                text = DuetTheme.localization.getString("createGroup"),
                onClickNav = { routing.back() }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DuetTheme.colors.backgroundColor)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PhotoBox()
            InputName(nameGroupState = nameGroupState)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                DefaultFilledTonalButtonDuet(
                    onClick = {
                        viewModel.setNameGroup(nameGroupState.value)
                        viewModel.createGroup()
                    },
                    text = DuetTheme.localization.getString("create"),
                    modifier = Modifier.padding(24.dp),
                    enabled = createStatus != StatusCreateGroup.IN_PROCESS
                )
            }
        }
    }
}

internal const val maxLengthName = 40
internal const val minLengthName = 3

@SuppressLint("DefaultLocale")
@Composable
internal fun InputName(nameGroupState: MutableState<String>) {
    var isErrorInput by remember {
        mutableStateOf(false)
    }

    TextField(
        value = nameGroupState.value,
        onValueChange = { newText ->
            if (newText.length <= maxLengthName) {
                nameGroupState.value = newText
                isErrorInput = false
            } else {
                isErrorInput = true
            }
        },
        label = { Text(text = DuetTheme.localization.getString("inputNameGroup")) },
        supportingText = {
            Text(
                text = String.format(
                    DuetTheme.localization.getString("NoMore"),
                    maxLengthName, minLengthName
                )
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(16.dp),
        colors = defaultTextFieldStyle(),
        isError = isErrorInput,
    )
}

@Composable
internal fun PhotoBox(viewModel: CreateNewGroupViewModel = koinViewModel()) {
    var selectedPhotoUri by remember {
        mutableStateOf(viewModel.screenState.value?.photoUri)
    }

    val photoPiker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            selectedPhotoUri = it
            viewModel.pickPhoto(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clickable {
                photoPiker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
        contentAlignment = Alignment.Center
    ) {
        if (selectedPhotoUri == null) {
            Icon(
                painter = painterResource(id = R.drawable.ic_load_img),
                tint = DuetTheme.colors.mainColor,
                contentDescription = "icon load image"
            )
        } else {
            AsyncImage(
                model = selectedPhotoUri,
                contentDescription = "selected photo",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SetupEmptyNameGroupBottomSheet(viewModel: CreateNewGroupViewModel = koinViewModel()) {
    val sheetState = rememberModalBottomSheetState()
    val stateCreateGroup by viewModel.statusCreateGroup.observeAsState()

    if (stateCreateGroup == StatusCreateGroup.ERROR_START_CREATE_EMPTY_NAME) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                viewModel.setNoneInStatusCreate()
            },
            containerColor = DuetTheme.colors.backgroundColor,
            scrimColor = DuetTheme.colors.errorColor.copy(alpha = 0.2f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_alert),
                    contentDescription = "error icon",
                    tint = DuetTheme.colors.errorColor
                )

                Text(
                    text = DuetTheme.localization.getString("errorNameGroup"),
                    style = defaultTextStyleDuet().copy(color = DuetTheme.colors.errorColor),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }

        }
    }
}

@Composable
fun SetupErrorDialog(viewModel: CreateNewGroupViewModel = koinViewModel()) {
    val statusCreateGroup by viewModel.statusCreateGroup.observeAsState()

    if (statusCreateGroup == StatusCreateGroup.ERROR) {
        DuetAlertDialogError(
            messageText = DuetTheme.localization.getString("checkInternetOrFile"),
            onClose = {
                viewModel.setNoneInStatusCreate()
            }
        )
    }

}

@Composable
fun SetupRoutOnCreateGroup(
    viewModel: CreateNewGroupViewModel = koinViewModel(),
    routing: CreateNewGroupScreenRouting
) {
    val statusCreateGroup by viewModel.statusCreateGroup.observeAsState()
    if (statusCreateGroup == StatusCreateGroup.FINISH) {
        routing.routToMain()
    }
}