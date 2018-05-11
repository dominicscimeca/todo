package com.cj.dscimeca.onboarding.todo.user

import java.io.{PrintWriter, StringWriter}

import org.scalatest.{FunSpec, Matchers}

/**
  * Unit test for simple App.
  */
class AppTest extends FunSpec with Matchers {

  def assertIsJSON(result: String): Unit = if (false) throw new RuntimeException("Is not JSON")

  def generateUserGetByIdReqResp(id: Integer): RequestStub = {
    val id = 1
    val path = "/users/%d".format(id)
    return new RequestStub(path)
  }

  describe("App") {
    //given
    val app = new App()

    describe("getUserById") {
      describe("bob smith") {
        val req = new RequestStub("/users/1")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)
        val result: String = resp.getBodyInString

        it("should return correct JSON as string") {
          result should equal("""{id:1,"firstname":"Bob","lastname":"Smith","fullname":"Bob Smith"}""")
        }
      }
      describe("john doe smith") {
        val req = new RequestStub("/users/2")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)
        val result: String = resp.getBodyInString

        it("should return correct JSON as string") {
          result should equal("""{id:2,"firstname":"John","lastname":"Doe","fullname":"John Doe"}""")
        }
      }
    }
  }

  class RequestStub(path: String) extends HttpServletRequestImplAbstract {
    override def getPathInfo: String = path
  }

  class ResponseStub extends HttpServletResponseImplAbstract {
    val stringWriter: StringWriter = new StringWriter()
    val printWriter: PrintWriter = new PrintWriter(stringWriter)

    def getBodyInString: String = stringWriter.getBuffer.toString

    override def getWriter: PrintWriter = new PrintWriter(printWriter)
  }

}