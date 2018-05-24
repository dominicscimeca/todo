package com.cj.dscimeca.onboarding.todo.user

import java.io.BufferedReader
import java.security.Principal
import java.util
import java.util.Locale

import javax.servlet._
import javax.servlet.http._

abstract class HttpServletRequestImplAbstract extends HttpServletRequest {
  override def getAuthType: String = ???

  override def getCookies: Array[Cookie] = ???

  override def getDateHeader(s: String): Long = ???

  override def getHeader(s: String): String = ???

  override def getHeaders(s: String): util.Enumeration[String] = ???

  override def getHeaderNames: util.Enumeration[String] = ???

  override def getIntHeader(s: String): Int = ???

  override def getMethod: String = ???

  override def getPathInfo: String = ???

  override def getPathTranslated: String = ???

  override def getContextPath: String = ???

  override def getQueryString: String = ???

  override def getRemoteUser: String = ???

  override def isUserInRole(s: String): Boolean = ???

  override def getUserPrincipal: Principal = ???

  override def getRequestedSessionId: String = ???

  override def getRequestURI: String = ???

  override def getRequestURL: StringBuffer = ???

  override def getServletPath: String = ???

  override def getSession(b: Boolean): HttpSession = ???

  override def getSession: HttpSession = ???

  override def changeSessionId(): String = ???

  override def isRequestedSessionIdValid: Boolean = ???

  override def isRequestedSessionIdFromCookie: Boolean = ???

  override def isRequestedSessionIdFromURL: Boolean = ???

  override def isRequestedSessionIdFromUrl: Boolean = ???

  override def authenticate(httpServletResponse: HttpServletResponse): Boolean = ???

  override def login(s: String, s1: String): Unit = ???

  override def logout(): Unit = ???

  override def getParts: util.Collection[Part] = ???

  override def getPart(s: String): Part = ???

  override def upgrade[T <: HttpUpgradeHandler](aClass: Class[T]): T = ???

  override def getAttribute(s: String): AnyRef = ???

  override def getAttributeNames: util.Enumeration[String] = ???

  override def getCharacterEncoding: String = ???

  override def setCharacterEncoding(s: String): Unit = ???

  override def getContentLength: Int = ???

  override def getContentLengthLong: Long = ???

  override def getContentType: String = ???

  override def getInputStream: ServletInputStream = ???

  override def getParameter(s: String): String = ???

  override def getParameterNames: util.Enumeration[String] = ???

  override def getParameterValues(s: String): Array[String] = ???

  override def getParameterMap: util.Map[String, Array[String]] = ???

  override def getProtocol: String = ???

  override def getScheme: String = ???

  override def getServerName: String = ???

  override def getServerPort: Int = ???

  override def getReader: BufferedReader = ???

  override def getRemoteAddr: String = ???

  override def getRemoteHost: String = ???

  override def setAttribute(s: String, o: scala.Any): Unit = ???

  override def removeAttribute(s: String): Unit = ???

  override def getLocale: Locale = ???

  override def getLocales: util.Enumeration[Locale] = ???

  override def isSecure: Boolean = ???

  override def getRequestDispatcher(s: String): RequestDispatcher = ???

  override def getRealPath(s: String): String = ???

  override def getRemotePort: Int = ???

  override def getLocalName: String = ???

  override def getLocalAddr: String = ???

  override def getLocalPort: Int = ???

  override def getServletContext: ServletContext = ???

  override def startAsync(): AsyncContext = ???

  override def startAsync(servletRequest: ServletRequest, servletResponse: ServletResponse): AsyncContext = ???

  override def isAsyncStarted: Boolean = ???

  override def isAsyncSupported: Boolean = ???

  override def getAsyncContext: AsyncContext = ???

  override def getDispatcherType: DispatcherType = ???
}