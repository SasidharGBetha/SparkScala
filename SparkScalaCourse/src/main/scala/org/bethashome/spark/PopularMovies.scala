package org.bethashome.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object PopularMovies {

  def main(args: Array[String]): Unit ={

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "PopularMovies")

    // Read each line of input data
    val lines = sc.textFile("../ml-100k/u.data")

    //Map to (movieID, 1) tuples

    val movies = lines.map(x => (x.split("\t")(1).toInt,1))

    //count up all the 1's for each movie

    val movieCounts = movies.reduceByKey((x,y) => x+y)

    


  }

}
