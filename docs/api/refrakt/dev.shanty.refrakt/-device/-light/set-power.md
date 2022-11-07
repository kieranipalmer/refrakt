//[refrakt](../../../../index.md)/[dev.shanty.refrakt](../../index.md)/[Device](../index.md)/[Light](index.md)/[setPower](set-power.md)

# setPower

[jvm]\
suspend fun [setPower](set-power.md)(power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), waitForResponse: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)

Sets the Power of the Light

#### Parameters

jvm

| | |
|---|---|
| power | Boolean representing the desired power of the device. False for off and True for on |
| waitForResponse | Whether the coroutine should be suspended until the light has changed power. Warning: This can potentially hang indefinitely if the light does not respond. It is recommended this function is wrapped in a timeout |
