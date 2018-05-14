package com.cj.dscimeca.onboarding.todo.user

import java.io.IOException

import javax.servlet.ServletException
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}


class App extends HttpServlet {

  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  override protected def service(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    DI.RestHandlerManager.handle(req, resp)
  }
}
