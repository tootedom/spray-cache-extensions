package org.greencheek.spray.cache.memcached.examples

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.greencheek.util.memcached.WithMemcached
import org.specs2.runner.JUnitRunner
import spray.caching.Cache
import org.greencheek.spray.cache.memcached.MemcachedCache
import net.spy.memcached.ConnectionFactoryBuilder.Protocol
import spray.util.pimps.PimpedFuture
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import org.greencheek.spray.cache.memcached.keyhashing.XXJavaHash
import org.greencheek.spray.cache.memcached.examples.Container.Panel
import spray.util._
import scala.concurrent._
import ExecutionContext.Implicits.global
import org.greencheek.spy.extensions.SerializingTranscoder

/**
 * Created by dominictootell on 09/06/2014.
 */
@RunWith(classOf[JUnitRunner])
class SerializationInnerClassSpec extends Specification {
  implicit def pimpFuture[T](fut: Future[T]): PimpedFuture[T] = new PimpedFuture[T](fut)

  val memcachedContext = WithMemcached(false)

  "Example inner case class serialization" in memcachedContext {
    val memcachedHosts = "localhost:" + memcachedContext.memcached.port
    val cache: Cache[Panel] = new MemcachedCache[Panel](memcachedHosts = memcachedHosts, protocol = Protocol.TEXT,
      timeToLive = Duration(5, TimeUnit.SECONDS), waitForMemcachedSet = true, keyHashType = XXJavaHash,
      serializingTranscoder = new SerializingTranscoder())

    cache("FancyButtonHolder")(Panel("FancyButtonHolder")).await === Panel("FancyButtonHolder")
    cache.get("FancyButtonHolder").get.await === Panel("FancyButtonHolder")

  }

}

object Container {
  case class Panel(name : String)
}
