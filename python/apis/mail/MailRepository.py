import pymysql
import datetime
from ..AppConfig import Mysql


class MailRepository:
    def connectDB(self):
        return pymysql.connect(
            host=Mysql.HOST,
            port=Mysql.PORT,
            user=Mysql.USER_ID,
            passwd=Mysql.USER_PASSWORD,
            db=Mysql.DB
        )

    def findEmailByCafeNo(self, cafeNo):
        db = self.connectDB()
        cursor = db.cursor()

        sql = """
            select mem_id from members where mem_no = (select member_no from cafe where cafe_no = '%s');
        """ % cafeNo

        cursor.execute(sql)
        email = cursor.fetchone()[0]

        db.commit()
        db.close()

        return email

    def findCafeNameByCafeNo(self, cafeNo):
        db = self.connectDB()
        cursor = db.cursor()

        sql = """
            select cafe_name from cafe where cafe_no = '%s';
        """ % cafeNo

        cursor.execute(sql)
        cafe_name = cursor.fetchone()[0]

        db.commit()
        db.close()

        return cafe_name

    def findEmailByMemNo(self, memNo):
        db = self.connectDB()
        cursor = db.cursor()

        sql = """
            select mem_id from members where mem_no = '%s';
        """ % memNo

        cursor.execute(sql)
        email = cursor.fetchone()[0]

        db.commit()
        db.close()

        return email

    def findMemNickByMemNo(self, memNo):
        db = self.connectDB()
        cursor = db.cursor()

        sql = """
            select mem_nick from members where mem_no = '%s';
        """ % memNo

        cursor.execute(sql)
        mem_nick = cursor.fetchone()[0]

        db.commit()
        db.close()

        return mem_nick
