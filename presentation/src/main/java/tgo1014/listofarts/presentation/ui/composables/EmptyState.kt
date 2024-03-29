package tgo1014.listofarts.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tgo1014.listofarts.presentation.R
import tgo1014.listofarts.presentation.ui.composables.previews.PreviewDefault
import tgo1014.listofarts.presentation.ui.theme.ListOfArtsTheme

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit = {},
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(R.string.no_items),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .then(modifier)
    )
    Button(onClick = onRetryClicked, shape = MaterialTheme.shapes.small) {
        Text(text = stringResource(id = R.string.retry))
    }
}

@PreviewDefault
@Composable
private fun EmptyStatePreview() = ListOfArtsTheme {
    Surface { EmptyState() }
}