package cleaner.domain

data class Coords(val x: Int, val y: Int) {
    fun withDelta(delta: Coords): Coords =
        Coords(this.x + delta.x, this.y + delta.y)

}
