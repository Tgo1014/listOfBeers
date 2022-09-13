package tgo1014.listofbeers.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import tgo1014.listofbeers.R
import tgo1014.listofbeers.models.Beer
import tgo1014.listofbeers.ui.composables.BeerImage
import tgo1014.listofbeers.ui.composables.ThemeProvider
import tgo1014.listofbeers.ui.composables.simpleVerticalScrollbar
import tgo1014.listofbeers.ui.theme.ListOfBeersTheme

@Composable
fun BeerDetails(beer: Beer, modifier: Modifier = Modifier) = Surface(modifier) {
    Box {
        val scrollState = rememberLazyListState()
        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(45.dp)
                .padding(end = 20.dp)
                .zIndex(1f)
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(350.dp)
        ) {
            BeerImage(
                beer,
                Modifier
                    .height(200.dp)
                    .width(100.dp)
            )
            Spacer(Modifier.size(12.dp))
            LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.simpleVerticalScrollbar(
                    state = scrollState,
                    color = MaterialTheme.colorScheme.primary.copy(0.2f),
                )
            ) {
                item {
                    Text(
                        text = beer.name.orEmpty(),
                        fontSize = 22.sp,
                        color = Color.White
                    )
                    Text(
                        text = beer.tagline.orEmpty(),
                        fontSize = 18.sp
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.first_brewed),
                        color = Color.White,
                    )
                    Text(text = beer.firstBrewed.orEmpty())
                }
                item {
                    Text(
                        text = stringResource(R.string.description),
                        color = Color.White,
                    )
                    Text(text = beer.description.orEmpty())
                }
                item {
                    Text(
                        text = stringResource(R.string.food_pairing),
                        color = Color.White,
                    )
                    beer.foodPairing.orEmpty().forEach {
                        Text(text = " • $it")
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.brewer_tips),
                        color = Color.White,
                    )
                    Text(text = beer.brewersTips.orEmpty())
                }
            }
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BeerDetailsPreview(
    @PreviewParameter(ThemeProvider::class) materialYouColors: Boolean
) {
    ListOfBeersTheme(materialYouColors = materialYouColors) {
        Surface(color = MaterialTheme.colorScheme.secondaryContainer) {
            BeerDetails(beer = Beer(name = "Punk IPA 2007 - 2010", tagline = "This is a test"))
        }
    }
}