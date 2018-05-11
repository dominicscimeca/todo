package com.cj.dscimeca.onboarding.todo.user

import java.io.{PrintWriter, StringWriter}

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

  class RequestStub(path: String) extends HttpServletRequestImplAbstract {
    override def getPathInfo: String = path
  }

  class ResponseStub(stringWriter: StringWriter) extends HttpServletResponseImplAbstract {
    val printWriter: PrintWriter = new PrintWriter(stringWriter)

    def getBodyInString: String = stringWriter.getBuffer.toString

    override def getWriter: PrintWriter = new PrintWriter(printWriter)
  }

}