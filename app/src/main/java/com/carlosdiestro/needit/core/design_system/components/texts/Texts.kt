package com.carlosdiestro.needit.core.design_system.components.texts

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HeadlineLarge(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
}

@Composable
fun HeadlineMedium(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun HeadlineMedium(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun HeadlineSmall(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}

@Composable
fun TitleLarge(
    @StringRes labelId: Int,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun TitleMedium(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun TitleSmall(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
    )
}

@Composable
fun TitleSmall(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun BodyLarge(
    @StringRes labelId: Int,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun BodyLarge(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun BodyMedium(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun BodyMedium(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun BodySmall(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun LabelLarge(
    @StringRes labelId: Int,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.labelLarge,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun LabelMedium(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}

@Composable
fun LabelSmall(
    @StringRes labelId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
    )
}


