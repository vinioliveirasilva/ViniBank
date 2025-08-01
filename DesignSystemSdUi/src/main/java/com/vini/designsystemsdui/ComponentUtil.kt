package com.vini.designsystemsdui

import com.vini.designsystemsdui.property.FontWeightOption
import com.vini.designsystemsdui.property.HorizontalAlignmentOptions
import com.vini.designsystemsdui.property.HorizontalArrangementOptions
import com.vini.designsystemsdui.property.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.ShapeOptions
import com.vini.designsystemsdui.property.TextAlignOption
import com.vini.designsystemsdui.property.VerticalAlignmentOption
import com.vini.designsystemsdui.property.VerticalArrangementOption
import com.vini.designsystemsdui.property.VerticalFillTypeOption
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

typealias Property = JsonObject
typealias Component = JsonObject
typealias Validator = JsonObject
typealias Action = JsonObject

internal object ComponentUtil {
    fun property(name: String, value: String, id: String? = null): Property = buildJsonObject {
        put("name", name)
        put("value", value)
        id?.run { put("id", this) }
    }

    fun property(name: String, value: Boolean, id: String? = null): Property = buildJsonObject {
        put("name", name)
        put("value", value)
        id?.run { put("id", this) }
    }

    fun property(name: String, value: Number, id: String? = null): Property = buildJsonObject {
        put("name", name)
        put("value", value)
        id?.run { put("id", this) }
    }

    fun action(
        type: String,
        data: JsonObject? = null,
        id: String? = null,
        name: String = "OnClick",
    ): Action = buildJsonObject {
        put("type", type)
        put("name", name)
        id?.run { put("id", this) }
        data?.run { put("data", this) }
    }

    fun validator(
        type: String,
        id: String,
        required: List<String>? = null,
        data: JsonObject? = null,
    ): Action = buildJsonObject {
        put("type", type)
        put("id", id)
        required?.run { putJsonArray("required") { forEach { add(JsonPrimitive(it)) } } }
        data?.run { put("data", this) }
    }

    fun component(
        type: String,
        properties: List<Property>? = null,
        components: List<Component>? = null,
        actions: List<Action>? = null,
        validators: List<Validator>? = null,
        vararg customComponents: Pair<String, List<Component>>,
    ): Component = buildJsonObject {
        put("type", type)
        properties?.run { putJsonArray("properties") { forEach { add(it) } } }
        components?.run { putJsonArray("components") { forEach { add(it) } } }
        validators?.run { putJsonArray("validators") { forEach { add(it) } } }
        actions?.run { putJsonArray("actions") { forEach { add(it) } } }
        customComponents.forEach { (name, component) ->
            putJsonArray(name) {
                component.forEach {
                    add(
                        it
                    )
                }
            }
        }
    }

    fun screen(
        flow: String,
        stage: String,
        version: String,
        template: String,
        shouldCache: Boolean,
        components: List<Component>? = null,
    ) = buildJsonObject {
        put("flow", flow)
        put("stage", stage)
        put("version", version)
        put("template", template)
        put("shouldCache", shouldCache)
        components?.run { putJsonArray("components") { forEach { add(it) } } }
    }

    fun jsonObject(vararg pairs: Pair<String, Any?>) = buildJsonObject {
        pairs.forEach { (name, value) ->
            when (value) {
                is String -> put(name, value)
                is JsonElement -> put(name, value)
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun jsonArray(vararg actions: Action) = buildJsonArray {
        actions.forEach { add(it) }
    }

    fun multipleActions(
        actions: List<Action>,
    ): Action = action(
        type = "multipleActions",
        data = jsonObject(
            "actions" to buildJsonArray {
                actions.mapIndexed { index, action ->
                    add(
                        buildJsonObject {
                            action.jsonObject.entries.forEach { (key, value) ->
                                put(key, value)
                            }
                            put("name", index)
                        }
                    )
                }
            }
        )
    )
}


object ComponentProperty {
    class TextProperty(
        val text: String,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("text", text, id)

    class TextAlignProperty(
        val textAlignOption: TextAlignOption = TextAlignOption.Start,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("textAlign", textAlignOption.name, id)

    class FontSizeProperty(
        val fontSize: Int = 14,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("fontSize", fontSize, id)

    class FontWeightProperty(
        val fontWeightOption: FontWeightOption = FontWeightOption.Default,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("fontWeight", fontWeightOption.name, id)


    class VerticalAlignmentProperty(
        val verticalAlignmentOption: VerticalAlignmentOption = VerticalAlignmentOption.Top,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("verticalAlignment", verticalAlignmentOption.name, id)

    class HorizontalAlignmentProperty(
        val horizontalAlignmentOptions: HorizontalAlignmentOptions = HorizontalAlignmentOptions.Start,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("horizontalAlignment", horizontalAlignmentOptions.name, id)

    class VerticalArrangementProperty(
        val verticalArrangementOption: VerticalArrangementOption = VerticalArrangementOption.Top,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("verticalArrangement", verticalArrangementOption.name, id)

    class HorizontalArrangementProperty(
        val horizontalArrangementOptions: HorizontalArrangementOptions = HorizontalArrangementOptions.Start,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("horizontalArrangement", horizontalArrangementOptions.name, id)

    class HorizontalFillTypeProperty(
        val horizontalFillTypeOption: HorizontalFillTypeOption = HorizontalFillTypeOption.None,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("horizontalFillType", horizontalFillTypeOption.name, id)

    class VerticalFillTypeProperty(
        val verticalFillTypeOption: VerticalFillTypeOption = VerticalFillTypeOption.None,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("verticalFillType", verticalFillTypeOption.name, id)

    class PaddingVerticalProperty(
        val padding: Int = 0,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("paddingVertical", padding, id)

    class PaddingHorizontalProperty(
        val padding: Int = 0,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("paddingHorizontal", padding, id)

    class HeightProperty(
        val height: Int,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("height", height, id)

    class WidthProperty(
        val width: Int,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("width", width, id)

    class WeightProperty(
        val weight: Int,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("weight", weight, id)

    class VisibilityProperty(
        val isVisible: Boolean = true,
        val id: String? = null,
    ) : BaseComponentProperty.BooleanComponentProperty("isVisible", isVisible, id)

    class IsEnabledProperty(
        val isEnabled: Boolean = true,
        val id: String? = null,
    ) : BaseComponentProperty.BooleanComponentProperty("isEnabled", isEnabled, id)

    class ShouldShowProperty(
        val shouldShow: Boolean = false,
        val id: String? = null,
    ) : BaseComponentProperty.BooleanComponentProperty("shouldShow", shouldShow, id)

    class ShapeProperty(
        val shapeOption: ShapeOptions,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("shape", shapeOption.name, id)

    class IconNameProperty(
        val name: String,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("icon", name, id)

    class DrawableNameProperty(
        val name: String,
        val id: String? = null,
    ) : BaseComponentProperty.StringComponentProperty("iconDrawable", name, id)

    class SizeProperty(
        val size: Int,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("size", size, id)

    class SelectedNavigationDestinationIndexProperty(
        val index: Int = 0,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("selectedDestination", index, id)

    class DestinationIndexProperty(
        val index: Int = 0,
        val id: String? = null,
    ) : BaseComponentProperty.NumberComponentProperty("index", index, id)
}

object BaseComponentProperty {
    open class BooleanComponentProperty(
        private val name: String,
        private val internalValue: Boolean,
        private val internalId: String? = null,
    ) {
        fun build() = buildJsonObject {
            put("name", name)
            put("value", internalValue)
            internalId?.run { put("id", this) }
        }
    }

    open class StringComponentProperty(
        private val name: String,
        private val internalValue: String,
        private val internalId: String? = null,
    ) {
        fun build() = buildJsonObject {
            put("name", name)
            put("value", internalValue)
            internalId?.run { put("id", this) }
        }
    }

    open class NumberComponentProperty(
        private val name: String,
        private val internalValue: Number,
        private val internalId: String? = null,
    ) {
        fun build() = buildJsonObject {
            put("name", name)
            put("value", internalValue)
            internalId?.run { put("id", this) }
        }
    }
}