package org.zerosign.zonk.webhdfs

package object errors {

  type Message = CharSequence

  sealed abstract class HdfsError(message: Option[Message]) extends Throwable(message.getOrElse(null).asInstanceOf[String]) {
    // ignore stacktrace
    @inline override final def fillInStackTrace() : Throwable = this
  }

  final case class IllegalArgument(message: Message) extends HdfsError(Some(message))

  final case class SecurityError(message: Message) extends HdfsError(Some(message))

  final case class HdfsDecodeError(message: Message) extends HdfsError(Some(message))

  final case class FileNotFound(message: Message) extends HdfsError(Some(message))

  final case class AccessControlError(message: Message) extends HdfsError(Some(message))

  final case class StandbyError(message: Message) extends HdfsError(Some(message))

  final case object UnsupportedOperation extends HdfsError(None)

  final case object IOError extends HdfsError(None)

  final case object RuntimeError extends HdfsError(None)
}
