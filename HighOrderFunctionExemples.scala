package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class HighOrderFunctionExemples extends KoanSuite with ShouldMatchers{
//=========================
// Part 5 High Order function
//=========================
	koan("Some useful methods on List with High Order Function") {
		case class Image(name: String = "Default name", score:Int = 0)
	  
		val imagesList = List(Image("Sky",5),Image("Sea",8),Image("Mountain",6),Image("Sky",4))
		
		imagesList.filter(image => image.score > 7).size should be(1)
		imagesList.filter(image => image.name == "Sea").size should be(1)
		imagesList.filter(image => image.name == "Sky" && image.score > 4).size should be(1)
	}
	
	koan("Adding some complexity") {
		case class Image(name: String = "Default name", scores:List[Int])
	  
		val imagesList = List(Image("Sky", List(5,2,9,1)),Image("Sea",List(3,6,4,2)),Image("Mountain",List(9,6,4,3)))
		
		// Average score
		// Sky => 4,25
		// Sea => 3,75
		// Mountain => 5,5
		imagesList.filter(image => image.scores.sum/image.scores.size.toDouble > 5.00).size should be(1)
	}
	
	koan("Applying a method on each list value") {
	  	case class Image(name: String = "Default name", score:Int)
	  	
		val imagesList = List(Image("Sky",5),Image("Sea",8),Image("Mountain",6))
		
		//Let's cheat the scores
		imagesList.map{image => image.score+1}.toString should be("List(6, 9, 7)")
	}
	
	koan("A basic search ordered by score"){
	  	case class Image(name: String = "Default name", score:Int)
		val imageList: List[Image] = List(Image("Sky",5),Image("Sea",8))
		
		def SearchExpression = {
			expression:String => imageList.filter(image => image.name == expression).sortBy(image => image.score)
		}

	  	SearchExpression("Sky").size should be(1)
	  	SearchExpression("Sea").size should be(1)
	}
}