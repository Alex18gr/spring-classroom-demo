package io.alexc.classroomdemo.error;

public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException() {
        super();
    }

    public ClassroomNotFoundException(Integer id) {
        super("Classroom with id = " + id + " not found.");
    }

    public ClassroomNotFoundException(String message) {
        super(message);
    }

    public ClassroomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassroomNotFoundException(Throwable cause) {
        super(cause);
    }
}
