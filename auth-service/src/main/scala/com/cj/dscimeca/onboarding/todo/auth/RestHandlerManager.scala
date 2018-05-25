package com.cj.dscimeca.onboarding.todo.auth

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class RestHandlerManager(handlers: List[RestHandler]) {

  def handle(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    addHeadersToResponse(resp)

    val handlerOption = findHandler(req)

    handlerOption match {
      case Some(handler) => handlerFound(handler, req, resp)
      case None => handlerNotFound(req, resp)
    }
  }

  def addHeadersToResponse(resp: HttpServletResponse): Unit = resp.setHeader("Access-Control-Allow-Origin","*")

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
