package cleaner.domain

data class Position(val coords: Coords, val heading: Heading) {
    fun withForwardMove(): Position = run {
        val step = heading.forwardStep()
        Position(coords.withDelta(step),heading)
    }
    fun withRightTurn(): Position = run {
        val newHeading = heading.rightTurn()
        Position(coords, newHeading)
    }
    fun withLeftTurn(): Position = run {
        val newHeading = heading.leftTurn()
        Position(coords, newHeading)
    }
}
