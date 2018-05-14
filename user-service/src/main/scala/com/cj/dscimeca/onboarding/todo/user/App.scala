package com.cj.dscimeca.onboarding.todo.user

import java.io.IOException

import javax.servlet.ServletException
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

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
        resp.getWriter.print(s"""Route "${req.getPathInfo}" does not exist""")
    }

    trait Handler{
        def handle(request: HttpServletRequest, response: HttpServletResponse): Unit
        def getPathRegex: Regex

        def canHandle(request: HttpServletRequest): Boolean = {
            val path = request.getPathInfo
            pathMatchesPathRegex(path)
        }

        def pathMatchesPathRegex(path: String): Boolean = getPathRegex.findAllIn(path).hasNext

        def getPathParams(path: String): Seq[String] = {
            val matchData = getPathRegex.findAllIn(path).matchData
            val extractFirstParam = (aMatch:Match) => aMatch.group(1)
            val paramIterator = matchData.map(extractFirstParam)

            paramIterator.toSeq
        }
    }

    object UserRestHandler extends Handler {
        override def getPathRegex: Regex = "/users/([0-9]+)".r

        override def handle(request: HttpServletRequest, response: HttpServletResponse): Unit = {
            val path = request.getPathInfo
            val id = Integer.valueOf(getPathParams(path).head)

            val userOption: Option[User] = DI.UserRepository.get(id)
            if (userOption.isEmpty) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                response.getWriter.print(s"No existing user with id: $id")
            } else {
                val user: User = userOption.get
                response.getWriter.print(user.toJSONString)
            }
        }
    }

}
