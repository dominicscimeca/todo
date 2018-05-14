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
      it("should return valid user if exists") {
        val user = User(10, "Steven", "Smith")

        DI.UserRepository = new UserRepositoryStub(Some(user))
        val req = new RequestStub("/users/10")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)

        //then
        resp.getBodyInString should equal("""{id:10,"firstname":"Steven","lastname":"Smith","fullname":"Steven Smith"}""")
      }
      it("should 404 if user does not exist"){
        DI.UserRepository = new UserRepositoryStub(None)
        val req = new RequestStub("/users/2")
        val resp = new ResponseStub()

        //when
        app.service(req, resp)

        //then
        resp.getStatus should equal(HttpServletResponse.SC_NOT_FOUND)
        resp.getBodyInString should equal(s"No existing user with id: 2")
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

  class UserRepositoryStub(user: Option[User]) extends UserRepository{
    override def get(id: Int): Option[User] = user
  }

}