package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ImageSearchEngine extends KoanSuite with ShouldMatchers {

//=========================
// Slide 1
//=========================
  
  class Image(var nom: String, var poid: Float, var url: String, var resolution: String)
  
  koan("Premiers tests") {
    
  }

}


//=========================
// END Slide 1
//=========================