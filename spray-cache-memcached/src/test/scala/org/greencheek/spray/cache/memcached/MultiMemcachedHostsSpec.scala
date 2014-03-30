package org.greencheek.spray.cache.memcached


// adds await on the future
import spray.util._

import org.greencheek.util.memcached.{WithMemcached, MemcachedBasedSpec}
import akka.actor.ActorSystem
import net.spy.memcached.ConnectionFactoryBuilder.Protocol
import scala.concurrent._
import ExecutionContext.Implicits.global
import org.greencheek.util.PortUtil

/**
 * Created by dominictootell on 30/03/2014.
 */
class MultiMemcachedHostsSpec extends MemcachedBasedSpec {

  implicit val system = ActorSystem()

  val memcachedContext = WithMemcached(false)


  "A Memcached cache" >> {
    "can store values when one host is unavailabled" in memcachedContext {

      val randomPort = PortUtil.getPort(PortUtil.findFreePort)
      val hosts = "localhost:"+memcachedContext.memcached.port + ",localhost:"+randomPort

      val cache = new MemcachedCache[String] ( memcachedHosts = hosts, protocol = Protocol.TEXT,
        doHostConnectionAttempt = true)


      cache("1")("A").await == "A"
      cache("2")("B").await == "B"

      memcachedContext.memcached.daemon.get.getCache.getCurrentItems == 2
      memcachedD.daemon.get.getCache.getCurrentItems == 0

    }
  }

}
