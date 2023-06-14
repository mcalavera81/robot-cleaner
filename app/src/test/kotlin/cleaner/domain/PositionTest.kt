package cleaner.domain

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PositionTest {

    @Nested
    class NorthHeading{
        private val position =  Position( Coords(1, 2), Heading.NORTH);
        @Test
        fun `After moving forward, should keep the heading, and increment the y coordinate`(){
            val newPosition = position.withForwardMove()
            assertEquals(newPosition.coords, Coords(1,3))
            assertEquals(newPosition.heading, Heading.NORTH)
        }

        @Test
        fun `After a right turn, should update the heading to EAST, and keep the coordinates`(){
            val newPosition = position.withRightTurn()
            assertEquals(newPosition.coords, Coords(1,2))
            assertEquals(newPosition.heading, Heading.EAST)
        }

        @Test
        fun `After a left turn, should update the heading to WEST, and keep the coordinates`(){
            val newPosition = position.withLeftTurn()
            assertEquals(newPosition.coords, Coords(1,2))
            assertEquals(newPosition.heading, Heading.WEST)
        }
    }

    @Nested
    class SouthHeading{
        private val position =  Position( Coords(3, 1), Heading.SOUTH);

        @Test
        fun `After moving forward, should keep the heading, and decrement the y coordinate`(){
            val newPosition = position.withForwardMove()
            assertEquals(newPosition.coords, Coords(3,0))
            assertEquals(newPosition.heading, Heading.SOUTH)
        }

        @Test
        fun `After a right turn, should update the heading to WEST, and keep the coordinates`(){
            val newPosition = position.withRightTurn()
            assertEquals(newPosition.coords, Coords(3,1))
            assertEquals(newPosition.heading, Heading.WEST)
        }

        @Test
        fun `After a left turn, should update the heading to EAST, and keep the coordinates`(){
            val newPosition = position.withLeftTurn()
            assertEquals(newPosition.coords, Coords(3,1))
            assertEquals(newPosition.heading, Heading.EAST)
        }
    }

    @Nested
    class EastHeading{
        private val position =  Position( Coords(2, 2), Heading.EAST);

        @Test
        fun `After moving forward, should keep the heading, and increment the x coordinate`(){
            val newPosition = position.withForwardMove()
            assertEquals(newPosition.coords, Coords(3,2))
            assertEquals(newPosition.heading, Heading.EAST)
        }

        @Test
        fun `After a right turn, should update the heading to SOUTH, and keep the coordinates`(){
            val newPosition = position.withRightTurn()
            assertEquals(newPosition.coords, Coords(2,2))
            assertEquals(newPosition.heading, Heading.SOUTH)
        }

        @Test
        fun `After a left turn, should update the heading to NORTH, and keep the coordinates`(){
            val newPosition = position.withLeftTurn()
            assertEquals(newPosition.coords, Coords(2,2))
            assertEquals(newPosition.heading, Heading.NORTH)
        }
    }

    @Nested
    class WestHeading{
        private val position =  Position( Coords(1, 3), Heading.WEST);

        @Test
        fun `After moving forward, should keep the heading, and decrement the x coordinate`(){
            val newPosition = position.withForwardMove()
            assertEquals(newPosition.coords, Coords(0,3))
            assertEquals(newPosition.heading, Heading.WEST)
        }

        @Test
        fun `After a right turn, should update the heading to NORTH, and keep the coordinates`(){
            val newPosition = position.withRightTurn()
            assertEquals(newPosition.coords, Coords(1,3))
            assertEquals(newPosition.heading, Heading.NORTH)
        }

        @Test
        fun `After a left turn, should update the heading to SOUTH, and keep the coordinates`(){
            val newPosition = position.withLeftTurn()
            assertEquals(newPosition.coords, Coords(1,3))
            assertEquals(newPosition.heading, Heading.SOUTH)
        }
    }

}