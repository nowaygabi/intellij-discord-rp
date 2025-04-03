package io.github.pandier.intellijdiscordrp.settings

import io.github.pandier.intellijdiscordrp.activity.ActivityDisplayMode
import io.github.pandier.intellijdiscordrp.activity.ActivityFactory
import io.github.pandier.intellijdiscordrp.settings.project.DiscordProjectSettings

data class DiscordSettings(
    var reconnectOnUpdate: Boolean = true,
    var customApplicationIdEnabled: Boolean = false,
    var customApplicationId: String = "",
    var defaultDisplayMode: ActivityDisplayMode = ActivityDisplayMode.FILE,
    var focusTimeoutEnabled: Boolean = true,
    var focusTimeoutMinutes: Int = 20,
    var logoStyle: LogoStyleSetting = LogoStyleSetting.MODERN,
    var fileStyle: FileStyleSetting = FileStyleSetting.DEFAULT,
    var showFullApplicationName: Boolean = false,

    var applicationDetails: String = "",
    var applicationState: String = "",
    var applicationLargeImage: ImageSetting = ImageSetting.APPLICATION,
    var applicationLargeImageEnabled: Boolean = true,
    var applicationLargeImageText: String = "{app_name}",
    var applicationSmallImage: ImageSetting = ImageSetting.APPLICATION,
    var applicationSmallImageEnabled: Boolean = false,
    var applicationSmallImageText: String = "",
    var applicationTimestampEnabled: Boolean = true,

    var projectDetails: String = "In {project_name}",
    var projectState: String = "",
    var projectLargeImage: ImageSetting = ImageSetting.APPLICATION,
    var projectLargeImageEnabled: Boolean = true,
    var projectLargeImageText: String = "{app_name}",
    var projectSmallImage: ImageSetting = ImageSetting.APPLICATION,
    var projectSmallImageEnabled: Boolean = false,
    var projectSmallImageText: String = "",
    var projectTimestampEnabled: Boolean = true,
    var projectTimestampTarget: TimestampTargetSetting = TimestampTargetSetting.PROJECT,

    var fileDetails: String = "In {project_name}",
    var fileState: String = "Editing {file_name}",
    var fileLargeImage: ImageSetting = ImageSetting.FILE,
    var fileLargeImageEnabled: Boolean = true,
    var fileLargeImageText: String = "{file_type}",
    var fileSmallImage: ImageSetting = ImageSetting.APPLICATION,
    var fileSmallImageEnabled: Boolean = true,
    var fileSmallImageText: String = "{app_name}",
    var fileTimestampEnabled: Boolean = true,
    var fileTimestampTarget: TimestampTargetSetting = TimestampTargetSetting.PROJECT,
) {
    private fun createApplicationActivityFactory(): ActivityFactory = ActivityFactory(
        displayMode = ActivityDisplayMode.APPLICATION,
        logoStyle = logoStyle,
        fileStyle = fileStyle,
        details = applicationDetails,
        state = applicationState,
        largeImage = if (applicationLargeImageEnabled) applicationLargeImage else null,
        largeImageText = applicationLargeImageText,
        smallImage = if (applicationSmallImageEnabled) applicationSmallImage else null,
        smallImageText = applicationSmallImageText,
        buttonText = null,
        buttonUrl = "",
        timestampEnabled = applicationTimestampEnabled,
        timestampTarget = TimestampTargetSetting.APPLICATION,
    )

    private fun createProjectActivityFactory(projectSettings: DiscordProjectSettings?): ActivityFactory =
        ActivityFactory(
            displayMode = ActivityDisplayMode.PROJECT,
            logoStyle = logoStyle,
            fileStyle = fileStyle,
            details = projectDetails,
            state = projectState,
            largeImage = if (projectLargeImageEnabled) projectLargeImage else null,
            largeImageText = projectLargeImageText,
            smallImage = if (projectSmallImageEnabled) projectSmallImage else null,
            smallImageText = projectSmallImageText,
            buttonText = if (projectSettings?.buttonEnabled == true) projectSettings.buttonText else null,
            buttonUrl = projectSettings?.buttonUrl ?: "",
            timestampEnabled = projectTimestampEnabled,
            timestampTarget = projectTimestampTarget,
        )

    private fun createFileActivityFactory(projectSettings: DiscordProjectSettings?): ActivityFactory = ActivityFactory(
        displayMode = ActivityDisplayMode.FILE,
        logoStyle = logoStyle,
        fileStyle = fileStyle,
        details = fileDetails,
        state = fileState,
        largeImage = if (fileLargeImageEnabled) fileLargeImage else null,
        largeImageText = fileLargeImageText,
        smallImage = if (fileSmallImageEnabled) fileSmallImage else null,
        smallImageText = fileSmallImageText,
        buttonText = if (projectSettings?.buttonEnabled == true) projectSettings.buttonText else null,
        buttonUrl = projectSettings?.buttonUrl ?: "",
        timestampEnabled = fileTimestampEnabled,
        timestampTarget = fileTimestampTarget,
    )

    fun createActivityFactory(mode: ActivityDisplayMode, projectSettings: DiscordProjectSettings?) = when (mode) {
        ActivityDisplayMode.APPLICATION -> createApplicationActivityFactory()
        ActivityDisplayMode.PROJECT -> createProjectActivityFactory(projectSettings)
        ActivityDisplayMode.FILE -> createFileActivityFactory(projectSettings)
    }
}
