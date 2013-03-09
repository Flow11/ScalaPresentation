package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PartialFunctionExemple extends KoanSuite with ShouldMatchers{
//=========================
// Part 6 Partial Function
//=========================
	koan("Partial function"){
		case class Image(name: String = "Default name", imageType:String = "jpg")
		
		val imageList:List[Image] = List(Image(), Image(imageType="png"))
		
		def jpgFunction: PartialFunction[Image, Image] = new PartialFunction[Image, Image] {
			def isDefinedAt(image: Image) = image.imageType == "jpg"
	      
	    	def apply(image: Image) = Image(name=image.name+".jpg")
		}
		
		def pngFunction: PartialFunction[Image, Image] = new PartialFunction[Image, Image] {
			def isDefinedAt(image: Image) = image.imageType == "png"
	      
	    	def apply(image: Image) = Image(name=image.name+".png")
		}
		
		val genericFunction = jpgFunction orElse pngFunction
		
		genericFunction(imageList(0)).name should be("Default name.jpg")
		genericFunction(imageList(1)).name should be("Default name.png")
	}
}