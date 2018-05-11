package com.cj.dscimeca.onboarding.todo.user

import java.io.IOException

import javax.servlet.ServletException
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

/**
 * Hello world!
 *
 */
class App extends HttpServlet
{
    private val users = Map(
        1 -> User(1, "Bob", "Smith"),
        2 -> User(2, "John", "Doe")
    )

    def getIdFromPath(path: String): Integer = Integer.valueOf(path.split("/").last)

    def isUserPath(path: String): Boolean = {
        val userPathRegex = "/users/[0-9]+".r
        userPathRegex.findAllIn(path).hasNext
    }

    @throws(classOf[ServletException])
    @throws(classOf[IOException])
    override protected def service(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
        var path = req.getPathInfo

        if(isUserPath(path)) {
            val id = getIdFromPath(path)
            val userOption: Option[User] = users.get(id)
            if (userOption.isEmpty) {
                throw new RuntimeException("no User by that Id")
            } else {
                val user: User = userOption.get
                resp.getWriter.print(s"""{id:${user.id},"firstname":"${user.firstName}","lastname":"${user.lastName}","fullname":"${user.firstName} ${user.lastName}"}""")
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
        }
    }

    case class User(id: Integer, firstName: String, lastName: String)
}
