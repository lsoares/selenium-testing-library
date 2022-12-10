package seleniumtestinglib

import org.openqa.selenium.By
import org.openqa.selenium.By.tagName
import org.openqa.selenium.NoSuchFrameException
import org.openqa.selenium.WebDriver

fun WebDriver.TargetLocator.frame(selectorWithinIFrame: By): WebDriver? {
    defaultContent().findElements(tagName("iframe")).forEach {
        val iFrame = defaultContent().switchTo().frame(it)
        iFrame.findElements(selectorWithinIFrame).firstOrNull()?.let { return iFrame }
    }
    throw NoSuchFrameException("iframe for selector $selectorWithinIFrame does not exist")
}
