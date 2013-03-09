package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PatternMatchingExemple extends KoanSuite with ShouldMatchers{
//=========================
// Part 8 Pattern Matching
//=========================
  
	koan("Pattern Matching"){
		def makeSearch(search:String) = {
		    search match {
				case "Cloud" => "Processing search with word 'Cloud'"
				case "Sky" => "Processing search with word 'Sky'"
				case "Sun" => "Processing search with word 'Sun'"
				case _ => "Couldn't find anything with the word: %s".format(search)
		    }
		}
		
		makeSearch("Sky") should be("Processing search with word 'Sky'")
	}
	
	koan("Pattern Matching & option"){
		def makeSearch(search:String):Option[String] = {
		    search match {
				case "Cloud" => Some("Processing search with word 'Cloud'")
				case "Sky" => Some("Processing search with word 'Sky'")
				case "Sun" => Some("Processing search with word 'Sun'")
				case _ => None
		    }
		}

		makeSearch("Sky").get should be("Processing search with word 'Sky'")
		makeSearch("Rocket") should be(None)
	}
	
	koan("Pattern Matching & option the other way"){
		def makeSearch(search:Option[String]) = {
		    search match {
				case Some(search) => "Processing search with word '%s'".format(search)
				case None => "Nothing to search"
		    }
		}

		makeSearch(Some("Sky")) should be("Processing search with word 'Sky'")
		makeSearch(None) should be("Nothing to search")
	}
}