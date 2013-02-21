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


}