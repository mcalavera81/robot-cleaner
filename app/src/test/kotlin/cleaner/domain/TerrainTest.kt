package cleaner.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TerrainTest {

    private val terrain = Terrain(maxX = 10, maxY = 10);

    @ParameterizedTest
    @MethodSource("coordinates")
    fun testCoordinates(coords: Coords, offLimits: Boolean) {
        assertEquals(terrain.isOffLimits(coords), offLimits)
    }

    companion object {
        @JvmStatic
        fun coordinates() = listOf(
            Arguments.of(Coords(0, 0), false),
            Arguments.of(Coords(1, 1), false),
            Arguments.of(Coords(11, 10), true),
            Arguments.of(Coords(11, 0), true),
            Arguments.of(Coords(10, 11), true),
            Arguments.of(Coords(0, 11), true),
            Arguments.of(Coords(11, 11), true),
            Arguments.of(Coords(10, 10), false),
            Arguments.of(Coords(10, -1), true),
            Arguments.of(Coords(-1, 10), true),
            Arguments.of(Coords(-1, -1), true),
        )
    }
}
