from .KakaoPaymentRepository import KakaoPaymentsRepository
from .config import *
import requests

class KakaoPaymentService :
    def readyToPayment(orderInfo) :
        url = PAYMENT_READY_URL
        
        headers = {
            "Authorization": "KakaoAK " + APP_ADMIN_KEY,
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }
        
        data = {
            "cid": CID,
            "partner_order_id": orderInfo["orderNo"],
            "partner_user_id": orderInfo["memNo"],
            "item_name": orderInfo["itemsName"], # 주문 이름 --- 아이스아메리카노 외 X로 들어오면 좋을듯
            "quantity": orderInfo["quantity"], # 수량
            "total_amount": orderInfo["totalAmount"], # 총 금액
            "tax_free_amount": "0", # TaxFree 금액 -- 0 고정
            "approval_url": "http://localhost:5000/payment/kakao/success/%s" %orderInfo["orderNo"], # 성공시 URL
            "fail_url": "https://localhost:5000/payment/kakao/fail/%s" %orderInfo["orderNo"], # 실패시 URL
            "cancel_url": "https://localhost:5000/payment/kakao/cancel/%s" %orderInfo["orderNo"] # 취소시 URL
        }
    
        response = requests.post(url=url, headers=headers, data=data)

        if response.status_code != 200 :
            print("error")
        else : 
            return response.json()
        
    def approveToPayment(orderInfo) :
        url = PAYMENT_APPROVE_URL

        headers = {
            "Authorization": "KakaoAK " + APP_ADMIN_KEY,
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }

        data = {
            "cid": CID,
            "tid": "T2b40e9f69eb7fb59c17",
            "partner_order_id": "partner_order_id",
            "partner_user_id": "partner_user_id",
            "pg_token": "d72700d115272b17fcbc"
        }

        response = requests.post(url=url, headers=headers, data=data)

        if response.status_code != 200 :
            print("error")
        else : 
            datainfo = response.json()
            print(datainfo)
                
    def savePayment(orderInfo, tid) :
        KakaoPaymentsRepository().savePayment(orderInfo, tid)