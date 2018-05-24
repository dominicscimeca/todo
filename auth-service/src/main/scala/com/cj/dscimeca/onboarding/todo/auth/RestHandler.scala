package com.cj.dscimeca.onboarding.todo.auth

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

trait RestHandler{
  def handle(request: HttpServletRequest, response: HttpServletResponse): Unit
  def getPathRegex: Regex

  def canHandle(request: HttpServletRequest): Boolean = {
    val path = request.getPathInfo
    pathMatchesPathRegex(path)
  }

  def pathMatchesPathRegex(path: String): Boolean = getPathRegex.findAllIn(path).hasNext

  def getPathParams(path: String): Seq[String] = {
    val matchData = getPathRegex.findAllIn(path).matchData
    val extractFirstParam = (aMatch:Match) => aMatch.group(1)
    val paramIterator = matchData.map(extractFirstParam)

    paramIterator.toSeq
  }
}
