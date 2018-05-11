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
    @throws(classOf[ServletException])
    @throws(classOf[IOException])
    override protected def service(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
        resp.getWriter.print("""{id:1,"firstname":"Bob","lastname":"Smith","fullname":"Bob Smith"}""")
//        super.service(req, resp);
    }
}
