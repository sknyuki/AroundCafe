import json
from flask import Blueprint, jsonify, redirect, request
from flask_cors import cross_origin
from flask_restful import abort

from .kakao.KakaoPaymentsService import KakaoPaymentService

paymentBp = Blueprint('paymentBp', __name__)

#결제 준비
@paymentBp.route('/ready/<string:socialType>', methods=['POST'])
def readyToPayment(socialType) :
    orderInfo = request.json
    if socialType == "kakao" :
        paymentInfo = KakaoPaymentService.readyToPayment(orderInfo)
        KakaoPaymentService.savePayment(orderInfo, paymentInfo['tid'])
        return redirect(paymentInfo["next_redirect_pc_url"])
    
#결제 승인
@paymentBp.route('/approve/<string:socialType>/<string:orderNo>')
def paymentSuccess(socialType, orderNo) :
    if socialType == "kakao" :
        pg_token = request.args.get("pg_token")
        # orderNo로 Payment찾아오기
        # 정보 입력 후 승인내역 보내기
        # Payment 정보 입력
        # response 돌려주기
        
        #paymentInfo = KakaoPaymentService.paymentapprove(pg_token)
        #return paymentInfo.status
    
#주문 조회

#결제 취소