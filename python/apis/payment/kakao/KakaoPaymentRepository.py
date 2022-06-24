import pymysql
from ...AppConfig import Mysql

class KakaoPaymentsRepository :       
    def connectDB(self) :
        return pymysql.connect(
            host = Mysql.HOST,
            port = Mysql.PORT,
            user = Mysql.USER_ID,
            passwd = Mysql.USER_PASSWORD,
            db = Mysql.DB
        )
    
    def savePayment(self, orderInfo, tid) :
        db = self.connectDB()
        cursor = db.cursor()
        
        sql = """
            insert into payment(ex_payment_no, payment_method, total_amount, total_point_amount) values (%s, %s, %s, %s, %r)
        """
        
        cursor.execute(sql, (tid, 'kakao', int(orderInfo['totalAmount']), int(orderInfo['totalPointAmount']), tuple(orderInfo['menuList'])))
        
        db.commit()
        db.close()
        
    # TID(카카오결제 고유 결제키(Trade Id))
    # def savePayment(orderID):
    def getPayment(self, orderNo) :
        db = self.connectDB()
        cursor = db.cursor()
        
        sql = """
            select * from payment where orderNo = :%s
        """ % orderNo
        
        cursor.execute(sql)
        
        db.commit()
        db.close()