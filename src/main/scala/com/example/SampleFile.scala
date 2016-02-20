package com.example

import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Rahul Shukla on 20/2/16.
  */
object SampleFile extends App {
  val config = ConfigFactory.load()
  val conf = new SparkConf()
    .setAppName("WordCount")
    .setMaster("spark://ULTP:7077")
    //.setMaster("local")
    //.setSparkHome(config.getString("example.spark.home"))
    .set("spark.cleaner.ttl", "30s")
    .set("spark.app.id", "KnowledgeBase")
    .set("spark.driver.allowMultipleContexts", "true")
  val sc = new SparkContext(conf)
  sc.textFile("build.sbt").flatMap(row => row.split(" ")).map((_, 1)).reduceByKey(_ + _).foreach(println)
}
