package org.greencheek.spray.cache.memcached.perftests

import spray.util._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import org.greencheek.spray.cache.memcached.perf.state._

/**
 * Created by dominictootell on 01/06/2014.
 */
object SimpleGetBenchmarks {

  def testSmallGet(key :SmallCacheKey, value :SmallCacheObject, cache : XXJavaHashSmallTextBenchmarkCache) : SmallCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testSmallNativeGet(key :SmallCacheKey, value :SmallCacheObject, cache : XXNativeJavaHashSmallTextBenchmarkCache) : SmallCacheObject = {
    return cache.cache.apply(key)(value).await
  }


  def simpleHashAlgoGet(key :SmallCacheKey, value :SmallCacheObject, cache : XXJavaHashSmallTextHashAlgoBenchmarkCache) : SmallCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def simpleAsciiOnlyHashAlgoGet(key :SmallCacheKey, value :SmallCacheObject, cache : AsciiOnlyXXJavaHashSmallTextBenchmarkCache) : SmallCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testMediumGet(key :LargeCacheKey, value : MediumCacheObject, cache : XXJavaMediumTextBenchmarkCache) : MediumCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testMediumCompressedGet(key :LargeCacheKey, value : MediumCacheObject, cache : XXJavaMediumCompressedTextBenchmarkCache ) : MediumCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testLargeGet(key :LargeCacheKey, value :LargeCacheObject, cache : XXJavaHashLargeTextBenchmarkCache) : LargeCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testLargeGetWithFST(key :LargeCacheKey, value :LargeCacheObject, cache : XXJavaHashLargeTextBenchmarkCacheWithFST) : LargeCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testLargeGetWithFSTWithStale(key :LargeCacheKey, value :LargeCacheObject, cache : XXJavaHashLargeTextBenchmarkCacheWithFSTWithStale) : LargeCacheObject = {
    return cache.cache.apply(key)(value).await
  }

  def testLargeGetWithFSTKnownClasses(key :LargeCacheKey, value :LargeCacheObject, cache : XXJavaHashLargeTextBenchmarkCacheWithFSTKnownClasses) : LargeCacheObject = {
    return cache.cache.apply(key)(value).await
  }
}
