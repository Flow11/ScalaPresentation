package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class CaseClassExemples extends KoanSuite with ShouldMatchers{
//=========================
// Part 1 Class & Case Class
//=========================

  	koan("Creating a class the old way") {
	  
		class Image {
			var _name: String = null
			
			def name = _name
			
			def name_= (value:String) = _name = value
			
			def this(name: String){
			  	this()
				this._name = name
			}
		}
		
		val image = new Image("MyImage")
		  
		image.name should be ("MyImage")
	}
  
	koan("Declaring a class with a constructor") {

		class Image(_name: String){
			def name = _name
			//Won't compile why?
		    //def name_= (value:String) = _name = value
		}
		
		val image = new Image("MyImage")
		  
		image.name should be ("MyImage")
	}
	
	koan("Declaring a class with a constructor and default value") {

		class Image(val name: String = "ImYourValue")
    
		val image = new Image
	  
		image.name should be ("ImYourValue")
	}

  
	koan("Declaring a class with a constructor and default getter") {
	  
		class Image(val name: String)
    
		val image = new Image("MyImage")
	  
		image.name should be ("MyImage")
	}
	
	koan("Declaring a class with a constructor and default getter/setter") {
	  
		class Image(var name: String)
    
		val image = new Image("MyImage")
	  
		image.name should be ("MyImage")
		
		image.name = "Hey You Change Me"
		
		image.name should be("Hey You Change Me")
	}
}