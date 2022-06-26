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
    
    def saveTID(self, paymentNo, tid) :
        db = self.connectDB()
        cursor = db.cursor()
        
        sql = """
            UPDATE payment SET ex_payment_no = '%s' WHERE payment_no = %s
        """ %(tid, paymentNo)
        
        cursor.execute(sql)
        
        db.commit()
        db.close()
        
    def findByPaymentNo(self, paymentNo) :
        db = self.connectDB()
        cursor = db.cursor()
        
        sql = """
            select * from payment where payment_no = %s
        """ % paymentNo
        
        cursor.execute(sql)
        payment = cursor.fetchall()[0]
        db.commit()
        db.close()
        
        return payment