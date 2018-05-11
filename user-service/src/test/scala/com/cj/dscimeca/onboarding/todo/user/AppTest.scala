package com.cj.dscimeca.onboarding.todo.user

import java.io.{BufferedReader, PrintWriter, StringWriter}
import java.security.Principal
import java.util
import java.util.Locale

import javax.servlet._
import javax.servlet.http._
import org.scalatest.{FunSpec, Matchers}

/**
  * Unit test for simple App.
  */
class AppTest extends FunSpec with Matchers {

  def assertIsJSON(result: String): Unit = if (false) throw new RuntimeException("Is not JSON")

  describe("App") {
    //given
    val app = new App()

    describe("getUserById") {
      //given
      val id = 1
      val path = "/users/%d".format(id)
      val req = new RequestStub(path)
      var writer = new StringWriter()
      val resp = new ResponseStub(writer)

      //when
      app.service(req, resp)
      val result: String = resp.getBodyInString

      it("should return correct JSON as string") {
        result should equal("""{id:1,"firstname":"Bob","lastname":"Smith","fullname":"Bob Smith"}""")
      }
    }
  }

  class ResponseStub(stringWriter: StringWriter) extends ResponseStubOverride {
    val printWriter: PrintWriter = new PrintWriter(stringWriter)

    def getBodyInString: String = stringWriter.getBuffer.toString

    override def getWriter: PrintWriter = new PrintWriter(printWriter)
  }

  abstract class ResponseStubOverride extends HttpServletResponse {
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

  class RequestStub(path: String) extends RequestStubOverride {
    override def getPathInfo: String = path
  }

  abstract class RequestStubOverride extends HttpServletRequest {
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

}