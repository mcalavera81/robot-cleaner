package cleaner.domain

import kotlin.math.abs

data class Terrain(val maxX: Int, val maxY:Int) {
    fun isOffLimits(coords: Coords): Boolean = run {
        coords.x > maxX || coords.x < 0 || coords.y > maxY || coords.y < 0
    }
}
