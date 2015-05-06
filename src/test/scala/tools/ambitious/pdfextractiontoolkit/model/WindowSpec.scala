package tools.ambitious.pdfextractiontoolkit.model

import org.scalatest.FreeSpec
import tools.ambitious.pdfextractiontoolkit.model.constraints.PageNumberConstraint
import tools.ambitious.pdfextractiontoolkit.model.geometry.{PositivePoint, Size}

class WindowSpec extends FreeSpec {
  "A Window instantiated with location equal to (0,10) and size equal to (100, 50)" - {
    val location: PositivePoint = new PositivePoint(0, 10)
    val size: Size = new Size(100, 50)

    val window: Window = new Window(location, size)

    "should have location" - {
      ".x equal to 0" in {
        assert(window.location.x == 0)
      }

      ".y equal to 10" in {
        assert(window.location.y == 10)
      }
    }

    "should have size" - {
      ".width equal to 100" in {
        assert(window.size.width == 100)
      }

      ".height equal to 50" in {
        assert(window.size.height == 50)
      }
    }

    "should have a constraints property which is an empty List" in {
      assert(window.constraints.isEmpty)
    }

    "should be able to add constraints" in {
      val pageNumberConstraint: PageNumberConstraint = new PageNumberConstraint(1)
      window.addConstraint(pageNumberConstraint)
    }
  }

  "A Window instantiated from absolute coordinates 5,10,20,35" - {
    val window: Window = Window.fromAbsoluteCoordinates(5,10,20,35)

    "should have location.x equal to 5" in {
      assert(window.location.x == 5)
    }

    "should have location.y equal to 10" in {
      assert(window.location.y == 10)
    }

    "should have size.width equal to 15" in {
      assert(window.size.width == 15)
    }

    "should have size.height equal to 25" in {
      assert(window.size.height == 25)
    }

    "should have left coordinate equal to 5" in {
      assert(window.leftCoordinate == 5)
    }

    "should have top coordinate equal to 10" in {
      assert(window.topCoordinate == 10)
    }

    "should have right coordinate equal to 20" in {
      assert(window.rightCoordinate == 20)
    }

    "should have bottom coordinate equal to 35" in {
      assert(window.bottomCoordinate == 35)
    }
  }
}
