from .KakaoPaymentRepository import KakaoPaymentsRepository
from .config import *
import requests

class KakaoPaymentService :
    def readyToPayment(self, paymentNo) :
        payment = KakaoPaymentsRepository().findPaymentByPaymentNo(paymentNo)
        
        partner_user_id = payment['mem_no']
        item_name = str(payment['item_init_name'])
        quantity = payment['total_quantity']
        
        if(payment['total_amount'] == None) :
            init_total_amount = 0
        else :
            init_total_amount = payment['total_amount']
            
        if(payment['total_point_amount'] == None) :
            init_total_point = 0
        else :
            init_total_point = payment['total_point_amount']
            
        total_amount = init_total_amount - init_total_point
        
        if(total_amount > 0) :
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
                "total_amount": str(total_amount), # 총 금액
                "tax_free_amount": "0", # TaxFree 금액 -- 0 고정
                "approval_url": "http://localhost:5000/payment/KAKAO/success?paymentNo=%s" %paymentNo, # 성공시 URL
                "fail_url": "http://localhost:5000/payment/KAKAO/fail?paymentNo=%s" %paymentNo, # 실패시 URL
                "cancel_url": "http://localhost:5000/payment/KAKAO/cancel?paymentNo=%s" %paymentNo # 취소시 URL
            }
        
            response = requests.post(url=url, headers=headers, data=data).json()
                    
            return response
        else :
            return "zero_amount"
        
    def approveToPayment(self, paymentNo, pg_token) :
        payment = KakaoPaymentsRepository().findPaymentByPaymentNo(paymentNo)
        
        partner_user_id = str(payment['mem_no'])
        TID = str(payment['ex_payment_no'])
        
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
        payment = KakaoPaymentsRepository().findPaymentByPaymentNo(paymentNo)
        
        TID = str(payment['ex_payment_no'])
        
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
        payment = KakaoPaymentsRepository().findPaymentByPaymentNo(paymentNo)
        
        TID = str(payment['ex_payment_no'])
                
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
            
                
    def saveTID(self, paymentNo, tid) :
        KakaoPaymentsRepository().saveTID(paymentNo, tid)
    
    def saveConfirmation(self, paymentNo) :
        payment = KakaoPaymentsRepository().findPaymentByPaymentNo(paymentNo)
        mem_no = payment['mem_no']
        member = KakaoPaymentsRepository().findMemberByMemberNo(mem_no)
        mem_point = member['mem_point'] - payment['total_point_amount']
        
        KakaoPaymentsRepository().savePaymentDate(paymentNo)
        KakaoPaymentsRepository().savePaymentStatus(paymentNo)
        KakaoPaymentsRepository().saveMemberPoint(mem_no, mem_point)
        
        
        