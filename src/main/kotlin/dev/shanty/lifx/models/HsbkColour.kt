package dev.shanty.lifx.models

data class HsbkColour(
    val hue: UShort,
    val saturation: UShort,
    val brightness: UShort,
    val kelvin: UShort,
) {

    constructor(hue: Int, saturation: Float, brightness: Float, kelvin: Float) : this(
        ((hue.toDouble() / 360.0) * 65535).toUInt().toUShort(),
        (saturation.toDouble() * 65535).toUInt().toUShort(),
        (brightness.toDouble() * 65535).toUInt().toUShort(),
        (kelvin.toDouble() * 7500 + 1500).toUInt().toUShort(),
    )

}
