package com.cj.dscimeca.onboarding.todo.user

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}


class App extends HttpServlet {

  override protected def service(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    DI.RestHandlerManager.handle(req, resp)
  }
}
