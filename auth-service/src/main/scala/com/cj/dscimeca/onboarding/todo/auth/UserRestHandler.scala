package com.cj.dscimeca.onboarding.todo.auth

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import scala.util.matching.Regex

class UserRestHandler(UserRepository: UserRepository) extends RestHandler {
  override def getPathRegex: Regex = "/users/([0-9]+)".r

  override def handle(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val path = request.getPathInfo
    val id = getUserIdFromPath(path)

    val userOption: Option[User] = DI.UserRepository.get(id)

    userOption match {
      case Some(user) => response.getWriter.print(user.toJSONString)
      case None =>
        response.setStatus(HttpServletResponse.SC_NOT_FOUND)
        response.getWriter.print(s"No existing user with id: $id")
    }
  }

  def getUserIdFromPath(path: String): Int = {
    val pathParams = getPathParams(path)
    val idString = pathParams.head

    idString.toInt
  }
}
