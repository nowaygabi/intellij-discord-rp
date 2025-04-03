package io.github.pandier.intellijdiscordrp.settings

enum class FileStyleSetting(
    private val friendlyName: String
) {
    DEFAULT("Default"),
    VSCORD("VsCord");

    override fun toString(): String =
        friendlyName
}
