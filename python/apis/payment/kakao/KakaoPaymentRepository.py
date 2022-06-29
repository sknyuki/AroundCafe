import pymysql
import datetime
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
        
    def savePaymentDate(self, paymentNo) :
        db = self.connectDB()
        cursor = db.cursor()
        
        sql = """
            UPDATE payment SET payment_date = '%s' WHERE payment_no = %s
        """ %(datetime.datetime.now(), paymentNo)
        
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
        
        field_names = [i[0] for i in cursor.description]
        payment = dict(zip(field_names, cursor.fetchone()))
        
        db.commit()
        db.close()
        
        return payment