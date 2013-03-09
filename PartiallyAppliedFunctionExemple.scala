package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PartiallyAppliedFunctionExemple extends KoanSuite with ShouldMatchers{
//=========================
// Part 7 Partially applied function
//=========================
	koan("Building a generic filter"){
		case class Image(name: String = "Default name", score:Int=0, tagsList:List[String]=Nil, author:String="Unknown User")
		
		val imageList:List[Image] = List(Image(score=2), Image(score=7),Image(score=6))
		val minScore = 4

		def customFilter(f: Image => Boolean)(imageList:List[Image]) = {
			imageList.filter(f)
		}
		
		def filterHighScore(image: Image):Boolean = image.score > minScore
		def filterLowScore(image: Image):Boolean = image.score < minScore
		
		val highScores = customFilter(filterHighScore) _
		val lowScores = customFilter(filterLowScore) _
		
		highScores(imageList).size should be(2)
		lowScores(imageList).size should be(1)
	}
}