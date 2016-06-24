/**
 * Created by xbkaishui on 16-6-21.
 */

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.streaming.{StreamingContext, Seconds}
import org.apache.spark.streaming.{State, StateSpec, Time}

object StreamingStateTest {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("StreamingStateTest").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sc, batchDuration = Seconds(5))

    // checkpointing is mandatory
//    ssc.checkpoint("/tmp/ck")

    val rdd = sc.parallelize(0 to 4).map(n => (n, n*n % 2 toString))
    import org.apache.spark.streaming.dstream.ConstantInputDStream
    val sessions = new ConstantInputDStream(ssc, rdd)

    val updateState = (batchTime: Time, key: Int, value: Option[String], state: State[Int]) => {
      println(s">>> batchTime = $batchTime")
      println(s">>> key       = $key")
      println(s">>> value     = $value")
      println(s">>> state     = $state")
      val sum = value.getOrElse("").size + state.getOption.getOrElse(0)
      state.update(sum)
      Some((key, value, sum)) // mapped value
    }
    val spec = StateSpec.function(updateState)

    val mappedStatefulStream = sessions.mapWithState(spec)

    mappedStatefulStream.print(10)

    sessions.print(4)

    ssc.start()
    ssc.awaitTermination()
  }


}
