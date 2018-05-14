package com.cj.dscimeca.onboarding.todo.user

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class RestHandlerManager(handlers: List[RestHandler]) {
  def handle(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val handlerOption = findHandler(req)

    if(handlerOption.isDefined){
      val handler = handlerOption.get
      handlerFound(handler, req, resp)
    }else{
      handlerNotFound(req, resp)
    }
  }

  def findHandler(request: HttpServletRequest): Option[RestHandler] = {
    handlers.find(_.canHandle(request))
  }

  def handlerFound(handler: RestHandler, request: HttpServletRequest, response: HttpServletResponse): Unit ={
    handler.handle(request, response)
  }

  def handlerNotFound(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND)
    response.getWriter.print(s"""Route "${request.getPathInfo}" does not exist""")
  }
}
