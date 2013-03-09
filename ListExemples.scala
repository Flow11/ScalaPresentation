package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ListExemples extends KoanSuite with ShouldMatchers{
//=========================
// Part 4 List
//=========================

	koan("Introducing List") {
		case class Image(name: String = "Default name", score:Int = 0)
	  
		val imagesList = List(Image,Image,Image)
		imagesList.size should be(3)
		
		//List are immutable
		imagesList.drop(3)
		imagesList.size should be(3)
	}
	
	koan("Testing equalitty on List") {
		case class Image(name: String = "Default name", score:Int = 0)
	  
		val imagesList = List(Image,Image,Image)
		val imagesList2 = List(Image,Image,Image)
		
		imagesList == imagesList2 should be(true)
		imagesList eq imagesList2 should be(false)
	}
	
	koan("Some useful methods on List") {
		case class Image(name: String = "Default name", score:Int = 0)
	  
		val imagesList = List(Image("I have my own name"),Image,Image)

		//Removing duplicate
		imagesList.distinct.size should be(2)
		//Reversing the list order
		imagesList.reverse should be(List(Image,Image,Image("I have my own name")))
		//ToString
		imagesList.toString should be("List(Image(I have my own name,0), Image, Image)")
	}
}