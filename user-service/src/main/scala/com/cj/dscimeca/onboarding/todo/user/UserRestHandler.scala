package com.cj.dscimeca.onboarding.todo.user

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import scala.util.matching.Regex

class UserRestHandler(UserRepository: UserRepository) extends Handler {
  override def getPathRegex: Regex = "/users/([0-9]+)".r

  override def handle(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val path = request.getPathInfo
    val id = Integer.valueOf(getPathParams(path).head)

    val userOption: Option[User] = UserRepository.get(id)
    if (userOption.isEmpty) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND)
      response.getWriter.print(s"No existing user with id: $id")
    } else {
      val user: User = userOption.get
      response.getWriter.print(user.toJSONString)
    }
  }
}
