package com.example.minitwitter.util.exceptions;

import java.util.Map;

class ApiValidationException(val errors:Map<String, String>) : Exception("Validation failed")

