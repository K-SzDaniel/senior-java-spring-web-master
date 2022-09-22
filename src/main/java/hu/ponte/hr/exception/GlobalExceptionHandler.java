

package hu.ponte.hr.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final MessageSource messageSource;


    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    private ValidationError processFieldErrors(List<FieldError> fieldErrors) {

        ValidationError validationError = new ValidationError();

        for (FieldError fieldError : fieldErrors) {
            validationError.addFieldError(fieldError.getField(), messageSource.getMessage(fieldError, Locale.getDefault()));
        }

        return validationError;
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiError> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        logger.error("Image size is to big: ", ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiError body = new ApiError("IMAGE_SIZE_ERROR", "The image should be between 0 and 2Mb.", ex.getLocalizedMessage());

        return new ResponseEntity<>(body, status);

    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> defaultErrorHandler(Throwable t) {
        logger.error("An unexpected error occurred: ", t);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiError body = new ApiError("UNCLASSIFIED_ERROR", "Oh, snap! Something really unexpected occurred.", t.getLocalizedMessage());

        return new ResponseEntity<>(body, status);
    }


}
