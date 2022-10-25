package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

data class ByRole(
    private val role: String,
    private val selected: Boolean? = null,
) : By() {
    override fun findElements(context: SearchContext) =
        with(TestingLibraryScript) {
            getWebDriver(context).findAllBy(
                "Role",
                role,
                mapOf("selected" to selected)
                    .filterValues { it != null },
            )
        }
}
