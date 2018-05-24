package com.cj.dscimeca.onboarding.todo.user

import java.io.PrintWriter
import java.util
import java.util.Locale

import javax.servlet.ServletOutputStream
import javax.servlet.http.{Cookie, HttpServletResponse}

abstract class HttpServletResponseImplAbstract extends HttpServletResponse {
  override def addCookie(cookie: Cookie): Unit = ???

  override def containsHeader(s: String): Boolean = ???

  override def encodeURL(s: String): String = ???

  override def encodeRedirectURL(s: String): String = ???

  override def encodeUrl(s: String): String = ???

  override def encodeRedirectUrl(s: String): String = ???

  override def sendError(i: Int, s: String): Unit = ???

  override def sendError(i: Int): Unit = ???

  override def sendRedirect(s: String): Unit = ???

  override def setDateHeader(s: String, l: Long): Unit = ???

  override def addDateHeader(s: String, l: Long): Unit = ???

  override def setHeader(s: String, s1: String): Unit = ???

  override def addHeader(s: String, s1: String): Unit = ???

  override def setIntHeader(s: String, i: Int): Unit = ???

  override def addIntHeader(s: String, i: Int): Unit = ???

  override def setStatus(i: Int): Unit = ???

  override def setStatus(i: Int, s: String): Unit = ???

  override def getStatus: Int = ???

  override def getHeader(s: String): String = ???

  override def getHeaders(s: String): util.Collection[String] = ???

  override def getHeaderNames: util.Collection[String] = ???

  override def getCharacterEncoding: String = ???

  override def getContentType: String = ???

  override def getOutputStream: ServletOutputStream = ???

  override def getWriter: PrintWriter = ???

  override def setCharacterEncoding(s: String): Unit = ???

  override def setContentLength(i: Int): Unit = ???

  override def setContentLengthLong(l: Long): Unit = ???

  override def setContentType(s: String): Unit = ???

  override def setBufferSize(i: Int): Unit = ???

  override def getBufferSize: Int = ???

  override def flushBuffer(): Unit = ???

  override def resetBuffer(): Unit = ???

  override def isCommitted: Boolean = ???

  override def reset(): Unit = ???

  override def setLocale(locale: Locale): Unit = ???

  override def getLocale: Locale = ???
}
