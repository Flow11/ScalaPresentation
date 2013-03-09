package org.functionalkoans.forscala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TraitExemples extends KoanSuite with ShouldMatchers{
//=========================
// Part 3 Trait
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