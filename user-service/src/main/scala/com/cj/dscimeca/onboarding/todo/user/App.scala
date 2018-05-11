package com.cj.dscimeca.onboarding.todo.user

import java.io.IOException

import javax.servlet.ServletException
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import scala.collection.mutable
import scala.util.matching.Regex
import scala.collection.mutable.ListBuffer

/**
 * Hello world!
 *
 */
class App extends HttpServlet
{
    @throws(classOf[ServletException])
    @throws(classOf[IOException])
    override protected def service(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
        val handlers: List[Handler] = List(UserRestHandler)

        for(handler <- handlers){
            if(handler.canHandle(req)){
                handler.handle(req,resp)
                return
            }
        }

        resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }

    trait Handler{
        def handle(request: HttpServletRequest, response: HttpServletResponse): Unit
        def getPathRegex: Regex

        def canHandle(request: HttpServletRequest): Boolean = {
            val path = request.getPathInfo
            pathMatchesPathRegex(path)
        }

        def pathMatchesPathRegex(path: String): Boolean = getPathRegex.findAllIn(path).hasNext
        def getPathParams(path: String): List[String] = {
            var params = new ListBuffer[String]()
            getPathRegex.findAllIn(path).matchData foreach {
                m => params += m.group(1)
            }

            params.toList
        }
    }

    object UserRestHandler extends Handler {
        override def getPathRegex: Regex = "/users/([0-9]+)".r

        override def handle(request: HttpServletRequest, response: HttpServletResponse): Unit = {
            val path = request.getPathInfo
            val id = Integer.valueOf(getPathParams(path).head)

            val userOption: Option[User] = UserRepository.get(id)
            if (userOption.isEmpty) {
                throw new RuntimeException("no User by that Id")
            } else {
                val user: User = userOption.get
                response.getWriter.print(user.toJSONString)
            }
        }

    }

    object UserRepository{
        private val users = Map(
            1 -> User(1, "Bob", "Smith"),
            2 -> User(2, "John", "Doe")
        )

        def get(id: Int): Option[User] = {
            users.get(id)
        }
    }

    case class User(id: Integer, firstName: String, lastName: String){

        def getFullName: String = "%s %s".format(firstName, lastName)

        def toJSONString: String = s"""{id:$id,"firstname":"$firstName","lastname":"$lastName","fullname":"$getFullName"}"""
    }
}
