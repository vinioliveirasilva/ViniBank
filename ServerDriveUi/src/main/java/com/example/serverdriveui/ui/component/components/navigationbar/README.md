# NavigationBarComponent

`NavigationBarComponent` represents a Material3 bottom navigation bar in the Server Driven UI (SDUI) layer. It is composed of one or more `NavigationBarItemComponent` instances and exposes a state used to track and change the selected destination.

## Properties

| Name | Type | Default | Description |
|------|------|---------|-------------|
| `selectedDestination` | `Int` | `0` | Index of the currently selected item. This state is also used by each `NavigationBarItemComponent` to update its selected state. |

In addition to the property above, this component also inherits the layout and modifier properties available to every component through `BaseComponent` (e.g. padding, width/height, weight).

## Example

Below is a minimal example of how to describe a navigation bar using SDUI JSON. Each item declares its index and which `selectedDestination` value it controls.

```json
{
  "type": "navigationBar",
  "properties": [
    { "name": "selectedDestination", "value": "0", "id": "nav.selected" }
  ],
  "components": [
    {
      "type": "navigationBarItem",
      "properties": [
        { "name": "index", "value": "0" },
        { "name": "selectedDestination", "value": "0", "id": "nav.selected" }
      ],
      "components": [
        { "type": "text", "properties": [ { "name": "text", "value": "Home" } ] }
      ],
      "selectedIcon": [
        { "type": "icon", "properties": [ { "name": "icon", "value": "Home" } ] }
      ],
      "unselectedIcon": [
        { "type": "icon", "properties": [ { "name": "icon", "value": "HomeOutline" } ] }
      ]
    },
    {
      "type": "navigationBarItem",
      "properties": [
        { "name": "index", "value": "1" },
        { "name": "selectedDestination", "value": "1", "id": "nav.selected" }
      ],
      "components": [
        { "type": "text", "properties": [ { "name": "text", "value": "Card" } ] }
      ],
      "selectedIcon": [
        { "type": "icon", "properties": [ { "name": "icon", "value": "Payment" } ] }
      ],
      "unselectedIcon": [
        { "type": "icon", "properties": [ { "name": "icon", "value": "PaymentOutline" } ] }
      ]
    }
  ]
}
```

The JSON above describes a navigation bar with two destinations ("Home" and "Card"). The `selectedDestination` property defines which item is currently selected and is updated whenever the user taps an item.
