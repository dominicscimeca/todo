package com.cj.dscimeca.onboarding.todo.user

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class RestHandlerManager(handlers: List[Handler]) {
  def handle(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    for (handler <- handlers) {
      if (handler.canHandle(req)) {
        handler.handle(req, resp)
        return
      }
    }

    resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    resp.getWriter.print(s"""Route "${req.getPathInfo}" does not exist""")
  }
}
