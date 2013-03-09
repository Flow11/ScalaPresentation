package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ValVarExemples extends KoanSuite with ShouldMatchers{
//=========================
// Part 2 Val & Var
//=========================
  
	koan("A var can be change but not a val reference") {
	  
		class Image(var name: String)
    
		val imageA = new Image("MyImageA")
		val imageB = new Image("MyImageB")
	  
		// Won't compile
		//imageA = imageB
		
		var imageA2 = new Image("imageA")
		var imageB2 = new Image("imageB")
		
		// Works!
		imageA2 = imageB2
		
		imageA2.name should be("imageB")
		
	}
	
	koan("Comparing classes") {
	  
	  	class Image(name: String)
    
		val imageA = new Image("MyImageA")
		val imageB = new Image("MyImageB")
		val imageC = new Image("MyImageA")
	  
		imageA == imageB should be(false)
	  	imageA == imageC should be(false)
	}
	
	koan("Case class") {
	  
	  	case class Image(name: String)
    
		val image = Image("MyImage")
	  
		image.name should be ("MyImage")
	}
	
	koan("Comparing case classes") {
	  
	  	case class Image(name: String = "ImYourValue")
    
		val imageA = new Image
		val imageB = new Image("MyImage")
		val imageC = new Image
	  
		imageA == imageB should be(false)
	  	imageA == imageC should be(true)
	}
	
	koan("Case classes can be disassembled") {
	  
	  	case class Image(name: String = "ImYourValue", url:String = "greatpicture.jpg")
    
		val image = new Image
		val imageSplited = Image.unapply(image).get
		
		imageSplited._1 should be("ImYourValue")
	  	imageSplited._2 should be("greatpicture.jpg")
	}
}