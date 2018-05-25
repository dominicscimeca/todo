package com.cj.dscimeca.onboarding.todo.auth

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import java.util.stream.Collectors

import scala.util.matching.Regex

class UserRestHandler(credentialsService: CredentialsService) extends RestHandler {
  override def getPathRegex: Regex = "/auth".r

  def respondWithJWT(response: HttpServletResponse) = ???

  def responseWith404(response: HttpServletResponse) = ???

  override def handle(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val path = request.getPathInfo

    val body = request.getReader.lines.collect(Collectors.joining(System.lineSeparator))
    val (username, password) = getUsernameAndPasswordFromBody(body)

    val isCredentialsValid = credentialsService.isCredentialsValid(username, password)

    if(isCredentialsValid){
      respondWithJWT(response)
    }else{
      responseWith404(response)
    }
  }

  def getUsernameAndPasswordFromBody(body: String): (String, String) = {
    ("bobsmith", "test1234")
  }
}
