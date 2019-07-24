package org.zerosign.zonk.webhdfs.types

import fs2.Stream
import org.zerosign.zonk.webhdfs.errors._
import org.zerosign.zonk.webhdfs.data.{ FileStatus, Checksum }

trait WebhdfsClient[F[_]] {
  type Error = HdfsError

  def home() : F[Either[Error, String]]

  def create(path: String, in: Stream[F, Byte], overwrite: Boolean) : F[Either[Error, Boolean]]

  def append(path: String, in: Stream[F, Byte]) : F[Either[Error, Boolean]]

  def open(path: String, offset: Option[Long], length: Option[Int], size: Option[Int]) : Stream[F, Byte]

  def concat(paths: Seq[String], target: String) : F[Either[Error, Boolean]]

  def mkdir(path: String) : F[Either[Error, Boolean]]

  def rename(src: String, dest: String) : F[Either[Error, Boolean]]

  def delete(path: String, recursive: Boolean) : F[Either[Error, Boolean]]

  def status(path: String) : F[Either[Error, FileStatus]]

  def list(path: Option[String]) : Stream[F, FileStatus]

  def checksum(path: String) : F[Either[Error, Checksum]]
}
