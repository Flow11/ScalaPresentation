package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ImageSearchEngine extends KoanSuite with ShouldMatchers {

//=========================
// Part 1 Class & Case Class / Val & Var
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

//=========================
// END Part 1
//=========================


//=========================
// Part 2 Trait
//=========================

	koan("Introducing Trait") {
		trait ImageInfos{
			def ImageInfos():String
		}
		
		case class Image(name: String, uploader: String) extends ImageInfos{
			def ImageInfos():String = {
				return this.name + " from: " + this.uploader
			}
		}
		
		val image = Image("FlyingPig" , "FlyingPigLover43")
		
		image.ImageInfos() should be ("FlyingPig from: FlyingPigLover43")
	}
	
	koan("I'm a little more than just an interface") {
		trait ImageInfos{
			val image:Image
			def ImageInfos:String = {
				return image.name + " from: " + image.uploader
			}
		}
		
		case class Image(name: String, uploader: String) extends ImageInfos{
			val image = this
		}
		
		val image = Image("FlyingPig" , "FlyingPigLover43")
		
		image.ImageInfos should be ("FlyingPig from: FlyingPigLover43")
	}
	
	koan("Cleaner Version") {
		trait ImageInfos{
			val image:Image
		}
		
		trait ImageInfosExtented extends ImageInfos{
		  	def ImageInfos:String = {
				return image.name + " from: " + image.uploader
			}
		}
		
		case class Image(name: String, uploader: String) extends ImageInfosExtented{
			val image = this
		}
		
		val image = Image("FlyingPig" , "FlyingPigLover42")
		
		image.ImageInfos should be ("FlyingPig from: FlyingPigLover42")
	}
	
	koan("Ordered Trait"){
		case class Image(name: String, score:Int) extends Ordered[Image]{
			def compare(image:Image) = {
				this.score - image.score
			}
		}
		
		val imageList = List(Image("IHaveALowRating",1),Image("IHaveAHighRating",3),Image("LeaveMeAlone",2))
		
		imageList.toString should be("List(Image(IHaveALowRating,1), Image(IHaveAHighRating,3), Image(LeaveMeAlone,2))")
		imageList.sorted.toString() should be("List(Image(IHaveALowRating,1), Image(LeaveMeAlone,2), Image(IHaveAHighRating,3))")
	}
	
	koan("Trait everywhere"){
	  	trait ImageInfos{
			val image:Image
			def ImageInfos:String = {
				return image.name + " from: " + image.uploader
			}
		}
	  
		trait ComputeAverageScore{
			val scoreValuesToCompute:List[Int]
			def ComputeAverageScore:Float = {
				return scoreValuesToCompute.sum / scoreValuesToCompute.size
			}
		}
		
		case class Image(name: String, uploader: String, scoreValues:List[Int]) extends ImageInfos with ComputeAverageScore{
			val image = this
			val scoreValuesToCompute = this.scoreValues
		}
		
		val image = Image("FlyingElephant", "FlyingElephantLover42", List(8,4))
		
		image.ImageInfos should be("FlyingElephant from: FlyingElephantLover42")
		image.ComputeAverageScore should be(6)
	}


//=========================
// END Part 2 Trait
//=========================

//=========================
// Part 3 List/Map & high Order function
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
	
//=========================
// END Part 3 List/Map
//=========================
	
//=========================
// Part 4 Building some features for our app
//=========================

	koan("A basic search ordered by score"){
	  	case class Image(name: String = "Default name", score:Int)
		val imageList: List[Image] = List(Image("Sky",5),Image("Sea",8))
		
		def SearchExpression = {
			expression:String => imageList.filter(image => image.name == expression).sortBy(image => image.score)
		}

	  	SearchExpression("Sky").size should be(1)
	  	SearchExpression("Sea").size should be(1)
	}
	
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
	
//=========================
// Part 4 Building some feature for our app
//=========================

	
//=========================
// Part 5 Pattern Matching / Option
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