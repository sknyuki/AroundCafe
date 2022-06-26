from .KakaoPaymentRepository import KakaoPaymentsRepository
from .config import *
import requests

class KakaoPaymentService :
    def readyToPayment(self, paymentNo) :
        payment = KakaoPaymentsRepository().findByPaymentNo(paymentNo)
        
        partner_user_id = str(payment[11])
        item_name = str(payment[6])
        quantity = payment[12]
        total_amount = str(payment[9] - payment[10])
        
        url = PAYMENT_READY_URL
        
        headers = {
            "Authorization": "KakaoAK " + APP_ADMIN_KEY,
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }
        data = {
            "cid": CID,
            "partner_order_id": paymentNo,
            "partner_user_id": partner_user_id,
            "item_name": item_name, # 주문 이름 --- 아이스아메리카노 외 X로 들어오면 좋을듯
            "quantity": quantity, # 수량
            "total_amount": total_amount, # 총 금액
            "tax_free_amount": "0", # TaxFree 금액 -- 0 고정
            "approval_url": "http://localhost:5000/payment/kakao/success?paymentNo=%s" %paymentNo, # 성공시 URL
            "fail_url": "http://localhost:5000/payment/kakao/fail?paymentNo=%s" %paymentNo, # 실패시 URL
            "cancel_url": "http://localhost:5000/payment/kakao/cancel?paymentNo=%s" %paymentNo # 취소시 URL
        }
    
        response = requests.post(url=url, headers=headers, data=data).json()
                   
        return response
        
    def approveToPayment(self, paymentNo, pg_token) :
        payment = KakaoPaymentsRepository().findByPaymentNo(paymentNo)
        
        partner_user_id = str(payment[11])
        TID = str(payment[3])
        item_name = str(payment[6])
        quantity = payment[12]
        total_amount = str(payment[9] - payment[10])
        
        url = PAYMENT_APPROVE_URL

        headers = {
            "Authorization": "KakaoAK " + APP_ADMIN_KEY,
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }

        data = {
            "cid": CID,
            "tid": TID,
            "partner_order_id": paymentNo,
            "partner_user_id": partner_user_id,
            "pg_token": pg_token
        }

        response = requests.post(url=url, headers=headers, data=data)

        if response.status_code != 200 :
            print("error")
        else : 
            return response.json()
        
    def checkPayment(self, paymentNo) :
        payment = KakaoPaymentsRepository().findByPaymentNo(paymentNo)
        
        TID = str(payment[3])
        
        url = PAYMENT_CHECK_URL
        
        headers = {
            "Authorization": "KakaoAK " + APP_ADMIN_KEY,
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }
        
        data = {
            "cid": CID,
            "tid": TID,
        }
        
        response = requests.post(url=url, headers=headers, data=data)
        
        if response.status_code != 200 :
            print("error")
        else : 
            return response.json()
        
        
    def cancelPayment(self, paymentNo, cancelAmount) :
        payment = KakaoPaymentsRepository().findByPaymentNo(paymentNo)
        
        TID = str(payment[3])
                
        url = PAYMENT_CANCEL_URL
        
        headers = {
            "Authorization": "KakaoAK " + APP_ADMIN_KEY,
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }
        
        data = {
            "cid": CID,
            "tid": TID,
            "cancel_amount": cancelAmount,
            "cancel_tax_free_amount": "0",
        }
        
        response = requests.post(url=url, headers=headers, data=data)
        
        if response.status_code != 200 :
            print("error")
        else : 
            return response.json()
            
                
    def savePayment(self, paymentNo, tid) :
        KakaoPaymentsRepository().saveTID(paymentNo, tid)