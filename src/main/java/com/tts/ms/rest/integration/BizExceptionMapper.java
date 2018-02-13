package com.tts.ms.rest.integration;


import com.tts.ms.exception.BizCoreRuntimeException;
import com.tts.ms.rest.common.ErrorMessage;
import com.tts.ms.util.ErrorCodeMessageUtil;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BizExceptionMapper implements ExceptionMapper<BizCoreRuntimeException> {


    @Override
    public Response toResponse(BizCoreRuntimeException exception) {

        String message = null;
        String errorCode = exception.getErrorCode();
        if (exception.getErrorCode() != null) {
            message = ErrorCodeMessageUtil.getErrorCodeMessage(exception.getErrorCode(),
                    exception.getErrorContents());
        } else if (exception.getMessage() != null) {
            message = exception.getMessage();
        }

        ErrorMessage errorMessage = new ErrorMessage(errorCode, message);

        return errorMessage.buildResponse();
    }

}
