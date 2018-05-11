package com.cj.dscimeca.onboarding.todo.user

import java.io.{PrintWriter, StringWriter}

import javax.servlet.http.HttpServletResponse
import org.scalatest.{FunSpec, Matchers}

/**
  * Unit test for simple App.
  */
class AppTest extends FunSpec with Matchers {

  describe("App") {
    //given
    val app = new App()
    describe("routing"){
      it("should return 404 status when route doesn't exist"){
        //given
        val req = new RequestStub("/not-a-real-route")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)

        //then
        val status = resp.getStatus
        status should equal(HttpServletResponse.SC_NOT_FOUND)
      }
    }
    describe("getUserById") {
      it("bob smith") {
        val req = new RequestStub("/users/1")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)
        val result: String = resp.getBodyInString

        result should equal("""{id:1,"firstname":"Bob","lastname":"Smith","fullname":"Bob Smith"}""")
      }
      it("john doe smith") {
        val req = new RequestStub("/users/2")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)
        val result: String = resp.getBodyInString

        result should equal("""{id:2,"firstname":"John","lastname":"Doe","fullname":"John Doe"}""")
      }
      it("no id should return 404") {
        val req = new RequestStub("/users/")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)

        //then
        resp.getStatus should equal(404)
      }
    }
  }

  class RequestStub(path: String) extends HttpServletRequestImplAbstract {
    override def getPathInfo: String = path
  }

  class ResponseStub extends HttpServletResponseImplAbstract {
    val stringWriter: StringWriter = new StringWriter()
    val printWriter: PrintWriter = new PrintWriter(stringWriter)
    var status = 200

    def getBodyInString: String = stringWriter.getBuffer.toString

    override def getWriter: PrintWriter = new PrintWriter(printWriter)

    override def setStatus(status: Int): Unit = this.status = status

    override def getStatus: Int = this.status
  }

}