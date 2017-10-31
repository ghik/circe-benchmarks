package io.circe.benchmarks

import com.avsystem.commons.serialization.GenCodec
import com.avsystem.commons.serialization.json.{CirceJsonInput, CirceJsonOutput, JsonStringInput, JsonStringOutput}
import io.circe._
import org.openjdk.jmh.annotations._

trait GenCodecFooInstances {
  implicit val fooCodec: GenCodec[Foo] = GenCodec.materialize
}

trait GenCodecData { self: ExampleData =>
}

trait GenCodecWriting { self: ExampleData =>
  @Benchmark
  def writeFoosGenCodec: String = JsonStringOutput.write(foos)

  @Benchmark
  def writeIntsGenCodec: String = JsonStringOutput.write(ints)
}

trait GenCodecReading { self: ExampleData =>
  @Benchmark
  def readFoosGenCodec: Map[String, Foo] = JsonStringInput.read[Map[String, Foo]](foosJson)

  @Benchmark
  def readIntsGenCodec: List[Int] = JsonStringInput.read[List[Int]](intsJson)
}

trait GenCodecEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosGenCodec: Json = CirceJsonOutput.write(foos)

  @Benchmark
  def encodeIntsGenCodec: Json = CirceJsonOutput.write(ints)
}

trait GenCodecDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosGenCodec: Map[String, Foo] = CirceJsonInput.read[Map[String, Foo]](foosC)

  @Benchmark
  def decodeIntsGenCodec: List[Int] = CirceJsonInput.read[List[Int]](intsC)
}
