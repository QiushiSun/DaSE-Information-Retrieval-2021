import java.io.{File, PrintWriter}

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object ReadGetIndex {
//  val writer = new PrintWriter(new File("spark_index_test.txt" ))
  def main(args: Array[String]): Unit = {
    //get Spark Session
    val spark = SparkSession.builder().appName("Information-Retrieval-Project2")
      .master("local")
      .getOrCreate()
    //Get Dir
    val document_source = spark.sparkContext.wholeTextFiles("src/data-2500")
    //Get File Name
    val res = document_source.flatMap{ x =>
      val doc = x._1
        .split(s"/")
        .last // get file name.txt
        .split("\\.")
        .head // get file name
      x._2.split("\r\n") //seperate -> line -> blank
        .flatMap(_.split(" ").map { y => (y, doc)})}
        .groupByKey.map{case(x,y)=>(x,y.toSet.mkString(","))} // mkstring即用指定字符串分隔
    // pattern matching
    // 此处需要注意partition!
    res.coalesce(1).saveAsTextFile("spark_index_test.txt")
    res.foreach(println)
  }
}