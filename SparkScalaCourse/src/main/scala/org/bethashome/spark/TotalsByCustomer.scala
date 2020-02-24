package org.bethashome.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object TotalsByCustomer {
  def parseLine(line:String)= {
    val fields = line.split(",")
    val customerID = fields(0).toInt
    val amountSpent = fields(2).toFloat
    (customerID, amountSpent)
  }

  def main(args: Array[String]){
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "TotalsByCustomer")

    // Read each line of input data
    val lines = sc.textFile("../customer-orders.csv")

    // Convert to (customerID,amountSpent) tuples
    val parsedLines = lines.map(parseLine)

    //total spent by customer

    val totalsByCustomerId = parsedLines.reduceByKey((x,y)=> x+y)

    val flipped = totalsByCustomerId.map(x=>(x._2,x._1))

val totalByCustomerIdSorted = flipped.sortByKey()
val results = totalByCustomerIdSorted.collect()

    results.foreach(println)
  }
}
