package seleniumtestinglib.locators

import seleniumtestinglib.queries.TextMatch
import seleniumtestinglib.queries.TextMatch.Companion.asJsString
import seleniumtestinglib.queries.asJsExpression
import java.util.regex.Pattern

data class Value(val min: Int? = null, val max: Int? = null, val now: Int? = null, val text: TextMatch? = null) {
    constructor(text: String? = null) : this(text = text?.asJsString())
    constructor(text: Pattern? = null) : this(text = text?.asJsExpression())
}

/*
 * https://www.w3.org/TR/wai-aria-1.2/#aria-current
 */
internal sealed class Current(open val value: Any) {
    override fun toString() = value.toString()
    internal class CurrentAsType(value: CurrentType) : Current(value.name.lowercase().asJsString())
    internal class CurrentAsBool(value: Boolean) : Current(value)
}

@Suppress("UNUSED")
enum class CurrentType {
    Page, Step, Location, Date, Time
}

/*
 * https://www.w3.org/TR/wai-aria-1.2/#role_definitions
 */
@Suppress("UNUSED")
enum class RoleType {
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