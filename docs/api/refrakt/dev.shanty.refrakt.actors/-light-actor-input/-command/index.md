//[refrakt](../../../../index.md)/[dev.shanty.refrakt.actors](../../index.md)/[LightActorInput](../index.md)/[Command](index.md)

# Command

[jvm]\
interface [Command](index.md) : [LightActorInput](../index.md)

## Types

| Name | Summary |
|---|---|
| [SetColour](-set-colour/index.md) | [jvm]<br>data class [SetColour](-set-colour/index.md)(val colour: [HsbkColour](../../../dev.shanty.refrakt.models/-hsbk-colour/index.md), val duration: [Duration](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.time/-duration/index.html)) : [LightActorInput.Command](index.md) |
| [SetPower](-set-power/index.md) | [jvm]<br>data class [SetPower](-set-power/index.md)(val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [LightActorInput.Command](index.md) |

## Inheritors

| Name |
|---|
| [SetColour](-set-colour/index.md) |
| [SetPower](-set-power/index.md) |
