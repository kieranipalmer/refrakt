//[refrakt](../../../../index.md)/[dev.shanty.refrakt](../../index.md)/[Device](../index.md)/[Light](index.md)

# Light

[jvm]\
class [Light](index.md)(actor: Actor&lt;[LightActorInput](../../../dev.shanty.refrakt.actors/-light-actor-input/index.md), [LightActorState](../../../dev.shanty.refrakt.actors/-light-actor-state/index.md)&gt;) : [Device](../index.md)

## Constructors

| | |
|---|---|
| [Light](-light.md) | [jvm]<br>fun [Light](-light.md)(actor: Actor&lt;[LightActorInput](../../../dev.shanty.refrakt.actors/-light-actor-input/index.md), [LightActorState](../../../dev.shanty.refrakt.actors/-light-actor-state/index.md)&gt;) |

## Functions

| Name | Summary |
|---|---|
| [setColour](set-colour.md) | [jvm]<br>suspend fun [setColour](set-colour.md)(colour: [HsbkColour](../../../dev.shanty.refrakt.models/-hsbk-colour/index.md), duration: [Duration](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.time/-duration/index.html), waitForResponse: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)<br>Sets the Colour of the Light |
| [setPower](set-power.md) | [jvm]<br>suspend fun [setPower](set-power.md)(power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), waitForResponse: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)<br>Sets the Power of the Light |

## Properties

| Name | Summary |
|---|---|
| [stateEvents](state-events.md) | [jvm]<br>val [stateEvents](state-events.md): StateFlow&lt;[LightActorState](../../../dev.shanty.refrakt.actors/-light-actor-state/index.md)?&gt; |
