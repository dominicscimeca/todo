package com.cj.dscimeca.onboarding.todo.auth

object DI{
  var CredentialsRepository:CredentialsRepository = CredentialsRepositoryInMemory

  var CredentialsService:CredentialsService = new CredentialsService(CredentialsRepository)

  private val restHandlers: List[RestHandler] = List(new UserRestHandler(CredentialsService))

  val RestHandlerManager: RestHandlerManager = new RestHandlerManager(restHandlers)

}
