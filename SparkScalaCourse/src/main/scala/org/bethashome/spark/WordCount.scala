package org.bethashome.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
object WordCount {


  def main(args: Array[String]){

   Logger.getLogger("org").setLevel(Level.ERROR)
    //create spark context

    val sc = new SparkContext("local[*]","WordCount")

    //read each line of my book into an RDD
    val input = sc.textFile("../book.txt")

    //Split into words separated by space character
    val words = input.flatMap(x=>x.split(" "))

    //count up the occurrences of each word
    val wordCounts = words.countByValue()

    wordCounts.foreach(println)
 }


}
