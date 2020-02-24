package org.bethashome.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

object MaxPrecipitation {
  def parseLine(line:String)= {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val precipitation = fields(3).toFloat
    (stationID, entryType, precipitation)
  }
  def main(args: Array[String]){

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MaxPrecipitation")

    // Read each line of input data
    val lines = sc.textFile("../1800.csv")

    // Convert to (stationID, entryType, precipitation) tuples
    val parsedLines = lines.map(parseLine)

    // Filter out all but TMIN entries
    val maxPrecipitation = parsedLines.filter(x => x._2 == "PRCP")

    // Convert to (stationID, precipitation)
    val stationPrecipitation = maxPrecipitation.map(x => (x._1, x._3.toFloat))

    // Reduce by stationID retaining the minimum temperature found
    val maxPrecipitationByStation = stationPrecipitation.reduceByKey( (x,y) => max(x,y))

    // Collect, format, and print the results
    val results = maxPrecipitationByStation.collect()

    for (result <- results.sorted) {
      val station = result._1
      val precipitation = result._2
      val formattedPRCP = f"$precipitation%.2f F"
      println(s"$station maximum Precipitation: $formattedPRCP")
    }
  }
}
