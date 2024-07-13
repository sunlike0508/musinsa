package com.waug.ota.booking.controller.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import org.springframework.test.web.servlet.ResultHandler;

public class RailEuropeBookingDetailDocuments {

    public static ResultHandler getRailEuropePTPBookingCardDetail() {
        return document("rail-europe-ptp-booking-card-detail",
                responseFields(beneathPath("legs").withSubsectionId("legs"),
                        fieldWithPath("originLabel").description("출발지"),
                        fieldWithPath("destinationLabel").description("도착지"),
                        fieldWithPath("departure").description("출발시간"), fieldWithPath("arrival").description("도착시간"),
                        fieldWithPath("duration").description("소요시간"),
                        fieldWithPath("travelerCount").description("여행 총 인원수"),
                        fieldWithPath("segmentCount").description("경유 횟수")));
    }


    public static ResultHandler getRailEuropePTPBookingDetail() {
        return document("rail-europe-ptp-booking-detail",
                responseFields(beneathPath("bookingDetailInfo").withSubsectionId("bookingDetailInfo"),
                        subsectionWithPath("legs").description("<<rail-europe-ptp-booking-detail-legs, 노선정보>>"),
                        fieldWithPath("isAllPossibleCancel").description("전체 환불 가능 유무 true : 가능"),
                        fieldWithPath("type").description("<<product-type,레일유로 ProductType>>"),
                        fieldWithPath("bookingReference").description("레일 유로 예약 번호"),
                        subsectionWithPath("travelers").description("<<rail-europe-dto-traveler,Traveler>> 리스트"),
                        fieldWithPath("refundAbleType").description("<<refundable-type, 환불타입>>"),
                        fieldWithPath("ticketingOptionCode").description("<<ticketing-option-code, 티켓 사용방법 코드>>"),
                        fieldWithPath("valueDocuments").description("(PAH 일때) 바우처 List"),
                        subsectionWithPath("products").description("<<rail-europe-dto-product,Product>> 리스트")),
                responseFields(beneathPath("bookingDetailInfo.legs").withSubsectionId("legs"),
                        fieldWithPath("originLabel").description("출발지"),
                        fieldWithPath("destinationLabel").description("도착지"),
                        fieldWithPath("departure").description("출발시간"), fieldWithPath("arrival").description("도착시간"),
                        fieldWithPath("duration").description("소요시간"),
                        fieldWithPath("travelerCount").description("여행 총 인원수"),
                        fieldWithPath("segmentCount").description("경유 횟수"), fieldWithPath("offerStatus").description(
                                "DEFAULT : 기본값, EXTERNAL_RESERVATION : 외부예약필요, UNKNOWN_SEAT : 좌석 포함을 알 수 없음"),
                        subsectionWithPath("segments").description(
                                "<<rail-europe-ptp-booking-detail-segments,각 구간에 대한 정보>>")),
                responseFields(beneathPath("bookingDetailInfo.legs[].segments").withSubsectionId("segments"),
                        fieldWithPath("[].segmentId").description("segment 아이디"),
                        fieldWithPath("[].originLabel").description("출발지"),
                        fieldWithPath("[].destinationLabel").description("도착지"),
                        fieldWithPath("[].departure").description("출발시간"),
                        fieldWithPath("[].arrival").description("도착시간"),
                        fieldWithPath("[].duration").description("소요시간"),
                        fieldWithPath("[].type").description("이동수단 종류"),
                        fieldWithPath("[].vehicleType").description("열차이름"),
                        fieldWithPath("[].vehicleReference").description("열차번호"),
                        fieldWithPath("[].vehicleLogo").description("공급업체 로고 url"),
                        fieldWithPath("[].comfortCategory").description("좌석등급"),
                        fieldWithPath("[].flexibility").description("환불정책"),
                        fieldWithPath("[].isSegmentConnection").description("환승여부"),
                        subsectionWithPath("[].services").description(
                                "<<rail-europe-ptp-booking-detail-service,해당열차 서비스 리스트>>"),
                        subsectionWithPath("[].segmentConnection").description(
                                "<<rail-europe-ptp-booking-detail-segmentConnection,해당열차 환승정보>>"),
                        subsectionWithPath("[].travelers").description(
                                "<<rail-europe-ptp-booking-detail-travelers,해당열차 여행자 리스트>>")),
                responseFields(beneathPath("bookingDetailInfo.legs[].segments[].services").withSubsectionId("services"),
                        fieldWithPath("[].serviceLabel").description("서비스 항목")), responseFields(
                        beneathPath("bookingDetailInfo.legs[].segments[].segmentConnection").withSubsectionId(
                                "segmentConnection"), fieldWithPath("connectionLabel").description("환승지"),
                        fieldWithPath("vehicleType").description("환승열차 이름"),
                        fieldWithPath("vehicleReference").description("환승열차 번호"),
                        fieldWithPath("isTransfer").description(
                                "transfer 유무 [ true : 다른 정거장에서 환승, false : 동일한 정거장에서 환승 ]"),
                        fieldWithPath("connectingTime").description("대기시간")), responseFields(
                        beneathPath("bookingDetailInfo.legs[].segments[].travelers").withSubsectionId("travelers"),
                        fieldWithPath("[].travelerId").description("여행자 아이디"),
                        fieldWithPath("[].type").description("여행자 구분"), fieldWithPath("[].firstName").description("이름"),
                        fieldWithPath("[].lastName").description("성"), fieldWithPath("[].age").description("나이"),
                        fieldWithPath("[].price").description("여행자의 구간 금액"),
                        fieldWithPath("[].seatName").description("좌석번호"),
                        fieldWithPath("[].canceled").description("취소상태 [true : 취소됨]"),
                        fieldWithPath("[].isPossibleCancel").description("취소 가능 유무"),
                        fieldWithPath("[].cancellableItem").description("취소 요청 아이디"),
                        subsectionWithPath("[].ticketInfo").description(
                                "<<rail-europe-ptp-booking-detail-ticketInfo,해당 여행자의 티켓정보>>"),
                        subsectionWithPath("[].travelerLabel").description(
                                "<<rail-europe-ptp-booking-detail-travelerLabel,여행자의 예약상태>>")), responseFields(
                        beneathPath("bookingDetailInfo.legs[].segments[].travelers[].ticketInfo").withSubsectionId(
                                "ticketInfo"), fieldWithPath("type").description("여행자 타입"),
                        fieldWithPath("ticketLabel").description("티켓이름"),
                        fieldWithPath("conditions").description("설명 내용"),
                        fieldWithPath("translation").description("설명 내용에 대한 번역. locale 기반")), responseFields(
                        beneathPath("bookingDetailInfo.legs[].segments[].travelers[].travelerLabel").withSubsectionId(
                                "travelerLabel"), fieldWithPath("status").description("예약상태"),
                        fieldWithPath("color").description("표시될 색상")));
    }

}
