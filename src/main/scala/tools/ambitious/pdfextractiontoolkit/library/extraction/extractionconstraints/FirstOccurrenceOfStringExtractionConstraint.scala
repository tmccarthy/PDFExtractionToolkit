package tools.ambitious.pdfextractiontoolkit.library.extraction.extractionconstraints

import technology.tabula
import tools.ambitious.pdfextractiontoolkit.library.extraction.tableextractors.TableExtractor
import tools.ambitious.pdfextractiontoolkit.library.extraction.{ExtractionUtils, StateBundle}
import tools.ambitious.pdfextractiontoolkit.library.model.geometry.Rectangle
import tools.ambitious.pdfextractiontoolkit.library.model.{Document, Page, Table}

case class FirstOccurrenceOfStringExtractionConstraint protected (text: String, textRegion: Rectangle, tableExtractor: TableExtractor)
  extends SimpleExtractionConstraint {

  override def onPage(page: Page, document: Document, stateBundle: StateBundle): Unit = {
    if (shouldExtractOnPage(page, document, stateBundle)) {
      stateBundle.state = Option.apply(page)
    }
  }

  override def tableFromState(stateBundle: StateBundle): Option[Table] = {
    if (stateBundle.state.isDefined) {
      val extractedTable: Table = performExtraction(stateBundle.state.asInstanceOf[Option[Page]].get)

      Option.apply(extractedTable)
    } else {
      Option.empty
    }
  }

  def shouldExtractOnPage(page: Page, document: Document, stateBundle: StateBundle): Boolean = {
    val table: tabula.Table = ExtractionUtils.extractTabulaTableFromPage(page, textRegion)
    val foundText = table.getCell(0, 0).getText

    text == foundText && stateBundle.state.isEmpty
  }
}

object FirstOccurrenceOfStringExtractionConstraint {
  def withTextAndTableExtractor(text: String, textRegion: Rectangle, tableExtractor: TableExtractor) =
    new FirstOccurrenceOfStringExtractionConstraint(text, textRegion, tableExtractor)
}

