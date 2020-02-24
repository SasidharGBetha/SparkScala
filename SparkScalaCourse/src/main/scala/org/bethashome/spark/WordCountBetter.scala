package org.bethashome.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
object WordCountBetter {



  def main(args: Array[String]){

    Logger.getLogger("org").setLevel(Level.ERROR)
    //create spark context

    val sc = new SparkContext("local[*]","WordCount")

    //read each line of my book into an RDD
    val input = sc.textFile("../book.txt")

    //Split into words separated by space character
    val words = input.flatMap(x=>x.split("\\W+"))

    //convert everything to lowercase
    val lowercaseWords = words.map(x=>x.toLowerCase())


    //count up the occurrences of each word
    val wordCounts = lowercaseWords.map(x=>(x,1)).reduceByKey((x,y)=>x+y)
    //flip the (word, count) tuples (count, word) and then sort by key (the counts)

    val wordCountsSorted = wordCounts.map(x=>(x._2,x._1)).sortByKey()

    // print the results, flipping the (count, word) results to word: count as we go

    for(results <- wordCountsSorted) {
      val count = results._1
      val word = results._2
      println(s"$word: $count")

    }
  }


}
