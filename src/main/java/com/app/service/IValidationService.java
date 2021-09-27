package com.app.service;

import com.app.pojos.Credential;

public interface IValidationService {

	Object validateLogin(Credential cred);

}
