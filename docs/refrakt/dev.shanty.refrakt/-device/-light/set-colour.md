//[refrakt](../../../../index.md)/[dev.shanty.refrakt](../../index.md)/[Device](../index.md)/[Light](index.md)/[setColour](set-colour.md)

# setColour

[jvm]\
suspend fun [setColour](set-colour.md)(colour: [HsbkColour](../../../dev.shanty.refrakt.models/-hsbk-colour/index.md), duration: [Duration](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.time/-duration/index.html), waitForResponse: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)

Sets the Colour of the Light

#### Parameters

jvm

| | |
|---|---|
| colour | The target colour of the light |
| duration | The duration the transition of colour should occur over |
| waitForResponse | Whether the coroutine should be suspended until the light has changed colour. Warning: This can potentially hang indefinitely if the light does not respond. It is recommended this function is wrapped in a timeout |
