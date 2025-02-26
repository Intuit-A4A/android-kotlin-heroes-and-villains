package com.example.heroesandvillains

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.heroesandvillains.data.DataAccessState
import com.example.heroesandvillains.network.HeroRepository
import com.example.heroesandvillains.ui.components.HeroesVillainsItem

/**
 * Activity to display the list of heroes
 */
class HeroListActivity : AppCompatActivity() {

    private val heroViewModel by viewModels<HeroListViewModel> {
        HeroesAndVillainsViewModelFactory(
            repository = HeroRepository()
        )
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val heroesAndVillainData = heroViewModel.heroesListContentState.collectAsStateWithLifecycle()

            when(heroesAndVillainData.value) {

                is DataAccessState.Loading -> {
                    // Loading State
                }
                is DataAccessState.Error -> {
                    // Error State
                }
                is DataAccessState.Success -> Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HeroListScreenUI()
                }
            }
        }
    }

    @Composable
    private fun HeroListScreenUI() {
        val focusManager = LocalFocusManager.current
        val searchedHeroList by heroViewModel.searchedHeroList.collectAsStateWithLifecycle()
        Scaffold(
            topBar = {
                Column {
                    Text(
                        text = stringResource(id = R.string.list_activity_title),
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            },
            containerColor = colorResource(id = R.color.color_container_background_secondary)
        ) { paddingValue ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValue)
            ) {
                items(items = searchedHeroList) {heroDataModel ->
                    HeroesVillainsItem(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        dataModel = heroDataModel,
                        onItemSelected = { id ->
                            focusManager.clearFocus()
                            Toast.makeText(this@HeroListActivity, "Selected item id: $id", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
