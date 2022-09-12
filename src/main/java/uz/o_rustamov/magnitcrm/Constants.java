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

    public static final HttpEntity<ApiResponse> ADD_OUTPUT_MARKET_STAFF_WARNING = ResponseEntity.ok(
            new ApiResponse(null, 100, true, "Ushbu amal market xodimi tomonidan bajarilganligi uchun mahsulotlar soniga ta'sir qilmaydi. Faqatgina haydovchi uchun yoziladi."));

    public static final HttpEntity<ApiResponse> ALREADY_EXIST = ResponseEntity.status(409).body(
            new ApiResponse("Resource already exist", 409, false, null));
    public static final HttpEntity<ApiResponse> CONNECTED_WITH_OTHERS_EXCEPTION = ResponseEntity.status(406).body(
            new ApiResponse("Boshqa ma'lumotlarga bog'langanligi uchun o'chirish imkonsiz", 406, false, null));

    public static final HttpEntity<ApiResponse> PARSE_EXCEPTION = ResponseEntity.status(406).body(
            new ApiResponse("Parse qilishda xatolik", 406, false, null));

    public static final HttpEntity<ApiResponse> FCM_ERROR = ResponseEntity.ok(new ApiResponse("Muvaffaqiyatli saqlandi. SMS yuborishda xatolik",
            200, true, null));
    public static final HttpEntity<ApiResponse> YOU_DONT_HAVE_ACCESS = ResponseEntity.status(403).body(new ApiResponse(
            "Sizda ushbu amalni bajarish uchun ruxsat mavjud emas",
            403, false, null));
    public static final HttpEntity<ApiResponse> TOKEN_EXPIRED = ResponseEntity.status(401).body(new ApiResponse(
            "Identifikatsiya ma'lumotlari eskirgan",
            401, false, null));

}
