package uz.o_rustamov.magnitcrm;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public class Constants {
    public static final String ROLE_DEVELOPER = "ROLE_DEVELOPER";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_DRIVER = "ROLE_DRIVER";
    public static final String ROLE_MARKET_STAFF = "ROLE_MARKET_STAFF";
    public static final String olimjon_rustamov = "olimjon_rustamov";
    public static final String oqil_akramov = "oqil_akramov";
    public static final String asqar_akramov = "asqar_akramov";
    public static final String bahriddin_umarov = "bahriddin_umarov";
    public static final String odil_akramov = "odil_akramov";

    public static final HttpEntity<ApiResponse> NOT_FOUND = ResponseEntity.status(404).body(
                    new ApiResponse("Recource not found", 404, false, null));

    public static final HttpEntity<ApiResponse> SUCCESS = ResponseEntity.ok(
                    new ApiResponse(null, 200, true, "Muvaffaqiyatli amalga oshirildi"));

    public static final HttpEntity<ApiResponse> ALREADY_EXIST = ResponseEntity.status(409).body(
                    new ApiResponse("Resource already exist", 409, false, null));
    public static final HttpEntity<ApiResponse> CONNECTED_WITH_OTHERS_EXCEPTION = ResponseEntity.status(406).body(
                    new ApiResponse("Boshqa ma'lumotlarga bog'langanligi uchun o'chirish imkonsiz", 406, false, null));
}
