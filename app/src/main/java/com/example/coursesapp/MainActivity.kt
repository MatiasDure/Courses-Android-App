package com.example.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.data.DataSource
import com.example.coursesapp.model.Topic
import com.example.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseApp(modifier = Modifier.padding(innerPadding));
                }
            }
        }
    }
}

@Composable
fun TopicDetails(title: String, lessonCount: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .height(68.dp)
        .background(Color.LightGray)
        .padding(
            top = dimensionResource(id = R.dimen.padding_medium),
            start = dimensionResource(id = R.dimen.padding_medium),
            end = dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(text = title, modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small)), style = MaterialTheme.typography.bodyMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null, modifier.padding(end = dimensionResource(id = R.dimen.padding_small)));
            Text(
                text = lessonCount.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .height(68.dp)
        .clip(RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painterResource(id = topic.imageId),
            contentDescription = null,
            modifier.size(68.dp)
        )
        TopicDetails(stringResource(id = topic.titleId), topic.lessonsCount)
    }
}

@Composable
fun TopicList(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small))
        ) {
        items(topics) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
fun CourseApp(modifier: Modifier = Modifier) {
    TopicList(DataSource.topics.sortedBy { topic -> topic.titleId }, modifier = modifier)
}

@Preview(showSystemUi = true, showBackground = false)
@Composable
fun GreetingPreview() {
    CoursesAppTheme {
        CourseApp();
    }
}