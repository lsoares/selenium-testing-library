package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.queries.ByType
import seleniumtestinglib.queries.JsType
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import seleniumtestinglib.queries.JsType.Companion.asJsString
import seleniumtestinglib.queries.asJsExpression
import seleniumtestinglib.queries.executeTLQuery

/**
 * https://testing-library.com/docs/queries/byrole
 */
data class ByRole(
    private val role: Role,
    private val name: JsType? = null,
    private val description: JsType? = null,
    private val hidden: Boolean? = null,
    private val normalizer: String? = null,
    private val selected: Boolean? = null,
    private val busy: Boolean? = null,
    private val checked: Boolean? = null,
    private val pressed: Boolean? = null,
    private val suggest: Boolean? = null,
    private val current: Current? = null,
    private val expanded: Boolean? = null,
    private val level: Int? = null,
    private val value: Value? = null,
    private val queryFallbacks: Boolean? = null,
) : By() {

    private var _current: Any? = current?.name?.lowercase()?.asJsString()

    fun enableQueryFallbacks() = copy(queryFallbacks = true)
    fun withName(name: Regex) = copy(name = name.asJsExpression())
    fun witDescription(description: Regex) = copy(description = description.asJsExpression())

    constructor(
        role: Role,
        name: String? = null,
        description: String? = null,
        hidden: Boolean? = null,
        normalizer: String? = null,
        selected: Boolean? = null,
        busy: Boolean? = null,
        checked: Boolean? = null,
        pressed: Boolean? = null,
        suggest: Boolean? = null,
        current: Boolean? = null,
        expanded: Boolean? = null,
        level: Int? = null,
        value: Value? = null,
    ) : this(
        role = role,
        name = name?.asJsString(),
        description = description?.asJsString(),
        hidden = hidden,
        normalizer = normalizer,
        selected = selected,
        busy = busy,
        checked = checked,
        pressed = pressed,
        suggest = suggest,
        expanded = expanded,
        level = level,
        value = value,
    ) {
        _current = current
    }

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Role,
            textMatch = role.name.lowercase().asJsString(),
            options = mapOf(
                "hidden" to hidden,
                "normalizer" to normalizer?.asJsExpression(),
                "name" to name,
                "description" to description,
                "selected" to selected,
                "busy" to busy,
                "checked" to checked,
                "pressed" to pressed,
                "suggest" to suggest,
                "current" to _current,
                "expanded" to expanded,
                "queryFallbacks" to queryFallbacks,
                "level" to level,
                "value" to value?.toMap(),
            ),
        )

    data class Value(val min: Int? = null, val max: Int? = null, val now: Int? = null, val text: JsType? = null)

    private fun Value.toMap() =
        mapOf("min" to min, "max" to max, "now" to now, "text" to text).filterValues { it != null }
}

/*
 * https://www.w3.org/TR/wai-aria-1.2/#aria-current
 */
@Suppress("UNUSED")
enum class Current {
    Page, Step, Location, Date, Time
}

/*
 * https://www.w3.org/TR/wai-aria-1.2/#role_definitions
 */
@Suppress("UNUSED")
enum class Role {
    Alert,
    AlertDialog,
    Application,
    Article,
    Banner,
    Button,
    Cell,
    CheckBox,
    ColumnHeader,
    ComboBox,
    Command,
    Comment,
    Complementary,
    Composite,
    ContentInfo,
    Definition,
    Dialog,
    Directory,
    Document,
    Feed,
    Figure,
    Form,
    Generic,
    Grid,
    GridCell,
    Group,
    Heading,
    Img,
    Input,
    Landmark,
    Link,
    List,
    ListBox,
    ListItem,
    Log,
    Main,
    Mark,
    Marquee,
    Math,
    Menu,
    MenuBar,
    MenuItem,
    MenuItemCheckBox,
    MenuItemRadio,
    Meter,
    Navigation,
    None,
    Note,
    Option,
    Presentation,
    ProgressBar,
    Radio,
    RadioGroup,
    Range,
    Region,
    RoleType,
    Row,
    RowGroup,
    RowHeader,
    ScrollBar,
    Search,
    SearchBox,
    Section,
    SectionHead,
    Select,
    Separator,
    Slider,
    SpinButton,
    Status,
    Structure,
    Suggestion,
    Switch,
    Tab,
    Table,
    TabList,
    TabPanel,
    Term,
    TextBox,
    Timer,
    Toolbar,
    Tooltip,
    Tree,
    TreeGrid,
    TreeItem,
    Widget,
    Window,
}