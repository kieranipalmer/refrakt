# Refrakt
A Kotlin implementation of the [Lifx LAN Protocol](https://lan.developer.lifx.com/docs/introduction).

## Getting Started

### Install Library

```kotlin
repositories {
    ...
    maven {
        url = uri("https://maven.pkg.github.com/kieranipalmer/refrakt")
    }
}

dependencies {
    implementation("dev.shanty:refrakt:0.0.1-SNAPSHOT")
}
```

### Setup

To begin communicating with Lifx Devices, you must instantiate an instance of [Lifx](./docs/api/refrakt/dev.shanty.refrakt/-lifx/-lifx.md).
This class requires a Coroutine Scope.
An example can be seen below:
```kotlin
    fun main() = runBlocking {
        val lifx = Lifx(this)
    }
```

Once created, the Lifx instance will begin Discovery of devices on the local network.
You can begin listening for the discovered devices by collecting the `discoveryEvents` flow.
```kotlin
    val lifx = Lifx(this)
    lifx.discoveryEvents.onEach { device ->
        println("Discovered Device $device")
        when(device) {
            is Device.Light -> device.setPower(true)
        }
    }.collect()
```


